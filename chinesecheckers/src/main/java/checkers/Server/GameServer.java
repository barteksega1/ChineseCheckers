package checkers.Server;

//mvn clean compile exec:java -Pserver

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import checkers.Game.GameThread;

public class GameServer {
    private final int port;
    private final int playerCount;
    private final int gameSize;
    private int numberOfJoinedPlayers = 0;
    private GameThread game;
    private boolean serverRunning = false;
    private boolean gameRunning = false;

    public GameServer(int port, int playerCount, int gameSize) {
        this.playerCount = playerCount;
        this.port = port;
        this.gameSize = gameSize;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = createServerSocket()) {
            System.out.println("Serwer uruchomiony na porcie " + port);
            serverRunning = true;
            while(serverRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowy gracz dołączył: " + socket.getInetAddress() + "\n");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                
                if (numberOfJoinedPlayers > 0) {
                    processJoiner(socket, reader, writer);
                    System.out.println("processed more players");
                    numberOfJoinedPlayers++;
                    if(numberOfJoinedPlayers == playerCount) {
                        game.getCommunicationDevice().sendMessageToAllPlayers("Wszyscy gracze połączeni. Gra się rozpoczyna!");
                        game.getCommunicationDevice().sendMessageToAllPlayers("Player Count is: " + playerCount + ", Game Size is: " + gameSize);
                        gameRunning = true;
                    }
                }

                else if(numberOfJoinedPlayers == 0) {
                    processFirstPlayer(socket, reader, writer, playerCount, gameSize);
                    System.out.println("processed first player");
                    numberOfJoinedPlayers++;
                }
                
                else if (gameRunning) {
                    writer.write("Sorry, the game is already running");
                }
                
            }
        }
          
    } 
    

    protected ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(8080); //default port
    }

    public void processFirstPlayer(Socket socket, BufferedReader br, PrintWriter pw, int playerCount, int gameSize) throws IOException {
        this.game = new GameThread(socket, br, pw, playerCount, gameSize);
        game.start();
    }

    public void processJoiner(Socket socket, BufferedReader br, PrintWriter pw) throws IOException {
        game.addPlayer(socket, br, pw);
    }
}
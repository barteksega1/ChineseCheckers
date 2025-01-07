package checkers.Server;

//mvn clean compile exec:java -Pserver

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import checkers.Board.Board;
import checkers.Client.GameClient;
import checkers.Game.GameThread;
import checkers.Message.MessageHandler;
import checkers.Player.Player;

public class GameServer {
    private final int port;
    private final ArrayList<Player> players = new ArrayList<>();
    //private final Board board;
    //private ArrayList<GameClient> clients = new ArrayList<>();
    //private ArrayList<Socket> sockets = new ArrayList<>();
    private final int playerCount;
    private int numberOfJoinedPlayers = 0;
    private final HashMap playersMap = new HashMap<>();
    private GameThread game;
    private boolean serverRunning = false;
    private boolean gamePresent = false;
    private boolean gameRunning = false;

    public GameServer(int port, int playerCount) //throws IOException {
    {   
    //super(port);
        this.playerCount = playerCount;
        this.port = port;
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
                        gameRunning = true;
                    }
                }

                else if(numberOfJoinedPlayers == 0) {
                    processFirstPlayer(socket, reader, writer, playerCount);
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

    public void processFirstPlayer(Socket socket, BufferedReader br, PrintWriter pw, int playerCount) throws IOException {
        this.game = new GameThread(socket, br, pw, playerCount);
        game.start();
        this.gamePresent = true;
    }

    public void processJoiner(Socket socket, BufferedReader br, PrintWriter pw) throws IOException {
        game.addPlayer(socket, br, pw);
    }
}

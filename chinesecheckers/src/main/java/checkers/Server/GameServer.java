package checkers.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import checkers.Game.GameThread;

/**
 * Represents the game server that handles player connections and game initialization.
 */
public class GameServer {
    private final int port;
    private final int playerCount;
    private final int gameSize;
    private int numberOfJoinedPlayers = 0;
    private GameThread game;
    private boolean serverRunning = false;
    private boolean gameRunning = false;

    /**
     * Constructs a GameServer with the specified port, player count, and game size.
     *
     * @param port the port number for the server
     * @param playerCount the number of players
     * @param gameSize the size of the game
     */
    public GameServer(int port, int playerCount, int gameSize) {
        this.playerCount = playerCount;
        this.port = port;
        this.gameSize = gameSize;
    }

    /**
     * Starts the game server and listens for player connections.
     *
     * @throws IOException if an I/O error occurs when creating the server socket
     */
    public void start() throws IOException {
        try (ServerSocket serverSocket = createServerSocket()) {
            System.out.println("Serwer uruchomiony na porcie " + port);
            serverRunning = true;
            while (serverRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowy gracz dołączył: " + socket.getInetAddress() + "\n");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                if (numberOfJoinedPlayers < playerCount) {
                    if (numberOfJoinedPlayers == 0) {
                        processFirstPlayer(socket, reader, writer, playerCount, gameSize);
                        System.out.println("processed first player");
                    } else {
                        processJoiner(socket, reader, writer);
                        System.out.println("processed more players");
                    }
                    numberOfJoinedPlayers++;
                    if (numberOfJoinedPlayers == playerCount) {
                        game.getCommunicationDevice().sendMessageToAllPlayers("Wszyscy gracze połączeni. Gra się rozpoczyna!");
                        game.getCommunicationDevice().sendMessageToAllPlayers("Player Count is: " + playerCount);
                        game.getCommunicationDevice().sendMessageToAllPlayers("Game Size is: " + gameSize);
                        gameRunning = true;
                    }
                } else if (gameRunning) {
                    writer.write("Sorry, the game is already running");
                }
            }
        }
    }

    /**
     * Creates a server socket on the specified port.
     *
     * @return the created ServerSocket
     * @throws IOException if an I/O error occurs when creating the server socket
     */
    protected ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(8080); // default port
    }

    /**
     * Processes the first player connection and initializes the game.
     *
     * @param socket the socket of the player
     * @param br the BufferedReader for the player
     * @param pw the PrintWriter for the player
     * @param playerCount the number of players
     * @param gameSize the size of the game
     * @throws IOException if an I/O error occurs
     */
    public void processFirstPlayer(Socket socket, BufferedReader br, PrintWriter pw, int playerCount, int gameSize) throws IOException {
        this.game = new GameThread(socket, br, pw, playerCount, gameSize);
        game.start();
    }

    /**
     * Processes additional player connections.
     *
     * @param socket the socket of the player
     * @param br the BufferedReader for the player
     * @param pw the PrintWriter for the player
     * @throws IOException if an I/O error occurs
     */
    public void processJoiner(Socket socket, BufferedReader br, PrintWriter pw) throws IOException {
        game.addPlayer(socket, br, pw);
    }
}
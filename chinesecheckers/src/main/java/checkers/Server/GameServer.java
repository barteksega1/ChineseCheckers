package checkers.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import checkers.Board.Board;
import checkers.Message.MessageHandler;
import checkers.Player.Player;

public class GameServer {
    private final int port;
    public final List<Player> players = new ArrayList<>();
    private final Board board = new Board(5);
    private final List<PrintWriter> clientWriters = new ArrayList<>();
    private final int maxPlayers;

    public GameServer(int port) {
        this(port, 6); // Default to 6 players
    }

    public GameServer(int port, int maxPlayers) {
        this.port = port;
        this.maxPlayers = maxPlayers;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = createServerSocket()) {
            System.out.println("Serwer uruchomiony na porcie " + port);

            while (players.size() < maxPlayers) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowy gracz dołączył: " + socket.getInetAddress());
                Player player = new Player("Player" + (players.size() + 1), (char) ('A' + players.size()));
                players.add(player);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(out);

                new Thread(new ClientHandler(socket, player, out)).start();
            }
        }
        System.out.println("Wszyscy gracze połączeni. Gra się rozpoczyna!");
    }

    protected ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(port);
    }

    public List<PrintWriter> getClientWriters() {
        return clientWriters;
    }

    public synchronized void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }


    private class ClientHandler implements Runnable {
        private final Socket socket;
        private final Player player;
        private final PrintWriter out;

        public ClientHandler(Socket socket, Player player, PrintWriter out) {
            this.socket = socket;
            this.player = player;
            this.out = out;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                out.println("Witaj, " + player.getId() + "! Twój symbol to: " + player.getSymbol());
                board.printBoard();

                String input;
                while ((input = in.readLine()) != null) {
                    String response = MessageHandler.handle(input);
                    broadcast(response);
                }
            } catch (IOException e) {
                System.err.println("Błąd obsługi klienta: " + e.getMessage());
            }
        }

        private void broadcast(String message) {
            synchronized (GameServer.this) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }

    public static void main(String[] args) {
        GameServer server = new GameServer(12345);
        try {
            server.start();
        } catch(IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
        
    }
}

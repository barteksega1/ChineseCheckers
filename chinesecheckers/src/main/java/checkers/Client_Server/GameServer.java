package checkers.Client_Server;

import java.io.*;
import java.net.*;
import java.util.*;
import checkers.Player.Player;
import checkers.Board.Board;
import checkers.Message.MessageHandler;

public class GameServer {
    private final int port;
    private final List<Player> players = new ArrayList<>();
    private final Board board = new Board();

    public GameServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serwer uruchomiony na porcie " + port);

        while (players.size() < 6) { // Dla uproszczenia: max 6 graczy
            Socket socket = serverSocket.accept();
            System.out.println("Nowy gracz dołączył: " + socket.getInetAddress());
            Player player = new Player("Player" + (players.size() + 1), (char) ('A' + players.size()));
            players.add(player);

            new Thread(new ClientHandler(socket, player)).start();
        }
        System.out.println("Wszyscy gracze połączeni. Gra się rozpoczyna!");
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private final Player player;

        public ClientHandler(Socket socket, Player player) {
            this.socket = socket;
            this.player = player;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                out.println("Witaj, " + player.getId() + "! Twój symbol to: " + player.getSymbol());
                board.display();

                String input;
                while ((input = in.readLine()) != null) {

                    // String[] parts = input.split(" ");
                    // int startX = Integer.parseInt(parts[0]);
                    // int startY = Integer.parseInt(parts[1]);
                    // int endX = Integer.parseInt(parts[2]);
                    // int endY = Integer.parseInt(parts[3]);

                    // synchronized (board) {
                    //     if (board.isValidMove(startX, startY, endX, endY)) {
                    //         board.makeMove(startX, startY, endX, endY);
                    //         out.println("Ruch wykonany!");
                    //         board.display();
                    //     } else {
                    //         out.println("Nieprawidłowy ruch!");
                    //     }
                    // } #left for the memes, to be removed

                    MessageHandler.handle(input);


                }
            } catch (IOException e) {
                System.err.println("Błąd obsługi klienta: " + e.getMessage());
            }
        }
    }
}

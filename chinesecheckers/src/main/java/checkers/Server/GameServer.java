package checkers.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import checkers.Board.Board;
import checkers.Message.MessageHandler;
import checkers.Player.Player;

public class GameServer {
    private final int port;
    private final List<Player> players = new ArrayList<>();
    private final Board board;
    private final int playerCount;
    private final HashMap playersMap = new HashMap<>();

    public GameServer(int port, int playerCount) {
        this.port = port;
        this.playerCount = playerCount;
        board = new Board(playerCount);
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = createServerSocket()) {
            System.out.println("Serwer uruchomiony na porcie " + port);

            while (players.size() < playerCount) { 
                Socket socket = serverSocket.accept();
                System.out.println("Nowy gracz dołączył: " + socket.getInetAddress() + "\n");
                Player player = new Player("Player" + (players.size() + 1), (char) ('A' + players.size()));
                players.add(player);
                playersMap.put(player.getId(), socket);

                new Thread(new ClientHandler(socket, player)).start();
            }
        } 
        System.out.println("Wszyscy gracze połączeni. Gra się rozpoczyna!");
    }

    protected ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(port);
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
                //out.println(board.printBoard()); printBoard will eventually return string here

                String input;
                while ((input = in.readLine()) != null) {
                    String outpuString = MessageHandler.handle(input);
                    if(outpuString != null)
                    {
                        out.println(outpuString);
                    }
                }
            } catch (IOException e) {
                System.err.println("Błąd obsługi klienta: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        int playerCountCheck = 0;
        String playerCountInput = "";
        while(playerCountCheck < 2) {
            try {
                System.out.println("Podaj liczbe graczy");
                playerCountInput = consoleInput.readLine();
                playerCountCheck = Integer.parseInt(playerCountInput);
                if(playerCountCheck > 6 || playerCountCheck < 2 || playerCountCheck == 5) {
                    throw new IllegalArgumentException("Niepoprawna liczba graczy");
                }
                GameServer server = new GameServer(12345, playerCountCheck);
                try {
                    server.start();
                } catch(IOException e) {
                    System.err.println("Server error: " + e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.err.print("Niepoprawna liczba graczy! \n" + 
                "Input: " + Integer.parseInt(playerCountInput) + "; oczekiwano: 2, 3, 4 lub 6\n");
                playerCountCheck = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
      
         
        
        
    }
}

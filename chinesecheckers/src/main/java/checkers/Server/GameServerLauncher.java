package checkers.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameServerLauncher {
        public static void main(String[] args) throws IOException {
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        int playerCountCheck = 0;
        boolean serverRunning = false;
        String playerCountInput = "";
        System.out.println("Jesteś hostem \n");
        // while (playerCountCheck < 2 && !serverRunning) {
        //     try {
        //         System.out.println("Podaj liczbe graczy: >>> ");
        //         playerCountInput = consoleInput.readLine();
        //         playerCountCheck = Integer.parseInt(playerCountInput);

        //         if (playerCountCheck > 6 || playerCountCheck < 2 || playerCountCheck == 5) {
        //             throw new IllegalArgumentException("Niepoprawna liczba graczy");
        //         }

        //         GameServer server = new GameServer(8080, playerCountCheck);
        //         serverRunning = true;

        //         try {
        //             server.start();
        //             throw new EOFException("Server is not running anymore, bye \n");
        //         } catch (EOFException e) {
        //             System.err.println(e.getMessage());
        //         } catch (IOException e) {
        //             System.err.println("server error: " + e.getMessage());
        //         }

        //     } catch (IllegalArgumentException e) {
        //         System.err.print("Niepoprawna liczba graczy! \n" +
        //                 "Input: " + playerCountInput + "; oczekiwano: 2, 3, 4 lub 6\n");
        //         playerCountCheck = 0;
        //     } catch (IOException ex) {
        //         ex.printStackTrace();
        //     }
        // }

        while(!serverRunning) {
            System.out.println("Podaj liczbe graczy: >>> ");
                playerCountInput = consoleInput.readLine();
                try {
                    playerCountCheck = Integer.parseInt(playerCountInput);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                
                try {
                    if (!(playerCountCheck > 6 || playerCountCheck < 2 || playerCountCheck == 5)) {
                        GameServer server = new GameServer(8080, playerCountCheck);
                        server.start();
                        serverRunning = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

package checkers.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import checkers.Game.CountGameSize;

/**
 * Launches the game server application.
 */
public class GameServerLauncher {

    /**
     * The main method to launch the game server.
     *
     * @param args the command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
       // SpringApplication.run(GameServerLauncher.class, args);
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        int playerCountCheck;
        int pinCountCheck;
        boolean serverRunning = false;
        String playerCountInput;
        String pinCountInput;
        System.out.println("Jesteś hostem \n");

        while (!serverRunning) {
            try {
                System.out.println("Podaj liczbe graczy: >>> ");
                playerCountInput = consoleInput.readLine();
                playerCountCheck = Integer.parseInt(playerCountInput);

                if (playerCountCheck > 6 || playerCountCheck < 2 || playerCountCheck == 5) {
                    throw new IllegalArgumentException("Niepoprawna liczba graczy");
                }

                System.out.println("Podaj liczbe pionków: >>> ");
                pinCountInput = consoleInput.readLine();
                pinCountCheck = Integer.parseInt(pinCountInput);

                if (!(pinCountCheck == 1 || pinCountCheck == 3 || pinCountCheck == 6 ||
                      pinCountCheck == 10 || pinCountCheck == 15 || pinCountCheck == 21)) {
                    throw new IllegalArgumentException("Niepoprawna liczba pionków");
                }

                CountGameSize countGameSize = new CountGameSize();
                int gameSize = countGameSize.getGameSize(pinCountCheck);

                System.out.println("Rozpoczynanie gry z " + playerCountCheck + " graczami i " + pinCountCheck + " pionkami.");
                GameServer server = new GameServer(8080, playerCountCheck, gameSize);
                server.start();
                serverRunning = true;

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage() + "\nOczekiwane wartości dla pionków: 1, 3, 6, 10, 15, 21");
            } catch (IOException ex) {
                System.err.println("Server error: " + ex.getMessage());
            }
        }
    }
}

package checkers.Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import checkers.Game.CountGameSize;
import checkers.Game.GameCredentials;

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
        int playerCountCheck = 0;
        int pinCountCheck = 0;
        boolean serverRunning = false;
        String playerCountInput;
        String pinCountInput;
        int botCount = 0;
        int numberOfHumanPlayers;
        String gameType = "";
        boolean restored = false;
        List<GameCredentials> credentials = new ArrayList<>();
        String credentialsFileName = "credentials.json";
        File credentialsFile = new File(credentialsFileName);
        final ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Jesteś hostem \n");

        while (!serverRunning) {
            try {
                System.out.println("Podaj rodzaj gry: 'last'/'new' >>> ");
                gameType = consoleInput.readLine();
                if(gameType.equals("last")) {
                    restored = true;
                }
                if(restored) {
                    if(credentialsFile.exists()) {
                        credentials = objectMapper.readValue(credentialsFile, new TypeReference<List<GameCredentials>>(){});
                        if(credentials.size() > 0) {
                            System.out.println("Ostatnia gra: ");
                            for(GameCredentials cred : credentials) {
                                playerCountCheck = cred.getPlayerCount();
                                botCount = cred.getBotCount();
                                pinCountCheck = cred.getPinCount();
                            }
                        } else {
                            System.out.println("Brak zapisanej gry");
                            continue;
                        }
                    } else {
                        System.out.println("Brak zapisanej gry");
                        continue;
                    }
                }

                else if(!restored) {
                    System.out.println("Podaj liczbe graczy: >>> ");
                playerCountInput = consoleInput.readLine();
                playerCountCheck = Integer.parseInt(playerCountInput);

                if (playerCountCheck > 6 || playerCountCheck < 2 || playerCountCheck == 5) {
                    throw new IllegalArgumentException("Niepoprawna liczba graczy");
                }

                System.out.println("Podaj liczbe botow >>> ");
                String botCountInput = consoleInput.readLine();
                botCount = Integer.parseInt(botCountInput);

                if (botCount > (playerCountCheck - 1) || botCount < 0) {
                    throw new IllegalArgumentException("Niepoprawna liczba botow");
                }

                numberOfHumanPlayers = playerCountCheck - botCount;

                System.out.println("Podaj liczbe pionków: >>> ");
                pinCountInput = consoleInput.readLine();
                pinCountCheck = Integer.parseInt(pinCountInput);

                if (!(pinCountCheck == 1 || pinCountCheck == 3 || pinCountCheck == 6 ||
                      pinCountCheck == 10 || pinCountCheck == 15 || pinCountCheck == 21)) {
                    throw new IllegalArgumentException("Niepoprawna liczba pionków");
                }

                try {
                    // Zapisujemy listę do pliku JSON (nadpisanie)
                    credentials.add(new GameCredentials(playerCountCheck, botCount, pinCountCheck));
                    objectMapper.writeValue(credentialsFile, credentials);
                    System.out.println("Plik " + credentialsFileName + " został zapisany.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                }
                
                CountGameSize countGameSize = new CountGameSize();
                int gameSize = countGameSize.getGameSize(pinCountCheck);
                System.out.println("Rozpoczynanie gry z " + playerCountCheck + " graczami i " + botCount + " botami oraz  " + pinCountCheck + " pionkami.");
                GameServer server = new GameServer(8080, playerCountCheck, botCount, gameSize);
                if(restored){
                    server.setRestored(restored);
                }
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

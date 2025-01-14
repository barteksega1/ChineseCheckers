package checkers.Client;

import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launches the game client application.
 */
public class GameClientLauncher extends Application {
    /**
     * The main method to launch the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application and initializes the game client.
     *
     * @param startStage the primary stage for this application
     * @throws IOException if an I/O error occurs when creating the socket
     */
    @Override
    public void start(Stage startStage) throws IOException {
        try {
            Socket socket = new Socket("localhost", 8080);
            GameClient client = new GameClient(socket);
            client.initialize();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}

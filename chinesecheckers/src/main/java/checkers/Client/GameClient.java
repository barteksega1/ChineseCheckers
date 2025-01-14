package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import checkers.ClientGUI.WaitingStage;
import checkers.ClientGUI.WinnerStage;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Represents the game client that communicates with the server.
 */
public class GameClient {
    private final BufferedReader br;
    private final PrintWriter pw;
    private ClientThread clientThread;
    private Stage stage;
    private int playerNumber = -1;

    /**
     * Constructs a GameClient with the specified socket.
     *
     * @param socket the socket to communicate with the server
     * @throws IOException if an I/O error occurs when creating the input or output stream
     */
    public GameClient(Socket socket) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pw = new PrintWriter(socket.getOutputStream(), true);
        this.clientThread = new ClientThread(this, br, pw);
    }

    /**
     * Initializes the client by starting the client thread.
     */
    public void initialize() {
        clientThread.start();
    }

    /**
     * Gets the input stream reader.
     *
     * @return the BufferedReader for input
     */
    public BufferedReader getInput() {
        return br;
    }

    /**
     * Gets the output stream writer.
     *
     * @return the PrintWriter for output
     */
    public PrintWriter getOutput() {
        return pw;
    }

    /**
     * Gets the client thread.
     *
     * @return the ClientThread
     */
    public ClientThread getClientThread() {
        return clientThread;
    }

    /**
     * Sets the client thread.
     *
     * @param clientThread the ClientThread to set
     */
    public void setClientThread(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    /**
     * Gets the player number.
     *
     * @return the player number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Sets the player number.
     *
     * @param playerNumber the player number to set
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    /**
     * Sets the stage for the client.
     *
     * @param stage the Stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Closes the previous stage if it exists.
     */
    public void closePreviousStage() {
        if (this.stage != null) {
            stage.close();
        }
    }

    /**
     * Shows the waiting stage.
     */
    public void showWaitingStage() {
        Platform.runLater(() -> {
            WaitingStage waitingStage = new WaitingStage(playerNumber);
            this.setStage(waitingStage);
            waitingStage.show();
        });
    }

    /**
     * Shows the winner stage.
     *
     * @param playerNum the number of the winning player
     */
    public void showWinnerStage(int playerNum) {
        Platform.runLater(() -> {
            WinnerStage winnerStage = new WinnerStage(playerNum);
            this.setStage(winnerStage);
            winnerStage.show();
        });
    }
}

package checkers.Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Manages communication between the server and players.
 */
public final class CommunicationDevice {

    private final ArrayList<Socket> playerSockets = new ArrayList<>();
    private final ArrayList<BufferedReader> playerReaders = new ArrayList<>();
    private final ArrayList<PrintWriter> playerWriters = new ArrayList<>();
    private final ArrayList<Boolean> playerConnected = new ArrayList<>();
    private int currentNumberOfPlayers = 0;

    /**
     * Sets up the communication device for the specified number of human players.
     *
     * @param numberOfHumanPlayers the number of human players
     */
    public void setUp(final int numberOfHumanPlayers) {
        // Method implementation
    }

    /**
     * Adds a player to the communication device.
     *
     * @param playerSocket the socket of the player
     * @param br the BufferedReader for the player
     * @param pw the PrintWriter for the player
     */
    public void addPlayer(Socket playerSocket, BufferedReader br, PrintWriter pw) {
        pw.println("Hello player");
        pw.println(currentNumberOfPlayers);
        playerSockets.add(playerSocket);
        playerReaders.add(br);
        playerWriters.add(pw);
        playerConnected.add(true);
        currentNumberOfPlayers++;
    }

    /**
     * Gets the input reader for the specified player number.
     *
     * @param number the player number
     * @return the BufferedReader for the player
     */
    public BufferedReader getInputReaderByNumber(int number) {
        return playerReaders.get(number);
    }

    /**
     * Gets the print writer for the specified player number.
     *
     * @param number the player number
     * @return the PrintWriter for the player
     */
    public PrintWriter getPrintWriterByNumber(int number) {
        return playerWriters.get(number);
    }

    /**
     * Gets the list of print writers for all players.
     *
     * @return the list of PrintWriters
     */
    public ArrayList<PrintWriter> getPlayerWriters() {
        return playerWriters;
    }

    /**
     * Sends a message to all players.
     *
     * @param message the message to send
     */
    public void sendMessageToAllPlayers(String message) {
        for (PrintWriter pw : playerWriters) {
            pw.println(message);
        }
    }

    /**
     * Gets the list of player connection statuses.
     *
     * @return the list of player connection statuses
     */
    public ArrayList<Boolean> getPlayerConnected() {
        return playerConnected;
    }

    /**
     * Gets the list of player sockets.
     *
     * @return the list of player sockets
     */
    public ArrayList<Socket> getPlayerSockets() {
        return playerSockets;
    }
}

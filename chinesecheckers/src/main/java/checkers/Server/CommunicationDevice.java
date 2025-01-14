package checkers.Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public final class CommunicationDevice {

    private final ArrayList<Socket> playerSockets = new ArrayList<>();
    private final ArrayList<BufferedReader> playerReaders = new ArrayList<>();
    private final ArrayList<PrintWriter> playerWriters = new ArrayList<>();
    private final ArrayList<Boolean> playerConnected = new ArrayList<>();
    private int currentNumberOfPlayers = 0;

    public void setUp(final int numberOfHumanPlayers) {
        // Method implementation
    }

    public void addPlayer(Socket playerSocket, BufferedReader br, PrintWriter pw) {
        pw.println("Hello player");
        pw.println(currentNumberOfPlayers);
        playerSockets.add(playerSocket);
        playerReaders.add(br);
        playerWriters.add(pw);
        playerConnected.add(true);
        currentNumberOfPlayers++;
    }

    public BufferedReader getInputReaderByNumber(int number) {
        return playerReaders.get(number);
    }

    public PrintWriter getPrintWriterByNumber(int number) {
        return playerWriters.get(number);
    }

    public ArrayList<PrintWriter> getPlayerWriters() {
        return playerWriters;
    }

    public void sendMessageToAllPlayers(String message) {
        for (PrintWriter pw : playerWriters) {
            pw.println(message);
        }
    }

    public ArrayList<Boolean> getPlayerConnected() {
        return playerConnected;
    }

    public ArrayList<Socket> getPlayerSockets() {
        return playerSockets;
    }
}

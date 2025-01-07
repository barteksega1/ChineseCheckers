package checkers.Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public final class CommunicationDevice {

    private int numberOfHumanPlayers;
    // private Socket[] playerSockets;
    // private BufferedReader[] playerReaders;
    // private PrintWriter[] playerWriters;
    // private boolean[] playerConnected;
        private ArrayList<Socket> playerSockets = new ArrayList<>();
        private ArrayList<BufferedReader> playerReaders = new ArrayList<>();
        private ArrayList<PrintWriter> playerWriters = new ArrayList<>();
        private ArrayList<Boolean> playerConnected = new ArrayList<>();
    int currentNumberOfPlayers = 0;

    public void setUp(final int numberOfHumanPlayers) {
        // this.playerSockets = new Socket[numberOfHumanPlayers];
        // this.playerReaders = new BufferedReader[numberOfHumanPlayers];
        // this.playerWriters = new PrintWriter[numberOfHumanPlayers];
        // this.playerConnected = new boolean[numberOfHumanPlayers];
    }

    public void addPlayer(Socket playerSocket, BufferedReader br, PrintWriter pw) {
        pw.println("Hello player");
        pw.println(currentNumberOfPlayers);
        // playerSockets[currentNumberOfPlayers] = playerSocket;
        // playerReaders[currentNumberOfPlayers] = br;
        // playerWriters[currentNumberOfPlayers] = pw;
        // playerConnected[currentNumberOfPlayers] = true;
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
    	for(PrintWriter pw: this.playerWriters) {
    		pw.println(message);
    	}
    }

}

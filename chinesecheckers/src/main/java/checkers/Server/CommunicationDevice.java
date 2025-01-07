package checkers.Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class CommunicationDevice {

    private int numberOfHumanPlayers;
    private Socket[] playerSockets;
    private BufferedReader[] playReaders;
    private PrintWriter[] playerWriters;
    private boolean[] playerConnected;
    int currentNumOfPlayers = 0;

    public void setUp(final int numberOfHumanPlayers) {
        playerSockets = new Socket[numberOfHumanPlayers];
        playerReaders = new BufferedReader[numberOfHumanPlayers];
        playerWriters = new PrintWriter[numberOfHumanPlayers];
        playerConnected = new boolean[numberOfHumanPlayers];
    }

    public void addPlayer(Socket playerSocket, BufferedReader br, PrintWriter pw) {
        pw.println("Hello player");
        pw.println(currentNumberOfPlayers);
        playerSockets[currentNumberOfPlayers] = playerSocket;
        playerInputReaders[currentNumberOfPlayers] = br;
        playerOutputWriters[currentNumberOfPlayers] = pw;
        playersConnected[currentNumberOfPlayers] = true;
        currentNumberOfPlayers++;
    }
    
    public BufferedReader getInputReaderByNumber(int number) {
        return playerReaders[number];
    }

    public PrintWriter getPrintWriterByNumber(int number) {
        return playerWriters[number];
    }
    
    public void sendMessageToAllPlayers(String message) {
    	for(PrintWriter pw: playerWriters) {
    		pw.println(message);
    	}
    }

}

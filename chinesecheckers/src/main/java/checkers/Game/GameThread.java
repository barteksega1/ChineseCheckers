package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import checkers.Message.MessageHandler;
import checkers.Server.CommunicationDevice;

public class GameThread extends Thread {
    
    private boolean started = false;
    private boolean ended = false;
    private Game game;
    //private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers = 0;
    private int numberOfHumanPlayers = 0;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayer = 0;
    private CommunicationDevice communicationDevice = new CommunicationDevice();
    private MessageHandler messageHandler = new MessageHandler();



    public GameThread(Socket firstPlayer, BufferedReader firstBufferedReader, PrintWriter firstPrintWriter, int numberOfPlayers) throws IOException {
    this.numberOfHumanPlayers = numberOfPlayers;
    this.game = new Game(numberOfHumanPlayers);
    this.communicationDevice.setUp(numberOfHumanPlayers);
    addPlayer(firstPlayer, firstBufferedReader, firstPrintWriter);
    }

    @Override
    public void run() {
        while(numberOfJoinedPlayers < numberOfHumanPlayers) {
            System.out.println("Waiting for " + (numberOfHumanPlayers - numberOfJoinedPlayers  + " more player(s)"));
            for(int i = 0; i < communicationDevice.getPlayerWriters().size(); i++)
            {
                communicationDevice.getPrintWriterByNumber(i).println("waiting for more players, you are player " + i);
                communicationDevice.getPrintWriterByNumber(i).println("Game Size is: " + numberOfHumanPlayers);
            }
            try {
                synchronized(this) {
                    wait(5000);
                    //System.out.println("waittttt \n");
                }
            }
            catch (InterruptedException ex) {};
        }
        System.out.println("\n Game is running \n");
        communicationDevice.sendMessageToAllPlayers("Game is running");
        Random random = new Random();
        
        currentPlayer = random.nextInt(numberOfJoinedPlayers);
        while(!ended)
        {
            try {
                System.out.println("current Player: " + currentPlayer);
                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Your turn player " + currentPlayer);
                String playerInput = communicationDevice.getInputReaderByNumber(currentPlayer).readLine();
                System.out.println(playerInput);
                if(playerInput.contains("slay")) {
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for you move");
                    currentPlayer = (currentPlayer + 1)%(numberOfJoinedPlayers);
                } else {
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                    currentPlayer = (currentPlayer + 1)%(numberOfJoinedPlayers);
                }
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
            
        }

    }
        
    public void addPlayer(Socket player, BufferedReader br, PrintWriter pw) throws IOException {
        communicationDevice.addPlayer(player, br, pw);
        numberOfJoinedPlayers++;
    }

    public int getNumberOfJoinedPlayers() {
        return numberOfJoinedPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfHumanPlayers() {
        return numberOfHumanPlayers;
    }

    public CommunicationDevice getCommunicationDevice() {
        return communicationDevice;
    }
}
package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import checkers.Player.Player;
import checkers.Server.CommunicationDevice;

public class GameThread extends Thread {
    
    private boolean started = false;
    private Game game;
    //private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers = 0;
    private int numberOfHumanPlayers = 0;
    private int numberOfJoinedPlayers = 0;
    private CommunicationDevice communicationDevice = new CommunicationDevice();



    public GameThread(Socket firstPlayer, BufferedReader firstBufferedReader, PrintWriter firstPrintWriter, int numberOfPlayers) throws IOException {
    this.numberOfHumanPlayers = numberOfPlayers;
    this.game = new Game(numberOfHumanPlayers);
    this.communicationDevice.setUp(numberOfHumanPlayers);
    addPlayer(firstPlayer, firstBufferedReader, firstPrintWriter);
    }

    @Override
    public void run() {
        while(numberOfJoinedPlayers < numberOfHumanPlayers) {
            System.out.println("Waiting for " + (numberOfHumanPlayers - numberOfJoinedPlayers  + " more players"));
            for(PrintWriter writer : communicationDevice.getPlayerWriters())
            {
                writer.println("waiting for more players");
            }
            try {
                synchronized(this) {
                    wait(5000);
                    System.out.println("waittttt \n");
                }
            }
            catch (InterruptedException ex) {};
        }
        System.out.println("\n Game is running \n");
        while(true)
        {
            try {
                synchronized(this) {
                wait(5000);
                System.out.println("Game is still running");
                }
            } catch (InterruptedException ex) {};
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


    }


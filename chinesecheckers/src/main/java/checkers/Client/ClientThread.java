package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import checkers.BoardGUI.BoardStage;
import checkers.Game.Game;
import javafx.application.Platform;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader br;
    private PrintWriter pw;
    private Game game;
    private int playerNumber;
    private BoardStage boardStage;
    private int playerCount;


    public ClientThread(GameClient client, BufferedReader br, PrintWriter pw) {
        this.client = client;
        this.br = br;
        this.pw = pw;
        this.hello();
    }

    public void hello() {
        System.out.println("Hello, waiting for the game to start");
    }
    
    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            //MessageHandler mh = new MessageHandler();
            System.out.println("Welcome to the game, wait for further instructions");
            while(true) {
                try {
                    String currentLine;
                    if (br.ready() && (currentLine = br.readLine()) != null) {
                        System.out.println("currentline: " + currentLine);
                        
                        if(isNumber(currentLine)) {
                            client.setPlayerNumber(Integer.parseInt(currentLine));
                            setPlayerNumber(client.getPlayerNumber());
                            client.showWaitingStage();
                        }
                        if(currentLine.contains("Game Size")) {
                            String[] splitLine = currentLine.split("\\s+");
                            System.out.println("i got this playercount : " + splitLine[splitLine.length-1]);
                            setPlayerCount(Integer.parseInt(splitLine[splitLine.length-1]));
                            System.out.println("PLAYERCOUNT: " + playerCount);
                            this.game = new Game(playerCount);
                            Platform.runLater(() -> {
                                this.boardStage = new BoardStage(this.game, this.playerNumber, this.client, this);
                            });
                            game.getPlayers();
                        }
                        else if(currentLine.contains("Game is running")) {
                            Platform.runLater(() -> {
                            client.closePreviousStage();
                            //this.boardStage = new BoardStage(this.game, this.playerNumber, this.client);
                            boardStage.show();
                        });
                        }
                        else if(!(game == null) && boardStage != null && currentLine.contains("Your turn")) {
                            Platform.runLater(() -> {
                                this.boardStage.setLabelForTurn(currentLine);
                                this.boardStage.showInputTools();
                            });
                            
                        }
                        else if(currentLine.contains("Thank you for your move")) {
                            Platform.runLater(() -> {
                                this.boardStage.hideInputTools();
                                this.boardStage.setLabelForWait();
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }
    }



      private boolean isNumber(String line) {
        try {
            Integer.parseInt(line);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public PrintWriter getPrintWriter() {
        return pw;
    }
}
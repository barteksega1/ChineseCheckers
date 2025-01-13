package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import checkers.BoardGUI.BoardStage;
import checkers.Game.Game;
import checkers.Message.MessageHandler;
import checkers.Move.MoveParser;
import javafx.application.Platform;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader br;
    private PrintWriter pw;
    private Game game;
    private int playerNumber;
    private BoardStage boardStage;
    private int playerCount;
    private boolean wasInputSent;
    private boolean turnEnded = false;
    private String playerInput;
    private boolean moved;
    private String currentLine;


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
        List<Integer> moveCoordinates = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            //MessageHandler mh = new MessageHandler();
            System.out.println("Welcome to the game, wait for further instructions");
            while(true) {
                try {
                    
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
                            boardStage.show();
                        });
                        }
                        else if(!(game == null) && boardStage != null && currentLine.contains("Your turn")) {
                            playerInput = null;
                            Platform.runLater(() -> {
                                this.boardStage.setLabelForTurn(currentLine);
                                this.boardStage.showInputTools();
                                //this.boardStage.clearLabel(this.boardStage.getOutputLabel());
                            });
                            while(playerInput == null) {
                                try {
                                    synchronized(this) {
                                        wait(10);
                                        //System.out.println("waittttt \n");
                                    }
                                }
                                catch (InterruptedException ex) {};
                            } 
                            // if(playerInput != null) {
                            //     pw.println(playerInput);
                            // }
                            pw.println(playerInput);
                            playerInput = null;
                        } 
                        else if(currentLine.contains("pass")) {
                            System.out.println(currentLine);
                            Platform.runLater(() -> {
                                this.boardStage.setLabelForWait(currentLine);
                                this.boardStage.hideInputTools();
                                this.boardStage.clearLabel(this.boardStage.getOutputLabel());
                            });
                        } 
                        else if(currentLine.contains("moved")) {
                            String[] splitLine = currentLine.split("\\s+");
                            Integer movingPlayer = Integer.parseInt(splitLine[1]);
                            String moveInputLine = "";
                            for(int i = 2; i < splitLine.length; i++) {
                                moveInputLine += splitLine[i];
                                moveInputLine += " ";
                            }
                            String[] moveInput = MessageHandler.handle(moveInputLine);
                            if(moveInput[0].equals("error")) {
                            System.out.println("messagehan error");
                            }
                            int[] moveCooridnates = new int[moveInput.length - 1];
                            moveCooridnates = MoveParser.parseMove(moveInput);

                            //just move, validation was done by the server ->>>>> move(movingPlayer, moveCoordinates);
                        }
                        else if(currentLine.contains("Thank you for your move")) {
                            System.out.println(currentLine);
                            Platform.runLater(() -> {
                                this.boardStage.setLabelForWait(currentLine);
                                this.boardStage.hideInputTools();
                                this.boardStage.clearLabel(this.boardStage.getOutputLabel());
                            });

                        }
                        else if(currentLine.contains("Sorry")) {
                            System.out.println(currentLine);
                            Platform.runLater(() -> {
                                this.boardStage.setOutputLabel("Input was incorrect, try again");
                            });
                            // while(playerInput == null) {
                            //     try {
                            //         synchronized(this) {
                            //             wait(1);
                            //             //System.out.println("waittttt \n");
                            //         }
                            //     }
                            //     catch (InterruptedException ex) {};
                            // } 
                            // pw.println(playerInput);
                        }
                            
                    }
                }   
                
                 catch (IOException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }
    }

    private void handlePlayerTurn() {
        playerInput = null;
        Platform.runLater(() -> {
            this.boardStage.setLabelForTurn(currentLine);
            this.boardStage.showInputTools();
            this.boardStage.clearLabel(this.boardStage.getOutputLabel());
        });
        while (playerInput == null) {
            try {
                synchronized (this) {
                    wait(1);
                }
            } catch (InterruptedException ex) {}
        }
        pw.println(playerInput);

        // Handle additional jump moves
        while (true) {
            try {
                currentLine = br.readLine();
                if (currentLine.contains("You have an additional jump move")) {
                    System.out.println(currentLine);
                    Platform.runLater(() -> {
                        this.boardStage.setLabelForTurn(currentLine);
                        this.boardStage.showInputTools();
                        this.boardStage.clearLabel(this.boardStage.getOutputLabel());
                    });
                    playerInput = null;
                    while (playerInput == null) {
                        try {
                            synchronized (this) {
                                wait(1);
                            }
                        } catch (InterruptedException ex) {}
                    }
                    pw.println(playerInput);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                e.getMessage();
                break;
            }
        }
    }

    private boolean isNumber(String line) {
        try {
            Integer.valueOf(line);
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

    public Boolean getWasInputSent() {
        return wasInputSent;
    }

    public void setWasInputSent(boolean wasInputSent) {
        this.wasInputSent = wasInputSent;
    }

    public void setTurnEnded(boolean turnEnded) {
        this.turnEnded = turnEnded;
    }
    
    public boolean getTurnEnded() {
        return this.turnEnded;
    }

    public void setPlayerInput(String playerInput) {
        this.playerInput = playerInput;
    }

    public String getPlayerInput() {
        return playerInput;
    }
}
package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import checkers.BoardGUI.BoardStage;
import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Game.Game;
import checkers.Message.MessageHandler;
import checkers.Move.MoveParser;
import javafx.application.Platform;

public final class ClientThread extends Thread {
    private final GameClient client;
    private final BufferedReader br;
    private final PrintWriter pw;
    private Game game;
    private int playerNumber;
    private BoardStage boardStage;
    private int playerCount;
    private boolean wasInputSent;
    private boolean turnEnded = false;
    private String playerInput;
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
        try {
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
                        else if(!(game == null) && boardStage != null && (currentLine.contains("Your turn")||currentLine.contains("You have an additional jump move"))) {
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
                            Integer movingPlayer = Integer.valueOf(splitLine[1]);
                            String moveInputLine = "";
                            for(int i = 2; i < splitLine.length; i++) {
                                moveInputLine += splitLine[i];
                                moveInputLine += " ";
                            }
                            String[] moveInput = MessageHandler.handle(moveInputLine);
                            if(moveInput[0].equals("error")) {
                            System.out.println("messagehan error");
                            }
                            int[] moveCoordinates = new int[moveInput.length - 1];
                            moveCoordinates = MoveParser.parseMove(moveInput);
                        
                            game.getBoard().getCell(moveCoordinates[0], moveCoordinates[1]).setColor(CellColor.NONE);
                            game.getBoard().getCell(moveCoordinates[0], moveCoordinates[1]).setStatus(CellStatus.FREE);

                            game.getBoard().getCell(moveCoordinates[2], moveCoordinates[3]).setColor(game.getPlayerByNumber(movingPlayer).getColor());
                            game.getBoard().getCell(moveCoordinates[2], moveCoordinates[3]).setStatus(CellStatus.OCCUPIED);
                            Platform.runLater(() -> {
                                this.boardStage.drawBoard(game.getBoard(), game.getBoard().getGameSize());
                            });
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
                        else if(currentLine.contains("won")) {
                            String[] splitLine = currentLine.split("\\s+");
                            int playerNum = -1;
                            try {
                                playerNum = Integer.parseInt(splitLine[1]);
                            } catch (NumberFormatException e) {
                                e.getMessage();
                            }
                            client.showWinnerStage(playerNum);
                        }
                    }  
                    
                }
                  
                
                 catch (IOException e) {
                    e.getMessage();
                }
            }
        } catch (NumberFormatException e) {
            e.getMessage();
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
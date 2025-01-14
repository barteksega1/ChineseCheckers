package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Message.MessageHandler;
import checkers.Move.MoveHandler;
import checkers.Move.MoveParser;
import checkers.Server.CommunicationDevice;

public final class GameThread extends Thread {
    
    private final boolean ended = false;
    private final Game game;
    private final int numberOfPlayers = 0;
    private int numberOfHumanPlayers = 0;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayer = 0;
    private final CommunicationDevice communicationDevice = new CommunicationDevice();
    

    public GameThread(Socket firstPlayer, BufferedReader firstBufferedReader, PrintWriter firstPrintWriter, int numberOfPlayers) throws IOException {
        this.numberOfHumanPlayers = numberOfPlayers;
        this.game = new Game(numberOfHumanPlayers);
        this.communicationDevice.setUp(numberOfHumanPlayers);
        addPlayer(firstPlayer, firstBufferedReader, firstPrintWriter);
    }

    @Override
    public void run() {
        while (numberOfJoinedPlayers < numberOfHumanPlayers) {
            System.out.println("Waiting for " + (numberOfHumanPlayers - numberOfJoinedPlayers) + " more player(s)");
            for (int i = 0; i < communicationDevice.getPlayerWriters().size(); i++) {
                communicationDevice.getPrintWriterByNumber(i).println("waiting for more players, you are player " + i);
                communicationDevice.getPrintWriterByNumber(i).println("Game Size is: " + numberOfHumanPlayers);
            }
            try {
                synchronized (this) {
                    wait(1000);
                }
            } catch (InterruptedException ex) {}
        }
        System.out.println("\n Game is running \n");
        communicationDevice.sendMessageToAllPlayers("Game is running");
        Random random = new Random();
        currentPlayer = random.nextInt(numberOfJoinedPlayers);
        while (!ended) {
            try {
                System.out.println("current Player: " + currentPlayer);
                int beginX = 0;
                int beginY = 0;
                int endX = 0;
                int endY = 0;
                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Your turn player " + currentPlayer);
                String playerInput = "";
                playerInput = communicationDevice.getInputReaderByNumber(currentPlayer).readLine();
                System.out.println(playerInput);
                if (playerInput.contains("move")) {
                    String[] moveInput = MessageHandler.handle(playerInput);
                    if (moveInput[0].equals("error")) {
                        communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                        System.out.println("Sorry, your move was incorrect " + currentPlayer);
                    } else {
                        int[] moveCoordinates = MoveParser.parseMove(moveInput);
                        try {
                            MoveHandler moveHandler = new MoveHandler();
                            String validationMessage = moveHandler.validateMove(game.getBoard(), moveCoordinates, game.getPlayerByNumber(currentPlayer));
                            if (validationMessage.equals("valid")) {
                                boolean isJumpMove = moveHandler.makeMove(game.getBoard(), moveCoordinates, game.getPlayerByNumber(currentPlayer));
                                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for your move");
                                System.out.println("Thank you for your move player " + currentPlayer);
                                beginX = moveCoordinates[0];
                                beginY = moveCoordinates[1];
                                endX = moveCoordinates[2];
                                endY = moveCoordinates[3];

                                game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                                game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);
                                game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                                game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);

                                communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                
                                // Check for additional jump moves only if the move was a jump move
                                while (isJumpMove && moveHandler.hasAdditionalJumpMoves(game.getBoard(), moveCoordinates)) {
                                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("You have an additional jump move");
                                    playerInput = communicationDevice.getInputReaderByNumber(currentPlayer).readLine();
                                    moveInput = MessageHandler.handle(playerInput);
                                    if (moveInput[0].equals("error")) {
                                        communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect" + validationMessage);
                                        System.out.println("Sorry, your move was incorrect " + currentPlayer + " " + validationMessage);
                                        break;
                                    } else {
                                        moveCoordinates = MoveParser.parseMove(moveInput);
                                        validationMessage = moveHandler.validateMove(game.getBoard(), moveCoordinates, game.getPlayerByNumber(currentPlayer));
                                        if (validationMessage.equals("valid")) {
                                            isJumpMove = moveHandler.makeMove(game.getBoard(), moveCoordinates, game.getPlayerByNumber(currentPlayer));
                                            if (!isJumpMove) {
                                                communicationDevice.getPrintWriterByNumber(currentPlayer).println("You have to make another jump move");
                                                System.out.println("Only jump moves are allowed after a jump move " + currentPlayer);
                                                isJumpMove = true;
                                                continue;
                                            }
                                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for your move");
                                            System.out.println("Thank you for your move player " + currentPlayer);
                                            endX = moveCoordinates[2];
                                            endY = moveCoordinates[3];
                                            beginX = moveCoordinates[0];
                                            beginY = moveCoordinates[1];
                                            
                                            game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                                            game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);
                                            game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                                            game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);

                                            communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                        } else {
                                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect: " + validationMessage);
                                            System.out.println("Sorry, your move was incorrect: " + validationMessage + " " + currentPlayer);
                                            break;
                                        }
                                    }
                                }


                                if(GameWon.isGameWon(game.getPlayerByNumber(currentPlayer).getPlayerCells(), game.getPlayerByNumber(currentPlayer).getColor())) {
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " won the game!");

                                }
                                else {
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                    currentPlayer = (currentPlayer + 1) % (numberOfJoinedPlayers);
                                }
                                
                            } else {
                                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect: " + validationMessage);
                                System.out.println("Sorry, your move was incorrect: " + validationMessage + " " + currentPlayer);
                            }
                        } catch (IllegalArgumentException e) {
                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect: " + e.getMessage());
                            System.out.println("Sorry, your move was incorrect: " + e.getMessage() + " " + currentPlayer);
                        }
                    }
                    
                } else if (playerInput.contains("pass")) {
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for your pass");
                    System.out.println("Thank you for your pass" + currentPlayer);
                    if(beginX != 0 && beginY != 0 && endX != 0 && endY != 0) {
                        communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                        
                        game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                        game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);

                        game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                        game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);
                        
                    }
                    currentPlayer = (currentPlayer + 1) % (numberOfJoinedPlayers);
                } else {
                    try {
                        synchronized(this) {
                        wait(1000);
                        //System.out.println("waittttt \n");
                        }
                    }
                    catch (InterruptedException ex) {};
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                    System.out.println("Sorry, your move was incorrect " + currentPlayer);
                }
            } catch (IOException e) {
                e.getMessage();
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
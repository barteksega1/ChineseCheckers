package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import checkers.Message.MessageHandler;
import checkers.Move.MoveHandler;
import checkers.Player.Player;
import checkers.Server.CommunicationDevice;

public class GameThread extends Thread {
    
    private boolean started = false;
    private boolean ended = false;
    private Game game;
    private int numberOfPlayers = 0;
    private int numberOfHumanPlayers = 0;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayer = 0;
    private CommunicationDevice communicationDevice = new CommunicationDevice();
    private MessageHandler messageHandler = new MessageHandler();
    private MoveHandler moveHandler = new MoveHandler();

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
                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Your turn player " + currentPlayer);
                String playerInput = communicationDevice.getInputReaderByNumber(currentPlayer).readLine();
                System.out.println(playerInput);
                if (playerInput.contains("move")) {
                    String[] moveInput = MessageHandler.handle(playerInput);
                    if (moveInput[0].equals("error")) {
                        communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                        System.out.println("Sorry, your move was incorrect " + currentPlayer);
                    } else {
                        int[] moveCoordinates = parseMove(moveInput);
                        Player player = Player.getPlayerByNumber(currentPlayer);

                        try {
                            String validationMessage = moveHandler.validateMove(game.getBoard(), moveCoordinates, player);
                            if (validationMessage.equals("valid")) {
                                boolean isJumpMove = moveHandler.makeMove(game.getBoard(), moveCoordinates, player);
                                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for your move");
                                System.out.println("Thank you for your move player " + currentPlayer);

                                // Check for additional jump moves only if the move was a jump move
                                while (isJumpMove && moveHandler.hasAdditionalJumpMoves(game.getBoard(), moveCoordinates)) {
                                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("You have an additional jump move");
                                    playerInput = communicationDevice.getInputReaderByNumber(currentPlayer).readLine();
                                    moveInput = MessageHandler.handle(playerInput);
                                    if (moveInput[0].equals("error")) {
                                        communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                                        System.out.println("Sorry, your move was incorrect " + currentPlayer);
                                        break;
                                    } else {
                                        moveCoordinates = parseMove(moveInput);
                                        validationMessage = moveHandler.validateMove(game.getBoard(), moveCoordinates, player);
                                        if (validationMessage.equals("valid")) {
                                            isJumpMove = moveHandler.makeMove(game.getBoard(), moveCoordinates, player);
                                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Thank you for your move");
                                            System.out.println("Thank you for your move player " + currentPlayer);
                                        } else {
                                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect: " + validationMessage);
                                            System.out.println("Sorry, your move was incorrect: " + validationMessage + " " + currentPlayer);
                                            break;
                                        }
                                    }
                                }

                                currentPlayer = (currentPlayer + 1) % (numberOfJoinedPlayers);
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
                    currentPlayer = (currentPlayer + 1) % (numberOfJoinedPlayers);
                } else {
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                    System.out.println("Sorry, your move was incorrect " + currentPlayer);
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

    public int[] parseMove(String[] moveString) {
        int[] parMove = new int[moveString.length - 1];
        for (int i = 1; i < moveString.length; i++) {
            try {
                parMove[i - 1] = Integer.parseInt(moveString[i]);
            } catch (Exception e) {}
        }
        return parMove;
    }
}
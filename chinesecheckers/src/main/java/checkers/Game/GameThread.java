package checkers.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import checkers.Cell.CellColor;
import checkers.Cell.CellStatus;
import checkers.Message.MessageHandler;
import checkers.Move.MoveHandler;
import checkers.Move.MoveParser;
import checkers.Move.SavedMove;
import checkers.Player.BotPlayer;
import checkers.Server.CommunicationDevice;
import checkers.Spring.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

/**
 * Represents a thread that handles the game logic for a Chinese Checkers game.
 */
public final class GameThread extends Thread {
    private final boolean ended = false;
    private boolean restored = false;
    private final Game game;
    private final int numberOfPlayers;
    private final int numberOfHumanPlayers;
    private int numberOfJoinedPlayers = 0;
    private int currentPlayer = 0;
    private final int botCount;
    private final CommunicationDevice communicationDevice = new CommunicationDevice();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final int gameSize;
    List<SavedMove> savedMoves = new ArrayList<>();
    List<GameCredentials> credentials = new ArrayList<>();
    String movesFileName = "last_game.json";
    String credentialsFileName = "credentials.json";
    File movesFile = new File(movesFileName);
    File credentialsFile = new File(credentialsFileName);

    /**
     * Constructs a GameThread with the specified parameters.
     *
     * @param firstPlayer the socket of the first player
     * @param firstBufferedReader the BufferedReader for the first player
     * @param firstPrintWriter the PrintWriter for the first player
     * @param numberOfPlayers the number of human players in the game
     * @param gameSize the size of the game - how many cells are in the longest row in the arm of the star
     * @throws IOException if an I/O error occurs
     */
    public GameThread(Socket firstPlayer, BufferedReader firstBufferedReader, PrintWriter firstPrintWriter, int numberOfPlayers, int botCount, int gameSize) throws IOException {
        this.numberOfPlayers = numberOfPlayers;
        this.gameSize = gameSize;
        this.botCount = botCount;
        this.numberOfHumanPlayers = (this.numberOfPlayers - this.botCount);
        this.game = new Game(numberOfHumanPlayers, botCount, gameSize);
        this.communicationDevice.setUp(numberOfHumanPlayers);
        addPlayer(firstPlayer, firstBufferedReader, firstPrintWriter);
    }

    /**
     * Runs the game thread, handling the game logic and player interactions.
     */
    @Override
    public void run() {
        while (numberOfJoinedPlayers < numberOfPlayers - botCount) {
            System.out.println("Waiting for " + (numberOfPlayers - numberOfJoinedPlayers - botCount) + " more player(s)");
            for (int i = 0; i < communicationDevice.getPlayerWriters().size(); i++) {
                communicationDevice.getPrintWriterByNumber(i).println("waiting for more players, you are player " + i);
                communicationDevice.getPrintWriterByNumber(i).println("Human Player Count is: " + numberOfHumanPlayers);
                communicationDevice.getPrintWriterByNumber(i).println("Bot Count is: " + botCount);
                communicationDevice.getPrintWriterByNumber(i).println("Game Size is: " + gameSize);
            }
            try {
                synchronized (this) {
                    wait(1000);
                }
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("\n Game is running \n");
        communicationDevice.sendMessageToAllPlayers("Game is running");
        Random random = new Random();
        currentPlayer = random.nextInt(numberOfJoinedPlayers);
        if(restored) {
            if (movesFile.exists()) {
                try {
                    savedMoves = objectMapper.readValue(movesFile, new TypeReference<List<SavedMove>>() {});
                    System.out.println("Wczytano istniejące ruchy z pliku.");
                } catch (IOException e) {
                    System.out.println("Błąd odczytu pliku JSON: " + e.getMessage());
                }
            } else {
                System.out.println("Plik nie istnieje. Zostanie utworzony nowy.");
            }
            for(SavedMove move : savedMoves) {
                currentPlayer = move.getPlayerNumber();
                int beginX = move.getStartX();
                int beginY = move.getStartY();
                int endX = move.getEndX();
                int endY = move.getEndY();
                game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);
                game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);
                communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                currentPlayer = (currentPlayer + 1) % (numberOfPlayers);
            }
        }
        while (!ended) {
            try {
                System.out.println("current Player: " + currentPlayer);
                int beginX = 0;
                int beginY = 0;
                int endX = 0;
                int endY = 0;
                if(game.getPlayerByNumber(currentPlayer).isBot()) {
                    BotPlayer botPlayer = (BotPlayer) game.getPlayerByNumber(currentPlayer);
                    MoveHandler moveHandler = new MoveHandler();
                    int[] botMove = botPlayer.makeMove(game.getBoard(), moveHandler);
                    if(botMove == null) {
                        communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " passed");
                        currentPlayer = (currentPlayer + 1) % (numberOfPlayers);
                        continue;
                    }
                    beginX = botMove[0];
                    beginY = botMove[1];
                    endX = botMove[2];
                    endY = botMove[3];
                    game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                    game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);
                    game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                    game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);
                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                    savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                    try {
                        // Zapisujemy listę do pliku JSON (nadpisanie)
                        objectMapper.writeValue(movesFile, savedMoves);
                        System.out.println("Plik " + movesFileName + " został zapisany.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    currentPlayer = (currentPlayer + 1) % (numberOfPlayers);
                    continue;
                }
                communicationDevice.getPrintWriterByNumber(currentPlayer).println("Your turn player " + currentPlayer);
                String playerInput;
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
                            if (validationMessage.equals("valid") && (game.getBoard().getCell(moveCoordinates[0], moveCoordinates[1]).getColor() == game.getPlayerByNumber(currentPlayer).getColor())) {
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
                                
                                savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                                    try {
                                        // Zapisujemy listę do pliku JSON (nadpisanie)
                                        objectMapper.writeValue(movesFile, savedMoves);
                                        System.out.println("Plik " + movesFileName + " został zapisany.");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

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
                                        if (validationMessage.equals("valid") && (moveCoordinates[0] == endX && moveCoordinates[1] == endY)) {
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
                                            savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                                                try {
                                                    // Zapisujemy listę do pliku JSON (nadpisanie)
                                                    objectMapper.writeValue(movesFile, savedMoves);
                                                    System.out.println("Plik " + movesFileName + " został zapisany.");
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                        } else {
                                            communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect: " + validationMessage);
                                            System.out.println("Sorry, your move was incorrect: " + validationMessage + " " + currentPlayer);
                                            break;
                                        }
                                    }
                                }

                                if (GameWon.isGameWon(game.getPlayerByNumber(currentPlayer).getPlayerCells(), game.getPlayerByNumber(currentPlayer).getColor())) {
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                    savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                                    try {
                                        // Zapisujemy listę do pliku JSON (nadpisanie)
                                        objectMapper.writeValue(movesFile, savedMoves);
                                        System.out.println("Plik " + movesFileName + " został zapisany.");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " won the game!");
                                } else {
                                    communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                                    savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                                    try {
                                        // Zapisujemy listę do pliku JSON (nadpisanie)
                                        objectMapper.writeValue(movesFile, savedMoves);
                                        System.out.println("Plik " + movesFileName + " został zapisany.");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    currentPlayer = (currentPlayer + 1) % (numberOfPlayers);
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
                    if (beginX != 0 && beginY != 0 && endX != 0 && endY != 0) {
                        communicationDevice.sendMessageToAllPlayers("Player " + currentPlayer + " moved " + beginX + " " + beginY + " " + endX + " " + endY);
                        savedMoves.add(new SavedMove(currentPlayer, beginX, beginY, endX, endY));
                        try {
                            // Zapisujemy listę do pliku JSON (nadpisanie)
                            objectMapper.writeValue(movesFile, savedMoves);
                            System.out.println("Plik " + movesFileName + " został zapisany.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        game.getBoard().getCell(beginX, beginY).setColor(CellColor.NONE);
                        game.getBoard().getCell(beginX, beginY).setStatus(CellStatus.FREE);

                        game.getBoard().getCell(endX, endY).setColor(game.getPlayerByNumber(currentPlayer).getColor());
                        game.getBoard().getCell(endX, endY).setStatus(CellStatus.OCCUPIED);
                    }
                    currentPlayer = (currentPlayer + 1) % (numberOfPlayers);
                } else {
                    try {
                        synchronized (this) {
                            wait(1000);
                        }
                    } catch (InterruptedException ex) {
                    }
                    communicationDevice.getPrintWriterByNumber(currentPlayer).println("Sorry, your move was incorrect");
                    System.out.println("Sorry, your move was incorrect " + currentPlayer);
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Adds a player to the game.
     *
     * @param player the socket of the player to add
     * @param br the BufferedReader for the player
     * @param pw the PrintWriter for the player
     * @throws IOException if an I/O error occurs
     */
    public void addPlayer(Socket player, BufferedReader br, PrintWriter pw) throws IOException {
        communicationDevice.addPlayer(player, br, pw);
        numberOfJoinedPlayers++;
    }

    /**
     * Returns the number of players who have joined the game.
     *
     * @return the number of players who have joined the game
     */
    public int getNumberOfJoinedPlayers() {
        return numberOfJoinedPlayers;
    }

    /**
     * Returns the total number of players in the game.
     *
     * @return the total number of players in the game
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Returns the number of human players in the game.
     *
     * @return the number of human players in the game
     */
    public int getNumberOfHumanPlayers() {
        return numberOfHumanPlayers;
    }

    /**
     * Returns the communication device used for player interactions.
     *
     * @return the communication device used for player interactions
     */
    public CommunicationDevice getCommunicationDevice() {
        return communicationDevice;
    }

    public boolean getRestored() {
        return restored;
    }

    public void setRestored(boolean restored) {
        this.restored = restored;
    }
}
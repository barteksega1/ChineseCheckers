package checkers.BoardGUI;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Client.ClientThread;
import checkers.Client.GameClient;
import checkers.Game.Game;
import checkers.Player.Player;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Represents the stage for the game board.
 */
public final class BoardStage extends Stage {
    private final Player player;
    private TextField inputTextField;
    private final Label outputLabel;
    private final Label turnLabel;
    private final Label moveLabel;
    private final Label colorLabel;
    private GridPane mainPane;
    private final Button sendButton;
    private String input;

    /**
     * Constructs a BoardStage with the specified game, player number, client, and client thread.
     *
     * @param game the game instance
     * @param numberOfPlayer the number of the player
     * @param client the game client
     * @param clientThread the client thread
     */
    public BoardStage(Game game, int numberOfPlayer, GameClient client, ClientThread clientThread) {
        this.setResizable(false);
        this.player = game.getPlayerByNumber(numberOfPlayer);
        this.colorLabel = new Label("You are " + player.getColor().toString() + ".");
        this.turnLabel = new Label("Wait for your turn...");
        this.moveLabel = new Label("Write here: ");
        this.inputTextField = new TextField();
        this.outputLabel = new Label("");
        this.sendButton = new Button("Send");
        drawBoard(game.getBoard(), game.getBoard().getGameSize());
        sendButton.setOnAction(e -> {
            input = inputTextField.getText();
            inputTextField.clear();
            System.out.println("button clicked: " + input);
            if (input != null) {
                clientThread.setPlayerInput(input);
                clearLabel(outputLabel);
                this.input = null;
            }
        });
    }

    /**
     * Draws the game board with the specified board and game size.
     *
     * @param board the board to draw
     * @param gameSize the size of the game - how many cells are in the longest row in the arm of the star
     */
    public void drawBoard(Board board, int gameSize) {
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;

        double stageWidth = 800 + (gameSize * 100);
        double stageHeight = 600 + (gameSize * 70);
        double margin = 50; // Add a margin for padding
        double cellSize = Math.min((stageWidth - margin) / columns, (stageHeight - margin) / rows) - 5; // Calculate cell size

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = board.getCell(row, column);
                if (cell.getStatus() != CellStatus.ILLEGAL) {
                    BoardField boardField = new BoardField(this, cell, row, column, cellSize);
                    gridPane.add(boardField, column, row);
                }
            }
        }

        Group group = new Group();
        group.getChildren().add(gridPane);

        this.mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(5);
        mainPane.setVgap(5);
        mainPane.add(colorLabel, 0, 0);
        mainPane.add(turnLabel, 1, 0);
        mainPane.add(moveLabel, 3, 0);
        mainPane.add(this.outputLabel, 4, 0);
        mainPane.add(this.inputTextField, 5, 0);
        mainPane.add(this.sendButton, 6, 0);
        mainPane.add(group, 0, 1, 2, 1);

        Scene scene = new Scene(mainPane, stageWidth, stageHeight); // Adjust the scene size dynamically
        this.hideInputTools();
        this.setScene(scene);
    }

    /**
     * Sets the label for the current turn.
     *
     * @param turnString the string to set for the turn label
     */
    public void setLabelForTurn(String turnString) {
        this.turnLabel.setText(turnString);
    }

    /**
     * Sets the label to indicate waiting for the player's turn.
     *
     * @param message the message to display
     */
    public void setLabelForWait(String message) {
        this.turnLabel.setText(message + " -- Wait for your turn...");
    }

    /**
     * Gets the output label.
     *
     * @return the output label
     */
    public Label getOutputLabel() {
        return outputLabel;
    }

    /**
     * Sets the output label with the specified message.
     *
     * @param messageString the message to set
     */
    public void setOutputLabel(String messageString) {
        this.outputLabel.setText(messageString);
    }

    /**
     * Clears the input text field.
     */
    public void clearInput() {
        this.inputTextField.clear();
    }

    /**
     * Gets the input from the text field.
     *
     * @return the input string
     */
    public String getInput() {
        return input;
    }

    /**
     * Gets the input text field.
     *
     * @return the input text field
     */
    public TextField getInputTextField() {
        return inputTextField;
    }

    /**
     * Shows the input tools (move label, input text field, output label, and send button).
     */
    public void showInputTools() {
        moveLabel.setVisible(true);
        inputTextField.setVisible(true);
        outputLabel.setVisible(true);
        sendButton.setVisible(true);
    }

    /**
     * Hides the input tools (move label, input text field, output label, and send button).
     */
    public void hideInputTools() {
        moveLabel.setVisible(false);
        inputTextField.setVisible(false);
        outputLabel.setVisible(false);
        sendButton.setVisible(false);
    }

    /**
     * Clears the text of the specified label.
     *
     * @param label the label to clear
     */
    public void clearLabel(Label label) {
        label.setText("");
    }
}

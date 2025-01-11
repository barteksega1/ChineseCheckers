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

public class BoardStage extends Stage {
    private Game game;
    private boolean active;
    private final Player player;
    private GameClient client;
    private TextField inputTextField;
    private Label outputLabel;
    private Label turnLabel;
    private Label moveLabel;
    private final Label colorLabel;
    private GridPane mainPane;
    private Button sendButton;
    private String input;
    private ClientThread clientThread;

    

    public BoardStage(Game game, int numberOfPlayer, GameClient client, ClientThread clientThread) {
        this.game = game;
        this.clientThread = clientThread;
        this.setResizable(false);
        this.player = game.getPlayerByNumber(numberOfPlayer);
        this.colorLabel = new Label("You are " + player.getColor().toString() + "." );
        this.client = client;
        this.active = false;
        this.turnLabel = new Label("Wait for your turn...");
        this.moveLabel = new Label("Write here: ");
        this.inputTextField = new TextField();
        this.outputLabel = new Label("");
        this.sendButton = new Button("Send");
        drawBoard(game.getBoard(), game.getBoard().getGameSize());
        sendButton.setOnAction( e -> {
            input = inputTextField.getText();
            inputTextField.clear();
            System.out.println("button clicked: " + input);
            this.clientThread.getPrintWriter().println(input);
        });
    }

    private void drawBoard(Board board, int gameSize) {
        int columns = gameSize * 3 + 4;
        int rows = gameSize * 2 + 3;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = board.getCell(row, column);
                if (cell.getStatus() != CellStatus.ILLEGAL) {
                    BoardField boardField = new BoardField(this, cell);
                    boardField.setRadius(10); // Ustawienie odpowiedniego rozmiaru pola
                    gridPane.add(boardField, column, row);
                }
            }
        }

        Group group = new Group();
        group.getChildren().add(gridPane);

        this.mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.add(colorLabel, 0, 0);
        mainPane.add(turnLabel, 1, 0);
        mainPane.add(moveLabel, 3, 0);
        mainPane.add(this.outputLabel, 4, 0);
        mainPane.add(this.inputTextField, 5, 0);
        mainPane.add(this.sendButton, 6, 0);
        mainPane.add(group, 0, 1, 2, 1);

        Scene scene = new Scene(mainPane, 800, 600); // Ustawienie odpowiedniego rozmiaru sceny
        this.setScene(scene);
    }



    public void setLabelForTurn(String turnString) {
        this.turnLabel.setText(turnString);
    }

    public void setLabelForWait() {
        this.turnLabel.setText("Wait for your turn...");
    }

    public void setOutputLabel(String messageString) {
        this.outputLabel.setText(messageString);
    }

    public void clearInput() {
        this.inputTextField.clear();
    }

    public String getInput() {
        return this.inputTextField.getText();
    }

    public TextField getInputTextField() {
        return inputTextField;
    }

    public void showInputTools() {
       moveLabel.setVisible(true);
       inputTextField.setVisible(true);
       outputLabel.setVisible(true);
       sendButton.setVisible(true);
    }

    public void hideInputTools() {
       moveLabel.setVisible(false);
       inputTextField.setVisible(false);
       outputLabel.setVisible(false);
       sendButton.setVisible(false);
    }
    


}

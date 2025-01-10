package checkers.BoardGUI;

import checkers.Board.Board;
import checkers.Cell.Cell;
import checkers.Cell.CellStatus;
import checkers.Client.GameClient;
import checkers.Game.Game;
import checkers.Player.Player;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoardStage extends Stage {
    private Game game;
    private boolean active;
    private final Player player;
    private GameClient client;
    private final Label turnLabel;
    private final Label colorLabel;

    public BoardStage(Game game, int numberOfPlayer, GameClient client) {
        this.game = game;
        this.setResizable(false);
        this.player = game.getPlayerByNumber(numberOfPlayer);
        this.colorLabel = new Label("You are " + player.getColor().toString() + ".");
        this.client = client;
        this.active = false;
        this.turnLabel = new Label("Wait for your turn...");
        drawBoard(game.getBoard(), game.getBoard().getGameSize());
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
                    gridPane.add(boardField, column, row);
                }
            }
        }

        Group group = new Group();
        group.getChildren().add(gridPane);

        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.add(colorLabel, 0, 0);
        mainPane.add(turnLabel, 1, 0);
        mainPane.add(group, 0, 1, 2, 1);

        Scene scene = new Scene(mainPane, mainPane.prefWidth(0) * 2, mainPane.prefHeight(0));
        this.setScene(scene);
    }
}

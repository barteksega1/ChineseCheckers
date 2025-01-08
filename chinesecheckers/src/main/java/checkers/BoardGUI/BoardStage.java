package checkers.BoardGUI;

import java.util.ArrayList;

import checkers.Client.GameClient;
import checkers.Game.Game;
import checkers.Game.GameThread;
import checkers.Player.Player;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoardStage extends Stage {
 
    private Game game;
    private boolean active;
    private Player player;
    private GameClient client;
    private Label turnLabel;
    private Label colorLabel;

    public BoardStage(Game game, int numberOfPlayer, GameClient client) {
        this.game = game;
		this.setResizable(false);
		this.player = game.getPlayerByNumber(numberOfPlayer);
		this.colorLabel = new Label("You are " + player.getColor().toString() + ".");
		this.client = client;
		this.active = false;
		this.turnLabel = new Label("Wait for you turn...");
    }
    

    private void drawBoard() {
        Group group = new Group();
		
        GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.add(colorLabel, 0, 0);
		grid.add(turnLabel, 1, 0);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.add(group, 0, 0);
		gridPane.add(grid, 0, 1);
        Scene scene = new Scene(gridPane,gridPane.prefWidth(0) * 2, gridPane.prefHeight(0));
		this.setScene(scene);
    }
}

package checkers.ClientGUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
    

public class WaitingStage extends Stage {
	private int playerNumber;
	public WaitingStage(int playerNumber) {
		this.playerNumber = playerNumber;
		Label label = new Label("Waiting for more players to join... \n You are Player " + playerNumber);
		this.setTitle("Chinese checkers");
		label.setFont(new Font(15));
		StackPane root = new StackPane();
        root.getChildren().add(label);
        Scene scene = new Scene(root, 400, 300);
        this.setScene(scene);
	}
}
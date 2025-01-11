package checkers.ClientGUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
    

public abstract class InfoStage extends Stage {
	private int playerNumber;
	private Label label;
	public InfoStage(int playerNumber) {
		this.playerNumber = playerNumber;
	 	label = new Label("");
		this.setTitle("Chinese checkers");
		label.setFont(new Font(15));
		StackPane root = new StackPane();
        root.getChildren().add(label);
        Scene scene = new Scene(root, 400, 300);
        this.setScene(scene);
	}

	public void setLabel(String messageString) {
		this.label.setText(messageString);
	}
}
package checkers.ClientGUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents an informational stage with a label.
 */
public abstract class InfoStage extends Stage {
    private final Label label;

    /**
     * Constructs an InfoStage.
     */
    public InfoStage() {
        label = new Label("");
        this.setTitle("Chinese checkers");
        label.setFont(new Font(15));
        StackPane root = new StackPane();
        root.getChildren().add(label);
        Scene scene = new Scene(root, 400, 300);
        this.setScene(scene);
    }

    /**
     * Sets the label with the specified message.
     *
     * @param messageString the message to set
     */
    public void setLabel(String messageString) {
        this.label.setText(messageString);
    }
}
package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import checkers.ClientGUI.WaitingStage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameClientLauncher extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage startStage) throws IOException {
        try {
            Socket socket = new Socket("localhost", 8080);
             
             GameClient client = new GameClient(socket); // Tworzymy klienta
            //  if(client.getPlayerNumber() != -1)
            //  {
            //     Platform.runLater(() -> {
            //         WaitingStage waitingStage = new WaitingStage();
            //         client.setStage(waitingStage);
            //         waitingStage.show();
            //     });
            //  }
             
        } catch (Exception e) {
            System.err.println("Błąd klienta: " + e.getMessage());
        }
    }

}

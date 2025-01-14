package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import checkers.ClientGUI.WaitingStage;
import checkers.ClientGUI.WinnerStage;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameClient {
    private final BufferedReader br;
    private final PrintWriter pw;
    private ClientThread clientThread;
    private Stage stage;
    private int playerNumber = -1;
    //klient ktory bedzie gui
    
    public GameClient(Socket socket) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pw = new PrintWriter(socket.getOutputStream(), true);
        this.clientThread = new ClientThread(this, br, pw);
        clientThread.start();
    }

    


    //gui stuff
    // public Stage getStage() {
    //     return stage;
    // }

    // public void setStage(Stage stage) {
    //     this.stage = stage;
    // }

    // public void closePreviousStage() {
	// 	if(this.stage != null)
	// 		stage.close();
	// }

    public BufferedReader getInput() {
        return br;
    }

    public PrintWriter getOutput() {
        return pw;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void setClientThread(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    // public GameThread getGameThread() {
    //     return gameThread;
    // }

    // public void setGameThread(GameThread gameThread) {
    //         this.gameThread = gameThread; // Przypisanie GameThread
    //         clientThread.setGame(gameThread);
    //     }

    public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void closePreviousStage() {
		if(this.stage != null)
			stage.close();
	}

    public void showWaitingStage() {
        Platform.runLater(() -> {
                    WaitingStage waitingStage = new WaitingStage(playerNumber);
                    this.setStage(waitingStage);
                    waitingStage.show();
                });
    }

    public void showWinnerStage(int playerNum) {
        Platform.runLater(() -> {
            WinnerStage winnerStage = new WinnerStage(playerNum);
            this.setStage(winnerStage);
            winnerStage.show();
        });
    }

}

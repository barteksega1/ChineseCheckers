package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import javafx.stage.Stage;

import checkers.Game.GameThread;

public class GameClient {
    private final BufferedReader in;
    private final PrintWriter out;
    //private GameThread gameThread = null;
    private ClientThread clientThread;
    //private final Object lock = new Object();
    //private Stage stage;
    //klient ktory bedzie gui
    
    public GameClient(BufferedReader in, PrintWriter out) throws InterruptedException {
        this.in = in;
        this.out = out;
        this.clientThread = new ClientThread(this, in);
        clientThread.run();
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
        return in;
    }

    public PrintWriter getOutput() {
        return out;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void setClientThread(ClientThread clientThread) {
        this.clientThread = clientThread;
    }

    // public GameThread getGameThread() {
    //     return gameThread;
    // }

    // public void setGameThread(GameThread gameThread) {
    //         this.gameThread = gameThread; // Przypisanie GameThread
    //         clientThread.setGame(gameThread);
    //     }

}

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
    private GameThread gameThread = null;
    private ClientThread clientThread;
    //private Stage stage;
    //klient ktory bedzie gui
    public GameClient(Socket socket, BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.clientThread = new ClientThread(this, in);
        clientThread.hello();
    }

    public void initializeClientThread() {
        if(this.gameThread != null) {
            clientThread.run();
        }
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

    public GameThread getGameThread() {
        return gameThread;
    }

    public void setGameThread(GameThread gameThread) {
        this.gameThread = gameThread;
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
             GameClient client = new GameClient(socket, in, out);
        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem: " + e.getMessage());
        }
    }



}


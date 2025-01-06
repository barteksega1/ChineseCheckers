package checkers.Client;

import java.io.BufferedReader;

import checkers.Game.GameThread;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader in;
    private GameThread gameThread = null;


    public ClientThread(GameClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    public void setGame(GameThread gametThread) {
        this.gameThread = gametThread;
    }
    
    public void hello() {
        System.out.println("Hello, waiting for the game to start");
    }
    
    @Override
    public void run() {
        MessageHandler mh = new MessageHandler();
        System.out.println("Welcome to the game, wait for further instructions");
        while(true) {
            try {
                String currentString;
                if(in.readLine() != null && in.ready()) {
                    currentString = in.readLine();
                    mh.handle(currentString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

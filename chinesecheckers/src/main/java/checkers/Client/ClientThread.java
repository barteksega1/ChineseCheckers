package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;

import checkers.Game.GameThread;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader br;


    public ClientThread(GameClient client, BufferedReader br) {
        this.client = client;
        this.br = br;
        this.hello();
    }

    public void hello() {
        System.out.println("Hello, waiting for the game to start");
    }
    
    @Override
    public void run() {
        //MessageHandler mh = new MessageHandler();
        System.out.println("Welcome to the game, wait for further instructions");
        while(true) {
            try {
                String currentLine;
                if (br.ready() && (currentLine = br.readLine()) != null) {
                    System.out.println(currentLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }
    
}

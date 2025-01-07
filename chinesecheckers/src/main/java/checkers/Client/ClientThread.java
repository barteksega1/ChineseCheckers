package checkers.Client;

import java.io.BufferedReader;

import checkers.Game.GameThread;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader br;
    private GameThread gameThread = null;
   // private final Object lock = new Object();


    public ClientThread(GameClient client, BufferedReader br) {
        this.client = client;
        this.br = br;
        this.hello();
    }

    public void setGame(GameThread gameThread) {
      //  synchronized (lock) {
            this.gameThread = gameThread;
            //lock.notify(); // Powiadom wątek, że gra jest gotowa
       // }
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

package checkers.Client;

import java.io.BufferedReader;

import checkers.Game.GameThread;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader in;
    private GameThread gameThread = null;
   // private final Object lock = new Object();


    public ClientThread(GameClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
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

        // try {
        //     synchronized (lock) {
        //         while (gameThread == null) {
        //             System.out.println("Czekam na przypisanie GameThread...");
        //             lock.wait(); // Czekaj na przypisanie GameThread
        //         }
        //     }
        // } catch (InterruptedException e) {
        //     System.err.println("wut? " + e.getMessage());
        // }

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

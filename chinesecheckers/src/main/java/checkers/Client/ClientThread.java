package checkers.Client;

import java.io.BufferedReader;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader in;


    public ClientThread(GameClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    @Override
    public void run() {
        MessageHandler mh = new MessageHandler();
        System.out.println("Hello Player! \n Input your command:");
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

package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import checkers.Game.GameThread;
import checkers.Message.MessageHandler;

public class ClientThread extends Thread {
    private GameClient client;
    private BufferedReader br;
    private PrintWriter pw;


    public ClientThread(GameClient client, BufferedReader br, PrintWriter pw) {
        this.client = client;
        this.br = br;
        this.pw = pw;
        this.hello();
    }

    public void hello() {
        System.out.println("Hello, waiting for the game to start");
    }
    
    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            //MessageHandler mh = new MessageHandler();
            String answer = new String();
            System.out.println("Welcome to the game, wait for further instructions");
            while(true) {
                try {
                    String currentLine;
                    if (br.ready() && (currentLine = br.readLine()) != null) {
                        System.out.println(currentLine);

                        if(currentLine.contains("Your turn")) {
                            answer = scanner.nextLine();
                            pw.println(answer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }
    }
    
}

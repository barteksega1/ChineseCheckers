package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import checkers.BoardGUI.BoardStage;
import checkers.Game.Game;
import javafx.application.Platform;

public final class ClientThread extends Thread {
    private final GameClient client;
    private final BufferedReader br;
    private final PrintWriter pw;
    private Game game;
    private int playerNumber;
    private BoardStage boardStage;


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
                        System.out.println("currentline: " + currentLine);
                        if(isNumber(currentLine)) {
                            client.setPlayerNumber(Integer.parseInt(currentLine));
                            client.showWaitingStage();
                        }
                            //dorobic czytanie liczby graczy i tworzenie gry dla kazdego klienta
                        else if(currentLine.contains("Game is running")) {
                            Platform.runLater(() -> {
                            client.closePreviousStage();
                            boardStage = new BoardStage(this.game, this.playerNumber, this.client);
                            boardStage.show();
                        });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }
    }



    private boolean isNumber(String line) {
        try {
            Integer.valueOf(line);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
}

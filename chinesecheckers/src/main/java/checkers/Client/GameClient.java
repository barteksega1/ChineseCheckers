package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private final BufferedReader in;
    private final PrintWriter out;
    private final BufferedReader console;
    //klient ktory bedzie gui
    public GameClient(Socket socket, BufferedReader in, PrintWriter out, BufferedReader console) {
        this.in = in;
        this.out = out;
        this.console = console;
    }

    public void start() {
        try {
            System.out.println("Połączono z serwerem!");
            String response;

            while ((response = in.readLine()) != null) {
                System.out.println(response);

                if (response.startsWith("Witaj")) {
                    System.out.println("Podaj komende");
                    String command = console.readLine();
                    out.println(command);
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            GameClient client = new GameClient(socket, in, out, console);
            client.start();
        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem: " + e.getMessage());
        }
    }
}


package checkers.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClientLauncher {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
             
             GameClient client = new GameClient(in, out); // Tworzymy klienta
        } catch (Exception e) {
            System.err.println("Błąd klienta: " + e.getMessage());
        }
    }
}

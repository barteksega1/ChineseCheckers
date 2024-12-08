package checkers.Client;

import java.io.*;
import java.net.*;

public class GameClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

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
}


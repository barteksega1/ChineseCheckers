package checkers.TestClient;

import checkers.Client.GameClient;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class TestGameClient {

    @Test
    public void testGameClient() throws IOException {
        Socket mockSocket = mock(Socket.class);
        BufferedReader mockIn = mock(BufferedReader.class);
        PrintWriter mockOut = mock(PrintWriter.class);
        BufferedReader mockConsole = mock(BufferedReader.class);

        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("Witaj\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        when(mockIn.readLine()).thenReturn("Witaj").thenReturn(null);
        when(mockConsole.readLine()).thenReturn("komenda");

        GameClient client = new GameClient(mockSocket, mockIn, mockOut, mockConsole);
        client.start();

        verify(mockOut).println("komenda");
    }
}

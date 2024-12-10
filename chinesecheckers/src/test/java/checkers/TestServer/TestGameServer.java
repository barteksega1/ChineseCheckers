package checkers.TestServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import checkers.Server.GameServer;

public class TestGameServer {

    @Test
    public void testGameServer() throws IOException {
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockSocket);

        GameServer server = new GameServer(12345) {
            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return mockServerSocket;
            }
        };

        Thread serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                System.err.println("IOException occurred: " + e.getMessage());
            }
        });
        serverThread.start();

        verify(mockServerSocket, timeout(1000).times(6)).accept();
    }
}

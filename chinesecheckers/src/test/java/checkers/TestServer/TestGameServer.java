package checkers.TestServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import checkers.Server.GameServer;

public class TestGameServer {
    private GameServer gameServer;
    private ServerSocket serverSocketMock;
    private Socket socketMock;
    private PrintWriter printWriterMock;

    @BeforeEach
    public void setUp() throws IOException {
        serverSocketMock = mock(ServerSocket.class);
        socketMock = mock(Socket.class);
        printWriterMock = mock(PrintWriter.class);

        gameServer = new GameServer(12345) {
            @Override
            protected ServerSocket createServerSocket() throws IOException {
                return serverSocketMock;
            }
        };

        when(serverSocketMock.accept()).thenReturn(socketMock);
        when(socketMock.getOutputStream()).thenReturn(mock(java.io.OutputStream.class));
    }

    @Test
    public void testServerStartsAndAcceptsConnections() throws IOException {
        new Thread(() -> {
            try {
                gameServer.start();
            } catch (IOException e) {
                fail("Server failed to start");
            }
        }).start();

        verify(serverSocketMock, timeout(1000).times(1)).accept();
    }

    @Test
    public void testBroadcastMessage() {
        gameServer.getClientWriters().add(printWriterMock);
        gameServer.broadcast("Test message");

        verify(printWriterMock, times(1)).println("Test message");
    }

    @Test
    public void testMaxPlayers() throws IOException {
        for (int i = 0; i < 6; i++) {
            gameServer.players.add(mock(checkers.Player.Player.class));
        }

        assertEquals(6, gameServer.players.size());
    }
}

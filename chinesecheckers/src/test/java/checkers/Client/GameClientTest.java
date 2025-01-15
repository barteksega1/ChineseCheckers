package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javafx.stage.Stage;

class GameClientTest {

    private GameClient client;
    private Socket socket;
    @SuppressWarnings("unused")
    private BufferedReader br;
    @SuppressWarnings("unused")
    private PrintWriter pw;

    @BeforeEach
    public void setUp() throws IOException {
        socket = mock(Socket.class);
        br = mock(BufferedReader.class);
        pw = mock(PrintWriter.class);
        when(socket.getInputStream()).thenReturn(System.in);
        when(socket.getOutputStream()).thenReturn(System.out);
        client = new GameClient(socket);
    }

    @Test
    void testInitialize() {
        assertNotNull(client.getClientThread());
    }

    @Test
    void testGetInput() {
        assertNotNull(client.getInput());
    }

    @Test
    void testGetOutput() {
        assertNotNull(client.getOutput());
    }

    @Test
    void testSetAndGetPlayerNumber() {
        client.setPlayerNumber(1);
        assertEquals(1, client.getPlayerNumber());
    }


    @Test
    void testClosePreviousStage() {
        Stage stage = mock(Stage.class);
        client.setStage(stage);
        client.closePreviousStage();
        verify(stage).close();
    }

}

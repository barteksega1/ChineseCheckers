package checkers.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;


class ClientThreadTest {

    private ClientThread clientThread;
    private GameClient client;
    private BufferedReader br;
    private PrintWriter pw;

    @BeforeEach
    public void setUp() throws IOException {
        client = mock(GameClient.class);
        br = mock(BufferedReader.class);
        pw = mock(PrintWriter.class);
        clientThread = new ClientThread(client, br, pw);
    }

    @Test
    void testSetAndGetTurnEnded() {
        clientThread.setTurnEnded(true);
        assertEquals(true, clientThread.getTurnEnded());
    }

    @Test
    void testSetAndGetPlayerInput() {
        clientThread.setPlayerInput("move");
        assertEquals("move", clientThread.getPlayerInput());
    }

}

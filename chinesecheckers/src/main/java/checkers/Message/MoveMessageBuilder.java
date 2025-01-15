package checkers.Message;

import java.util.Arrays;

/**
 * Builds a move message.
 */
public class MoveMessageBuilder extends MessageBuilder {

    /**
     * Constructs a MoveMessageBuilder with the specified parts.
     *
     * @param parts the parts of the message
     */
    public MoveMessageBuilder(String[] parts) {
        // Constructor implementation
    }

    /**
     * Executes the move message.
     *
     * @param parts the parts of the message
     * @return the move input parts
     */
    @Override
    public String[] executeMessage(String[] parts) {
        String[] moveInput = Arrays.copyOfRange(parts, 1, parts.length);
        return moveInput;
    }
}

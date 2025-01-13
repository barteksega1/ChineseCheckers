package checkers.Message;

import java.util.Arrays;

public class MoveMessageBuilder extends MessageBuilder {
    
    private final String[] parts;

    public MoveMessageBuilder(String[] parts) {
        this.parts = parts;
    }

    @Override
    public String[] executeMessage(String[] parts) {
        String[] moveInput = Arrays.copyOfRange(parts, 1, parts.length);
        return moveInput;
    }
}

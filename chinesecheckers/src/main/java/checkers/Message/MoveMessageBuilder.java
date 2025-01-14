package checkers.Message;

import java.util.Arrays;

public class MoveMessageBuilder extends MessageBuilder {
    

    public MoveMessageBuilder(String[] parts) {
    }

    @Override
    public String[] executeMessage(String[] parts) {
        String[] moveInput = Arrays.copyOfRange(parts, 1, parts.length);
        return moveInput;
    }
}

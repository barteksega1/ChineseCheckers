package checkers.Message;

import java.util.Arrays;

import checkers.Commands.MoveCommand;

public class MoveMessageBuilder extends MessageBuilder {
    
    @SuppressWarnings("unused")    // for now
    private final String[] parts; // final for now

    public MoveMessageBuilder(String[] parts)
    {
        this.parts = parts;
    }

    @Override public String[] executeMessage(String[] parts) {
        String[] moveInput = Arrays.copyOfRange(parts, 1, parts.length);
        MoveCommand move = new MoveCommand(moveInput);
        return moveInput;
    };

}

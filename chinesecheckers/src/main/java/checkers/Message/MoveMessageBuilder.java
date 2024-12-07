package checkers.Message;

import java.util.*;
import checkers.Commands.MoveCommand;

public class MoveMessageBuilder extends MessageBuilder {
    
    private String[] parts;

    public MoveMessageBuilder(String[] parts)
    {
        this.parts = parts;
    }

    @Override public void executeMessage(String[] parts) {
        String[] moveInput = Arrays.copyOfRange(parts, 1, parts.length);
        MoveCommand move = new MoveCommand(moveInput);
        move.command();
    };

}

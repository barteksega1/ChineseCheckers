package checkers.Message;

import java.util.Arrays;

import checkers.Commands.MoveCommand;

public class ErrorMessageBuilder extends MessageBuilder {
    
    @SuppressWarnings("unused")    // for now
    private final String[] parts; // final for now

    public ErrorMessageBuilder(String[] parts)
    {
        this.parts = parts;
    }

    @Override public String[] executeMessage(String[] parts) {
        String[] error = {"error"};
        return error;
    };

}
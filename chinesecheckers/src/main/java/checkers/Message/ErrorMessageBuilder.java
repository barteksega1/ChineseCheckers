package checkers.Message;

/**
 * Builds an error message.
 */
public class ErrorMessageBuilder extends MessageBuilder {

    @ SuppressWarnings("unused")
    private final String[] parts; 

    /**
     * Constructs an ErrorMessageBuilder with the specified parts.
     *
     * @param parts the parts of the message
     */
    public ErrorMessageBuilder(String[] parts) {
        this.parts = parts;
    }

    /**
     * Executes the error message.
     *
     * @param parts the parts of the message
     * @return an array containing "error"
     */
    @Override
    public String[] executeMessage(String[] parts) {
        String[] errorInput = {"error"}; 
        return errorInput;
    }
}


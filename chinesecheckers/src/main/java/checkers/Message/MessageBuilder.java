package checkers.Message;

/**
 * Abstract class for building messages.
 */
abstract class MessageBuilder {
    
    /**
     * Executes the message with the given parts.
     *
     * @param parts the parts of the message
     * @return the processed message parts
     */
    public abstract String[] executeMessage(String[] parts);

}

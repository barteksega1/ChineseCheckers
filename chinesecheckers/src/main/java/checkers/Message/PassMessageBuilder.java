package checkers.Message;

/**
 * Builds a pass message.
 */
public class PassMessageBuilder extends MessageBuilder {

    @ SuppressWarnings("unused")
    private final String[] parts;

    /**
     * Constructs a PassMessageBuilder with the specified parts.
     *
     * @param parts the parts of the message
     */
    public PassMessageBuilder(String[] parts) {
        this.parts = parts; // Ignoring the parts but they are here for better times
    }

    /**
     * Executes the pass message.
     *
     * @param parts the parts of the message
     * @return the parts of the message
     */
    @Override
    public String[] executeMessage(String[] parts) {
        return parts;
    }
}

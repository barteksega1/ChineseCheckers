package checkers.Message;

/**
 * Handles messages and delegates to appropriate message builders.
 */
public class MessageHandler {

    /**
     * Handles the given message string and returns the processed message parts.
     *
     * @param messageString the message string to handle
     * @return the processed message parts
     */
    public static String[] handle(String messageString) {
        MessageBuilder builder;
        String[] parts = messageString.split("\\s+");
        if (parts.length != 5) {
            builder = new ErrorMessageBuilder(parts);
            return builder.executeMessage(parts);
        }

        switch (parts[0]) {
            case "move":
            case "Move":
            case "m":
            case "moved":
                builder = new MoveMessageBuilder(parts);
                return builder.executeMessage(parts);
            default:
                builder = new ErrorMessageBuilder(parts);
                return builder.executeMessage(parts);
        }
    }
}

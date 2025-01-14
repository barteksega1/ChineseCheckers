package checkers.Message;

public class MessageHandler {

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

package checkers.Message;

public class PassMessageBuilder extends MessageBuilder {

    //TODO: look if thats needed
    private final String[] parts;

    public PassMessageBuilder(String[] parts) {
        this.parts = parts; // Ignoring the parts but they are here for better times
    }

    @Override
    public String[] executeMessage(String[] parts) {
        return parts;
    }
}

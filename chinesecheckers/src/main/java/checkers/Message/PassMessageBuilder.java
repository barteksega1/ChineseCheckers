package checkers.Message;
import checkers.Commands.PassCommand;;

public class PassMessageBuilder extends MessageBuilder {

    @SuppressWarnings("unused")  // for now
    private final String[] parts;  // final for now

    public PassMessageBuilder(String[] parts)
    {
        this.parts = parts; //ignoring the parts but they are here for better times 😭
    }

    @Override public void executeMessage(String[] parts) {
        PassCommand pass = new PassCommand();
        pass.command();
    };
    
}

package checkers.Message;
import checkers.Commands.PassCommand;;

public class PassMessageBuilder extends MessageBuilder {

    private String[] parts;

    public PassMessageBuilder(String[] parts)
    {
        this.parts = parts; //ignoring the parts but they are here for better times 😭
    }

    @Override public void executeMessage(String[] parts) {
        PassCommand pass = new PassCommand();
        pass.command();
    };
    
}

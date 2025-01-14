package checkers.Message;


public class ErrorMessageBuilder extends MessageBuilder {

    //TODO: look if thats needed
    private final String[] parts; 

    public ErrorMessageBuilder(String[] parts)
    {
        this.parts = parts;
    }

    @Override public String[] executeMessage(String[] parts) {
        String[] errorInput = {"error"}; 
        return errorInput;
    };
}


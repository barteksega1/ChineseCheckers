package checkers.Message;


public class ErrorMessageBuilder extends MessageBuilder {
    
    @SuppressWarnings("unused")    // for now
    private final String[] parts; // final for now

    public ErrorMessageBuilder(String[] parts)
    {
        this.parts = parts;
    }

    @Override public String[] executeMessage(String[] parts) {
        String[] errorInput = {"error"}; 
        return errorInput;
    };

}
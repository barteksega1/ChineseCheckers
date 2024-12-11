package checkers.Message;

public class MessageHandler 
{
    

    public static String handle(String messageString)
    {
        MessageBuilder builder;
        String[] parts = messageString.split("\\s+");

        switch(parts[0]) { //with gui this part can be replaced using button handlers making it more friendly for future modifications

            case "move":
            case "Move":
            case "m":
            builder = new MoveMessageBuilder(parts);
            return builder.executeMessage(parts);

            case "pass":
            case "p":
            builder = new PassMessageBuilder(parts);
            return builder.executeMessage(parts);


            default:
            return "Unknown command \n";
        }
        
  
    }

}

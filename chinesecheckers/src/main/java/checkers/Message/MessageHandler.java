package checkers.Message;

public class MessageHandler 
{
    

    public static void handle(String messageString)
    {
        MessageBuilder builder;
        String[] parts = messageString.split("\\s+");

        switch(parts[0]) { //with gui this part can be replaced using button handlers making it more friendly for future modifications

            case "move":
            case "Move":
            case "m":
            builder = new MoveMessageBuilder(parts);
            builder.executeMessage(parts);
            break;

            case "pass":
            case "p":
            builder = new PassMessageBuilder(parts);
            builder.executeMessage(parts);


            default:
            System.out.println("Unknown command \n");
            break;
        }
        
  
    }

}

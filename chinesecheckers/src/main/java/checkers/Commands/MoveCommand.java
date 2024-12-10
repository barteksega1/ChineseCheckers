package checkers.Commands;

public class MoveCommand extends Command {
    
    @SuppressWarnings("unused") // for now
    private final String[] moveInput; // final for now

    public MoveCommand(String[] moveInput)
    {
        this.moveInput = moveInput;
    }

    //implementation of move command
    @Override public void command()
    {
        System.out.println("\n moved \n");
    }
    
}

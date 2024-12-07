package checkers.Commands;

public class MoveCommand extends Command {
    private String[] moveInput;

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

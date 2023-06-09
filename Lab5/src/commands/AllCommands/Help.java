package commands.AllCommands;

import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class Help implements Command {
    /*Method that displays help*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            for (Command command : Invoker.getCommandHashMap().values()) {
                System.out.println(command.description());
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "help : вывести справку по доступным командам";
    }
}

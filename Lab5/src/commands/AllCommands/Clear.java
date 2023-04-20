package commands.AllCommands;

import Vehicle_data.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class Clear implements Command {
    /**Method, that clear collection*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            VehiclesCollection.getVehicle().clear();
            System.out.println("Коллекция очищена");
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}

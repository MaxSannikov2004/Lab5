package commands.AllCommands;

import Vehicle_data.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;
//import exceptions.InvalidCommandException;

public class Info implements Command {
    /**Метод, выводящий информацию о коллекции*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            VehiclesCollection.getInfo();
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
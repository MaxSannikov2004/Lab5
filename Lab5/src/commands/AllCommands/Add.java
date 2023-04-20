package commands.AllCommands;

import commands.Command;
import Vehicle_data.*;
import commands.Invoker;
import exeptions.InvalidCommandException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Add implements Command {
    //Method, that adding new object

    @Override
    public void execute() throws InputMismatchException {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            VehiclesCollection.getVehicle().add(Adder.vehicleAdder());
            System.out.println("Новый элемент коллекции добавлен");
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    // Method, that do ADD command from file
    protected static void adderFromFile (Scanner scanner) {
        try {
            VehiclesCollection.getVehicle().add(Adder.fromFileAdder(scanner));
            System.out.println("Новый элемент коллекции добавлен");
        } catch (InputMismatchException ignored) {}
    }
    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
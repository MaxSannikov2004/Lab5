package commands.AllCommands;

import Vehicle_data.Vehicle;
import Vehicle_data.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Save implements Command {

    /**Метод, сохраняющий коллекцию в файл*/
    @Override
    public void execute() {
        try {
            if(Invoker.getSplit().length != 1){
                throw new InvalidCommandException();
            }
            try (FileOutputStream outputStream = new FileOutputStream(Invoker.getFile())) {
                for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
                    outputStream.write((vehicle.getName() + ", " + vehicle.getCoordinates().getX() + ", " + vehicle.getCoordinates().getY() + ", " + vehicle.getEnginePower() + ", " + vehicle.getCapacity() + ", " + vehicle.getFuelConsumption() + ", " + vehicle.getType() + "\n").getBytes());
                }
                outputStream.flush();
                outputStream.close();
                System.out.println("Коллекция успешно сохранена в файл");

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public String description() {
        return "save : сохранить коллекцию в файл";
    }
}
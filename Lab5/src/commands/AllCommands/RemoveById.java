package commands.AllCommands;

import Vehicle_data.Vehicle;
import Vehicle_data.VehiclesCollection;
import commands.Command;
import commands.Invoker;
import exeptions.InvalidCommandException;

public class RemoveById implements Command {

    /**Метод, удаляющий дракона по значению id
     * @param id - id объекта которого надо удалить*/
    private void removerById(long id) {
        boolean vehicleExist = false;
        for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
            if (vehicle.getId() == id) {
                VehiclesCollection.getVehicle().remove(vehicle);
                System.out.println("Дракон успешно удалён");
                vehicleExist = true;
            }
        }
        if (!vehicleExist) {
            System.out.println("Такого дракона не существует");
        }
    }
    /**Выполняет команду с помощью removerById
     * @see RemoveById#removerById(long) */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            if (!VehiclesCollection.getVehicle().isEmpty()) {
                removerById(id);
            } else {
                System.out.println("Коллекция пуста, такого дракона не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}

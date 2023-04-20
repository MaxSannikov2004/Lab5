package commands;

import Vehicle_data.VehiclesCollection;
import commands.AllCommands.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class Invoker {
    /** Поле, отвечающее за продолжение работы программы */
    private static boolean programRunning = true;
    /** Поле, содержащее в себе введенные пользователем команду и её аргументы */
    private static String[] split;
    /** Поле, содержащее путь к файлу, с которым взаимодействует программа */
    private static String file;
    /** Коллекция, через которую осуществляется выполнение команд */
    private static final HashMap<String, Command> commandHashMap = new HashMap<>();
    static {
        commandHashMap.put("help", new Help());
        commandHashMap.put("info", new Info());
        commandHashMap.put("show", new Show());
        commandHashMap.put("add", new Add());
        commandHashMap.put("update", new Update());
        commandHashMap.put("remove_by_id", new RemoveById());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("save", new Save());
        commandHashMap.put("execute_script", new ExecuteScript());
        commandHashMap.put("exit", new Exit());
        /*commandHashMap.put("insert_at", new InsertAt());
        commandHashMap.put("remove_at", new RemoveAt());
        commandHashMap.put("remove_last", new RemoveLast());
        commandHashMap.put("remove_all_by_type", new RemoveAllByType());
        commandHashMap.put("min_by_capacity", new MinByCapacity());
        commandHashMap.put("count_greater_than_type", new CountGreaterThanType());*/
    }
    public static String[] getSplit() {
        return split;
    }
    public static void setSplit(String[] split) {
        Invoker.split = split;
    }
    public static String getFile() {
        return file;
    }
    public static void setProgramRunning(boolean programRunning) {
        Invoker.programRunning = programRunning;
    }
    public static HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
    /** Метод, реализующий работу с консолью */
    private static void startConsoleProgram() {
        System.out.println("Введите команду (help : вывести справку по доступным командам)");
        Scanner scanner = new Scanner(System.in);
        while (programRunning) {
            try {
                try {
                    split = scanner.nextLine().trim().split(" ");
                } catch (NoSuchElementException noSuchElementException) {
                    System.out.println("Неверный ввод, перезапустите программу");
                    programRunning = false;
                }
                commandHashMap.get(split[0]).execute();
            } catch (NullPointerException nullPointerException) {
                if (programRunning) { System.out.println("Неверная команда"); }
            }
        }
        scanner.close();
    }
    /**Метод, проверяющий заданный файл и начинающий работу с коллекцией
     * @see Invoker#startConsoleProgram()
     * @see VehiclesCollection#putVehicleFromFile() */
    public static void invoker(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            file = args[0];
            if (new File(file).canRead() & new File(file).exists()) {
                if (VehiclesCollection.putVehicleFromFile()) startConsoleProgram();
            } else {
                System.out.println("Нет доступа к файлу");
            }
        } else {
            System.out.println("Неверно указано имя файла");
        }
    }
}
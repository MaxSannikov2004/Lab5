package commands.AllCommands;

import Vehicle_data.*;
import commands.Command;
import commands.Invoker;
import exeptions.IllegalValueOfYException;
import exeptions.InvalidCommandException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Update implements Command {

    /**Метод, обновляющий название
     * @param vehicle вид техники, у которой меняется имя*/
    private void updateName(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите название");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                vehicle.setName(name);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий мощность двигателя
     * @param vehicle, у которого меняется мощность двигателя*/
    private void updateEnginePower(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новую мощность двигателя");
            String string = scanner.nextLine();
            if (string.matches("([-+]?\\d+)")) {
                Double enginePower = Double.parseDouble(string);
                vehicle.setEnginePower(enginePower);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий тип дракона
     * @param vehicle, у которого меняется вместимость*/
    private void updateCapacity(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новую вместимость");
            String string = scanner.nextLine();
            if (string.matches("([-+]?\\d+)")) {
                long capacity = Long.parseLong(string);
                vehicle.setCapacity(capacity);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий цвет дракона
     * @param vehicle, у которого меняется расход топлива*/
    private void updateFuelConsumption(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый расход топлива");
            String string = scanner.nextLine();
            if (string.matches("([-+]?\\d+)")) {
                long fuelConsumption = Long.parseLong(string);
                vehicle.setFuelConsumption(fuelConsumption);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**Метод, обновляющий тип техники
     * @param vehicle, у которого меняется тип*/
    private void updateType(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите тип транспорта (Цифру или название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
            String vehicleType = scanner.nextLine();
            if (!(vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                switch (vehicleType) {
                    case "1", "PLANE" -> vehicle.setType(VehicleType.PLANE);
                    case "2", "WISE" -> vehicle.setType(VehicleType.HELICOPTER);
                    case "3", "CHAOTIC_EVIL" -> vehicle.setType(VehicleType.SUBMARINE);
                }
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    /**Метод, обновляющий координаты дракона
     * @param vehicle, у которого меняется координаты
     * @see Update#getNewXCoordinate(Scanner)
     * @see Update#getNewYCoordinate(Scanner) */
    private void updateCoordinates(Scanner scanner, Vehicle vehicle) {
        vehicle.getCoordinates().setX(getNewXCoordinate(scanner));
        vehicle.getCoordinates().setY(getNewYCoordinate(scanner));
    }
    /**Метод, получающий новую координату х
     * @return возвращает координату х*/
    private double getNewXCoordinate(Scanner scanner) {
        double x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y дракона");
            String xString = scanner.nextLine();
            try {
                x = Double.parseDouble(xString);
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
        return x;

    }
    /**Метод, получающий новую координату у
     * @return возвращает координату у*/
    private long getNewYCoordinate(Scanner scanner) {
        long y = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y");
            String yString = scanner.nextLine();
            try {
                if (yString.matches("([-+]?\\d+)")) {
                    y = Long.parseLong(yString);
                    if (y > 746) {
                        throw new IllegalValueOfYException();
                    } else {
                        i = false;
                    }
                } else {
                    System.out.println("Неверный тип данных");
                }
            } catch (IllegalValueOfYException illegalValueOfYException) {
                System.out.println(illegalValueOfYException.getMessage());
            }
        }
        return y;

    }
    /**Метод, выводящий варианты параметров для изменения и возвращающий один из них
     * @return возвращает цифру, обозначающую параметр для изменения*/
    private String requestInput(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                                    Выберите параметр дракона, который хотите изменить:
                                    Название - введите  1
                                    Мощность двигателя - введите 2
                                    Вместимость - введите 3
                                    Расход топлива - введите 4
                                    Вид - введите 5
                                    Координаты - введите 6""");
            s = scanner.nextLine();
            i = false;
        }
        return s;
    }
    /**Метод, вызывающий нужный метод для обновления определенного параметра
     * @param vehicle, параметр которого нужно изменить
     * @param s число, обозначающее, какую характеристику дракона надо изменить
     * @see Update#updateName(Scanner, Vehicle)
     * @see Update#updateEnginePower(Scanner, Vehicle)
     * @see Update#updateCapacity(Scanner, Vehicle)
     * @see Update#updateFuelConsumption(Scanner, Vehicle)
     * @see Update#updateType(Scanner, Vehicle)
     * @see Update#updateCoordinates(Scanner, Vehicle) */
    private void fieldsUpdater(String s, Scanner scanner, Vehicle vehicle) {
        switch (s) {
            case "1" -> updateName(scanner, vehicle);
            case "2" -> updateEnginePower(scanner, vehicle);
            case "3" -> updateCapacity(scanner, vehicle);
            case "4" -> updateFuelConsumption(scanner, vehicle);
            case "5" -> updateType(scanner, vehicle);
            case "6" -> updateCoordinates(scanner, vehicle);
        }
        System.out.println("Параметр дракона успешно обновлён");
    }
    /**Метод, обновляющий данные о драконе
     * @param id - id параметра, который нужно изменить
     * @see Update#requestInput(Scanner)
     * @see Update#fieldsUpdater(String, Scanner, Vehicle) */
    private void updateVehicle(long id) {
        boolean vehicleExists = false;
        for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
            if (vehicle.getId() == id) {
                vehicleExists = true;
                Scanner scanner = new Scanner(System.in);
                String s = requestInput(scanner);
                if (!(s.matches("[1-7]"))) {
                    System.out.println("Неверный параметр");
                } else {
                    fieldsUpdater(s, scanner, vehicle);
                }
            }
        }
        if (!vehicleExists) System.out.println("Такого дракона не существует");
    }
    /**Метод, исполняющий команду
     * @see Update#updateVehicle(long) */
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
                updateVehicle(id);
            } else {
                System.out.println("Такого дракона не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    /** Метод, обновляющий дракона параметрами из файла
     * @see Update#parametersReader(Scanner)
     * @see Update#fieldsUpdaterFromFile(String, String, Vehicle, Scanner) */
    protected static void updaterFromFile(Scanner scanner) {
        String[] parameters = parametersReader(scanner);
        try {
            if (Invoker.getSplit().length != 2) throw new InputMismatchException();
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            boolean vehicleExists = false;
            Vehicle thisVehicle = new Vehicle("", new Coordinates(0, 0), Double.parseDouble("0"), Long.parseLong("0"), Long.parseLong("0"), null);
            for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
                if (vehicle.getId() == id) {
                    vehicleExists = true;
                    thisVehicle = vehicle;
                }
            }
            if (!vehicleExists) throw new InputMismatchException();
            fieldsUpdaterFromFile(parameters[0], parameters[1], thisVehicle, scanner);
        } catch (InputMismatchException ignored) {}
    }
    /** Метод, считывающий обновляемое поле из фала
     * @return возвращает массив, состоящий из номера обновляемого параметра и его нового значения */
    private static String[] parametersReader(Scanner scanner) {
        String[] parameters = new String[2];
        for (int i = 0; i < parameters.length; ++i) {
            try {
                parameters[i] = scanner.nextLine();
                if (parameters[i].trim().isEmpty()) parameters[i] = null;
            } catch (NoSuchElementException noSuchElementException) {
                parameters[i] = null;
            }
        }
        return parameters;
    }
    /** Метод, обновляющий выбранное поле из файла
     * @see Update#updateName(Scanner, Vehicle)
     * @see Update#updateEnginePower(Scanner, Vehicle)
     * @see Update#updateCapacity(Scanner, Vehicle)
     * @see Update#updateFuelConsumption(Scanner, Vehicle)
     * @see Update#updateType(Scanner, Vehicle)
     * @see Update#updateCoordinates(Scanner, Vehicle) */
    private static void fieldsUpdaterFromFile(String parameter, String newValue, Vehicle vehicle, Scanner scanner) {
        if (parameter.matches(("[1-7]"))) {
            switch (parameter) {
                case "1" -> updateNameFromFile(newValue, vehicle);
                case "2" -> updateEnginePowerFromFile(newValue, vehicle);
                case "3" -> updateCapacityFromFile(newValue, vehicle);
                case "4" -> updateFuelConsumptionFromFile(newValue, vehicle);
                case "5" -> updateTypeFromFile(newValue, vehicle);
                case "6" -> updateCoordinatesFromFile(newValue,scanner, vehicle);
            }
            System.out.println("Параметр дракона успешно обновлён");
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий имя дракона на новое из файла */
    private static void updateNameFromFile(String name, Vehicle vehicle) {
        if (!name.trim().isEmpty()) {
            vehicle.setName(name);
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий возраст дракона на новый из файла */
    private static void updateEnginePowerFromFile(String enginePowerString, Vehicle vehicle) {
        try {
            Double enginePower = Double.parseDouble(enginePowerString);
            vehicle.setEnginePower(enginePower);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий тип дракона на новый из файла */
    private static void updateCapacityFromFile(String capacityString, Vehicle vehicle) {
        try {
            long capacity = Long.parseLong(capacityString);
            vehicle.setCapacity(capacity);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий цвет дракона на новый из файла */
    private static void updateFuelConsumptionFromFile(String fuelConsumptionString, Vehicle vehicle) {
        try {
            long fuelConsumption = Long.parseLong(fuelConsumptionString);
            vehicle.setFuelConsumption(fuelConsumption);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий характер дракона на новый из файла */
    private static void updateTypeFromFile(String vehicleType, Vehicle vehicle) {
        if (vehicleType.matches("[1-3]") || vehicleType.equals("PLANE") || vehicleType.equals("HELICOPTER") || vehicleType.equals("SUBMARINE")) {
            switch (vehicleType) {
                case "1", "PLANE" -> vehicle.setType(VehicleType.PLANE);
                case "2", "WISE" -> vehicle.setType(VehicleType.HELICOPTER);
                case "3", "CHAOTIC_EVIL" -> vehicle.setType(VehicleType.SUBMARINE);
            }
        } else {
            throw new InputMismatchException();
        }
    }
    /** Метод, обновляющий координаты дракона на новые из файла */
    private static void updateCoordinatesFromFile(String xString, Scanner scanner, Vehicle vehicle) {
        try {
            Double.parseDouble(xString);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();}
            double x = Double.parseDouble(xString);
            vehicle.getCoordinates().setX(x);

        try {
            String yString = scanner.nextLine();
            long y = Long.parseLong(yString);
            if (y > 746) {
                throw new InputMismatchException();
            }
            vehicle.getCoordinates().setY(y);


        } catch (NoSuchElementException noSuchElementException) {
            throw new InputMismatchException();
        }
    }
    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}


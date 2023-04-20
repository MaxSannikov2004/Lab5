package Vehicle_data;

import commands.Invoker;
import exeptions.IllegalValueOfYException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Adder {
        /**Метод для создания нового вида транспорта с помощью консоли
         * @return Dragon*/
        public static Vehicle vehicleAdder() {
            Scanner sc = new Scanner(System.in);
            String name = "k";
            double x = 0;
            long y = 0;
            Double enginePower = null;
            long capacity = Long.parseLong("0");
            long fuelConsumption = Long.parseLong("0");
            VehicleType type = VehicleType.PLANE;
            int i = 1;
            while (i != 0) {
                try {
                    while (i == 1) {
                        System.out.println("Введите название");
                        name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            throw new InputMismatchException();
                        }
                        ++i;
                    }
                    while (i == 2) {
                        System.out.println("Введите координату X");
                        String s = sc.nextLine();
                        try {
                            Double.parseDouble(s);
                        } catch (NumberFormatException ex){
                            throw new InputMismatchException();
                        }
                        x = Double.parseDouble(s);
                        ++i;

                    }
                    while (i == 3) {
                        try {
                            System.out.println("Введите координату Y");
                            String s = sc.nextLine();
                            if(!s.matches("([-+]?\\d+)")){
                                throw new InputMismatchException();
                            }
                            y = Long.parseLong(s);
                            if (y > 746) {
                                throw new IllegalValueOfYException();
                            }
                            ++i;
                        } catch (IllegalValueOfYException illegalValueOfXException) {
                            System.out.println(illegalValueOfXException.getMessage());
                        }
                    }
                    while (i == 4) {
                        System.out.println("Введите мощность двигателя");
                        String s = sc.nextLine();
                        if(!s.matches("([-+]?\\d+)")){
                            throw new InputMismatchException();
                        }
                        enginePower = Double.parseDouble(s);
                        ++i;
                    }
                    while (i == 5) {
                        System.out.println("Введите вместимость");
                        String s = sc.nextLine();
                        if(!s.matches("([-+]?\\d+)")){
                            throw new InputMismatchException();
                        }
                        capacity = Long.parseLong(s);
                        ++i;
                    }
                    while (i == 6) {
                        System.out.println("Введите расход топлива");
                        String s = sc.nextLine();
                        if(!s.matches("([-+]?\\d+)")){
                            throw new InputMismatchException();
                        }
                        fuelConsumption = Long.parseLong(s);
                        ++i;
                    }
                    while (i == 7) {
                        System.out.println("Введите тип транспорта (Цифру или название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
                        String vehicleType = sc.nextLine();
                        if (!(vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                            throw new InputMismatchException();
                        }
                        switch (vehicleType) {
                            case "1", "PLANE" -> type = VehicleType.PLANE;
                            case "2", "WISE" -> type = VehicleType.HELICOPTER;
                            case "3", "CHAOTIC_EVIL" -> type = VehicleType.SUBMARINE;
                        }
                        ++i;
                    }

                    i = 0;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Неверный тип данных");
                }
            }
            return new Vehicle(name, new Coordinates(x, y), enginePower, capacity, fuelConsumption, type);
        }
        /**Метод для добавления дракона из файла
         * @see Adder#fieldsReader(Scanner)
         * @see Adder#nameFromFileChecker(String)
         * @see Adder#XCoordinateFromFileChecker(String)
         * @see Adder#YCoordinateFromFileChecker(String)
         * @see Adder#enginePowerFromFileChecker(String)
         * @see Adder#capacityFromFileChecker(String)
         * @see Adder#fuelConsumptionFromFileChecker(String)
         * @see Adder#typeFromFileChecker(String)
         * @return Vehicle */
        public static Vehicle fromFileAdder(Scanner scanner) {
            if (Invoker.getSplit().length != 1) throw new InputMismatchException();
            String[] fields = fieldsReader(scanner);
            return new Vehicle(nameFromFileChecker(fields[0]), new Coordinates(XCoordinateFromFileChecker(fields[1]), YCoordinateFromFileChecker(fields[2])), enginePowerFromFileChecker(fields[3]), capacityFromFileChecker(fields[4]), fuelConsumptionFromFileChecker(fields[5]), typeFromFileChecker(fields[6]));
        }
        /**Метод, считывающий поля из файла
         * @return возвращает массив с полями нового дракона */
        private static String[] fieldsReader(Scanner sc) {
            String[] fields = new String[8];
            for (int i = 0; i < fields.length; ++i) {
                try {
                    fields[i] = sc.nextLine();
                    if (fields[i].trim().isEmpty()) fields[i] = null;
                } catch (NoSuchElementException noSuchElementException) {
                    fields[i] = null;
                }
            }
            return fields;
        }
        /** Метод, проверяющий имя нового объекта
         * @return String name */
        private static String nameFromFileChecker(String name) {
            if (name.trim().isEmpty()) {
                throw new InputMismatchException();
            }
            return name;
        }
        /** Метод, проверяющий координату x
         * @return double x */
        private static double XCoordinateFromFileChecker(String xString) {
            try {
                Double.parseDouble(xString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Double.parseDouble(xString);
        }
        /** Метод, проверяющий координату y
         * @return float y */
        private static long YCoordinateFromFileChecker(String yString) {
            try {
                Long.parseLong(yString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            long y = Long.parseLong(yString);
            if (y > 746) {
                throw new InputMismatchException();
            }
            return y;
        }
        /** Метод, проверяющий энергию двигателя
         * @return Double enginePower */
        private static Double enginePowerFromFileChecker (String enginePowerString) {
            try {
                Long.parseLong(enginePowerString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Double.parseDouble(enginePowerString);
        }
        /** Метод, проверяющий вместимость транспорта
         * @return long capacity */
        private static long capacityFromFileChecker(String capacityString) {
            try {
                Long.parseLong(capacityString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Long.parseLong(capacityString);
        }
        /** Метод, проверяющий расход двигателя
         * @return Type */
        private static long fuelConsumptionFromFileChecker(String fuelConsumptionString) {
            try {
                Long.parseLong(fuelConsumptionString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Long.parseLong(fuelConsumptionString);
        }
        /** Метод, проверяющий вид техники
         * @return VehicleType*/
        private static VehicleType typeFromFileChecker(String vehicleType) {
            if (!(vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                throw new InputMismatchException();
            }
            VehicleType type = null;
            switch (vehicleType) {
                case "1", "PLANE" -> type = VehicleType.PLANE;
                case "2", "WISE" -> type = VehicleType.HELICOPTER;
                case "3", "CHAOTIC_EVIL" -> type = VehicleType.SUBMARINE;
            }
            return type;
        }

}

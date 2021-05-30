package ru.lab.lab6.utils;

import ru.lab.lab6.entities.InputData;
import ru.lab.lab6.functions.Function;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class ReaderInputData {

    public static InputData read(List<Function> functions) {
        InputData inputData = new InputData();
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Каким образом будете вводить данные? Введите 'f' если из файла, 'k' - клавиатуры. ");
            String type = scanner.nextLine();
            if (!type.equals("f") && !type.equals("k")) {
                printError("Выбран несуществующий тип ввода данных.");
            }

            boolean isKeyboard = true;
            if (type.equals("f")) {
                isKeyboard = false;
                System.out.println("Введите путь до файла.");
                String path = scanner.nextLine();
                FileReader fileReader = new FileReader(path);
                scanner.close();
                scanner = new Scanner(fileReader);
            }

            if (isKeyboard) {
                printFunctions(functions);
                System.out.println("Введите номер функции.");
            }

            int functionIndex = scanner.nextInt() - 1;
            if (functionIndex < 0 || functionIndex >= functions.size()) {
                printError("Неверный номер функции.");
            }

            if (isKeyboard) {
                System.out.println("Введите интервал дифференцирования [a, b]. (a < b)");
            }
            Double a = readDouble(scanner);
            Double b = readDouble(scanner);
            if (a > b) {
                printError("Неверные значения [a, b].");
            }

            if (isKeyboard) {
                System.out.println("Введите y(" + a + ").");
            }

            Double y0 = readDouble(scanner);

            if (isKeyboard) {
                System.out.println("Введите точность.");
            }

            Double eps = readDouble(scanner);
            if (eps <= 0) {
                printError("Некорректное значение точности.");
            }

            inputData.setFunction(functions.get(functionIndex));
            inputData.setY0(y0);
            inputData.setA(a);
            inputData.setB(b);
            inputData.setEps(eps);

        } catch (Exception e) {
            System.out.println("Произошла ошибка при чтении данных.");
            System.exit(0);
        }
        return inputData;
    }

    private static void printError(String error) {
        System.out.println(error);
        System.exit(0);
    }

    private static void printFunctions(List<Function> functions) {
        System.out.println("Доступные функции:");
        for (int i = 0; i < functions.size(); ++i) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + functions.get(i).getName());
        }
    }

    private static Double readDouble(Scanner scanner) {
        String line = scanner.next();
        line = line.replace(',', '.');
        return Double.parseDouble(line);
    }
}

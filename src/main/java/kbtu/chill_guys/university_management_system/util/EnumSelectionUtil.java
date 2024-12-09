package main.java.kbtu.chill_guys.university_management_system.util;

import java.util.Scanner;

public class EnumSelectionUtil {

    private EnumSelectionUtil(){
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static <E extends Enum<E>> E selectEnum(Class<E> enumClass) {
        E[] enumValues = enumClass.getEnumConstants();

        System.out.println("Choose number:");
        for (int i = 0; i < enumValues.length; i++) {
            System.out.printf("%d. %s%n", i + 1, enumValues[i].name());
        }

        while (true) {
            try {
                System.out.print("Введите номер: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= enumValues.length) {
                    return enumValues[choice - 1];
                } else {
                    System.out.println("Некорректный номер. Попробуйте ещё раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }
}

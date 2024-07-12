package utils;

import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    // Reads a string from the console
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Reads an integer from the console
    public static int readInt(String prompt) {
        int result = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                result = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return result;
    }

    // Reads a double from the console
    public static double readDouble(String prompt) {
        double result = 0.0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                result = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid double.");
            }
        }
        return result;
    }

    // Reads a boolean from the console
    public static boolean readBoolean(String prompt) {
        boolean result = false;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt + " (true/false): ");
            try {
                result = Boolean.parseBoolean(scanner.nextLine());
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
        return result;
    }

    // Close the scanner when done
    public static void closeScanner() {
        scanner.close();
    }
}
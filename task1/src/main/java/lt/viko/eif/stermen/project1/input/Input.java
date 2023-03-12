package lt.viko.eif.stermen.project1.input;

import java.util.Objects;
import java.util.Scanner;

/**
 * A simple input class that waits and reads user input by using the scanner class.
 */
public class Input {
    private static Scanner sc = new Scanner(System.in);

    /**
     * @return reads the input and tries to convert it to an integer.
     */
    public static int gibi() {
        int converted;
        while (true) {
            try {
                converted = sc.nextInt();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum integer amount to be inputted.
     * @param max maximum integer amount to be inputted.
     * @return input as integer.
     */
    public static int gibi(int min, int max) {
        while (true) {
            int input = gibi();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a double.
     */
    public static double gibd() {
        double converted;
        while (true) {
            try {
                converted = sc.nextDouble();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum double amount to be inputted.
     * @param max maximum double amount to be inputted.
     * @return input as double.
     */
    public static double gibd(int min, int max) {
        while (true) {
            double input = gibd();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a float.
     */
    public static float gibf() {
        float converted;
        while (true) {
            try {
                converted = sc.nextFloat();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param min minimum float amount to be inputted.
     * @param max minimum float amount to be inputted.
     * @return input as float.
     */
    public static float gibf(int min, int max) {
        while (true) {
            float input = gibf();
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT OUT OF RANGE: " + input + " in [MIN]{" + min + "}-[MAX]{" + max + "}\nTRY AGAIN");
            }
        }
    }

    /**
     * @return reads the input and tries to convert it to a String.
     */
    public static String gibs() {
        while (true) {
            try {
                String converted = sc.next();
                return converted;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @return reads the input and tries to return only if input is 'Y', 'y', 'N', 'n'
     */
    public static String GetYorN() {
        while (true) {
            String input = gibs();
            input = input.toUpperCase();
            if (Objects.equals(input, "Y") || Objects.equals(input, "N")) {
                return input;
            } else {
                System.out.println("EXCEPTION! INPUT MUST BE 'Y' OR 'N'");
            }
        }
    }
}

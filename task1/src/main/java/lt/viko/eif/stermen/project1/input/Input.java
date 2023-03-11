package lt.viko.eif.stermen.project1.input;

import java.util.Objects;
import java.util.Scanner;

/**
 *
 */
public class Input {
    /**
     *
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * @return
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
     * @param min
     * @param max
     * @return
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
     * @return
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
     * @param min
     * @param max
     * @return
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
     * @return
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
     * @param min
     * @param max
     * @return
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
     * @return
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
     * @return
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

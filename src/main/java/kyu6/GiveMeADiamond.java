package main.java.kyu6;

/**
 * 6 kyu - Give me a Diamond
 *
 * https://www.codewars.com/kata/5503013e34137eeeaa001648
 *
 * Details:
 *
 * Jamie is a programmer, and James' girlfriend. She likes diamonds, and wants a diamond string
 * from James. Since James doesn't know how to make this happen, he needs your help.
 * Task
 *
 * You need to return a string that looks like a diamond shape when printed on the screen, using
 * asterisk (*) characters. Trailing spaces should be removed, and every line must be terminated
 * with a newline character (\n).
 *
 * Return null/nil/None/... if the input is an even number or negative, as it is not possible to
 * print a diamond of even or negative size.
 */

public class GiveMeADiamond {
    public static final String DIAMOND = "*";
    public static final String SPACE = " ";

    public static String print(int n) {
        if (n <= 0 || n % 2 == 0) {
            return null;
        } else {
            final StringBuilder result = new StringBuilder();

            for (int i = 0; i < n; i++) {
                final int spaces = n/2 - i > 0 ? n/2 - i : i - n/2;
                final int diamonds = i <= n/2 ? 1+i*2 : -1 + (Math.abs(i - n) << 1);

                if (spaces != 0) {
                    result.append(String.format(String.format("%%%ds", spaces), " ").replace(" ", SPACE));
                }

                result.append(String.format(String.format("%%%ds", diamonds), " ").replace(" ", DIAMOND));
                result.append("\n");
            }
            return new String(result);
        }
    }
}

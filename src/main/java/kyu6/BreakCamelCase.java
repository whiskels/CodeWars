package main.java.kyu6;

/**
 * 6 kyu - Break camelCase
 *
 * https://www.codewars.com/kata/5208f99aee097e6552000148
 *
 * Details:
 *
 * Complete the solution so that the function will break up camel casing, using a space between
 * words.
 */

public class BreakCamelCase {
    public static String camelCase(String input) {
        return input.replaceAll("([A-Z])", " $1");
    }
}

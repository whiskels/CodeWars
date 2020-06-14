package main.java.kyu6;

/**
 * 6 kyu - CamelCase Method
 *
 * https://www.codewars.com/kata/587731fda577b3d1b0001196
 *
 * Details:
 *
 * Write simple .camelCase method (camel_case function in PHP, CamelCase in C# or camelCase in Java) for strings.
 * All words must have their first letter capitalized without spaces.
 */

public class CamelCaseMethod {
    public static String camelCase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean isPreviousCharSpace = false;
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if ((i == 0 || isPreviousCharSpace) && !Character.isWhitespace(current)) {
                sb.append(Character.toUpperCase(current));
                isPreviousCharSpace = false;
            } else if (Character.isWhitespace(current)) {
                isPreviousCharSpace = true;
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }
}
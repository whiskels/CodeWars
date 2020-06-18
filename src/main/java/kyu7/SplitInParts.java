package main.java.kyu7;

/**
 * 7 kyu - Split In Parts
 *
 * https://www.codewars.com/kata/5650ab06d11d675371000003
 *
 * Details:
 *
 * The aim of this kata is to split a given string into different strings of equal size (note size
 * of strings is passed to the method)
 */

public class SplitInParts {
    public static String splitInParts(String s, int partLength) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= s.length() - s.length() % partLength - partLength; i += partLength) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(s, i, i + partLength);
        }

        if (s.length() % partLength != 0) {
            sb.append(" ")
              .append(s.substring(s.length() - s.length()%partLength));
        }

        return new String(sb);
    }
}
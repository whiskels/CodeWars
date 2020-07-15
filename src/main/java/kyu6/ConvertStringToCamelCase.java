package main.java.kyu6;

/**
 * 6 kyu - Convert string to camel case
 *
 * https://www.codewars.com/kata/517abf86da9663f1d2000003
 *
 * Details:
 *
 * Complete the method/function so that it converts dash/underscore delimited words into camel
 * casing. The first word within the output should be capitalized only if the original word was
 * capitalized (known as Upper Camel Case, also often referred to as Pascal case).
 */

public class ConvertStringToCamelCase {
    public static String toCamelCase(String s) {
        final String regex = "([^a-zA-Z']+)'*\\1*";
        final String[] split = s.split(regex);
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                sb.append(split[i]);
            } else if (split[i].length()>0){
                sb.append(split[i].replaceFirst(split[i].substring(0, 1), split[i].substring(0, 1).toUpperCase()));
            }
        }
        return new String(sb);
    }
}

package main.java.kyu5;

/**
 * 5 kyu - Simple Pig Latin
 * <p>
 * https://www.codewars.com/kata/520b9d2ad5c005041100000f
 * <p>
 * Details:
 * <p>
 * Move the first letter of each word to the end of it,
 * then add "ay" to the end of the word. Leave punctuation marks untouched.
 */

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class SimplePigLatin {
    public static String pigIt(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        final String[] words = str.split("\\s");
        final StringBuilder sb = new StringBuilder();

        for (String s : words) {
            if (Pattern.matches("\\p{IsPunctuation}", s)) {
                sb.append(s)
                        .append(" ");
            } else {
                sb.append(s.substring(1))
                        .append(s.charAt(0))
                        .append("ay ");
            }
        }

        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 1);
        }

        return new String(sb);
    }

    @Test
    public void fixedTests() {
        assertEquals("igPay atinlay siay oolcay", pigIt("Pig latin is cool"));
        assertEquals("hisTay siay ymay tringsay", pigIt("This is my string"));
    }
}

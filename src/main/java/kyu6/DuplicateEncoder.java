package main.java.kyu6;

/**
 * 6 kyu - Duplicate Encoder
 *
 * https://www.codewars.com/kata/54b42f9314d9229fd6000d9c
 *
 * Details:
 *
 * The goal of this exercise is to convert a string to a new string where each character in the new
 * string is "(" if that character appears only once in the original string, or ")" if that
 * character appears more than once in the original string. Ignore capitalization when determining
 * if a character is a duplicate.
 */

import java.util.ArrayList;
import java.util.Collections;

public class DuplicateEncoder {
    public static String encode(String word) {
        final ArrayList<Character> chars = new ArrayList<>();
        final StringBuilder line = new StringBuilder();

        for (char c : word.toLowerCase().toCharArray()) {
            chars.add(c);
        }

        for (int i = 0; i < chars.size(); i++) {
            if (Collections.frequency(chars,chars.get(i)) > 1) {
                line.append(")");
            } else {
                line.append("(");
            }
        }

        return new String(line);
    }
}

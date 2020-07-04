package main.java.kyu5;

/**
 * 5 kyu - Scramblies
 * <p>
 * https://www.codewars.com/kata/55c04b4cc56a697bb0000048
 * <p>
 * Details:
 * <p>
 * Complete the function scramble(str1, str2) that returns true if a portion of str1 characters
 * can be rearranged to match str2, otherwise returns false.
 * <p>
 * Notes:
 * <p>
 * Only lower case letters will be used (a-z). No punctuation or digits will be included.
 * Performance needs to be considered
 */

import java.util.HashMap;
import java.util.Map;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        if (str2 == null || str2.length() == 0) {
            return true;
        }
        HashMap<Character, Integer> s1 = new HashMap<>();
        HashMap<Character, Integer> s2 = new HashMap<>();

        for (char ch : str1.toCharArray()) {
            char current = Character.toLowerCase(ch);
            s1.put(current, s1.getOrDefault(current, 0) + 1);
        }

        for (char ch : str2.toCharArray()) {
            char current = Character.toLowerCase(ch);
            s2.put(current, s2.getOrDefault(current, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : s2.entrySet()) {
            try {
                if (entry.getValue() > s1.get(entry.getKey())) {
                    return false;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }

        return true;
    }
}
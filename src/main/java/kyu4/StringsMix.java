package main.java.kyu4;

/**
 * 4 kyu - Strings mix
 *
 * https://www.codewars.com/kata/5629db57620258aa9d000014
 *
 * Details:
 *
 * Given two strings s1 and s2, we want to visualize how different the two strings are. We will
 * only take into account the lowercase letters (a to z). First let us count the frequency of each
 * lowercase letters in s1 and s2.
 *
 * s1 = "A aaaa bb c"
 *
 * s2 = "& aaa bbb c d"
 *
 * s1 has 4 'a', 2 'b', 1 'c'
 *
 * s2 has 3 'a', 3 'b', 1 'c', 1 'd'
 *
 * So the maximum for 'a' in s1 and s2 is 4 from s1; the maximum for 'b' is 3 from s2. In the
 * following we will not consider letters when the maximum of their occurrences is less than or
 * equal to 1.
 *
 * We can resume the differences between s1 and s2 in the following string: "1:aaaa/2:bbb" where
 * 1 in 1:aaaa stands for string s1 and aaaa because the maximum for a is 4. In the same manner
 * 2:bbb stands for string s2 and bbb because the maximum for b is 3.
 *
 * The task is to produce a string in which each lowercase letters of s1 or s2 appears as many
 * times as its maximum if this maximum is strictly greater than 1; these letters will be prefixed
 * by the number of the string where they appear with their maximum value and :. If the maximum is
 * in s1 as well as in s2 the prefix is =:.
 *
 * In the result, substrings (a substring is for example 2:nnnnn or 1:hhh; it contains the prefix)
 * will be in decreasing order of their length and when they have the same length sorted in
 * ascending lexicographic order (letters and digits - more precisely sorted by codepoint);
 * the different groups will be separated by '/'. See examples and "Example Tests".
 */

import org.junit.Test;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StringsMix {
    public static String mix(String s1, String s2) {
        if (s1.equals(s2)) {
            return "";
        }

        final Map<Character, Long> charactersFromS1 = calculateCharacterFrequency(s1);
        final Map<Character, Long> charactersFromS2 = calculateCharacterFrequency(s2);
        final List<Character> allCharacters = new ArrayList<>(charactersFromS1.keySet());

        for (char c : new ArrayList<>(charactersFromS2.keySet())) {
            if (!allCharacters.contains(c)) {
                allCharacters.add(c);
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < allCharacters.size(); i++) {
            final StringBuilder sb = new StringBuilder();
            final char current = allCharacters.get(i);
            final long frequency_1 = charactersFromS1.getOrDefault(current, 0L);
            final long frequency_2 = charactersFromS2.getOrDefault(current, 0L);
            final long frequency = Math.max(frequency_1, frequency_2);

            if (frequency_1 == frequency_2) {
                sb.append('=');
            } else {
                sb.append(frequency_1 > frequency_2 ? '1' : '2');
            }
            sb.append(':');

            for (int j = 0; j < frequency; j++) {
                sb.append(current);
            }

            result.add(sb.toString());
        }

        return result.stream()
                .sorted(Comparator.comparing(String::length)
                        .reversed()
                        .thenComparing(s -> s.length() == 0 ? -1 : s.charAt(0))
                        .thenComparing(Function.identity()))
                .collect(Collectors.joining("/"));
    }

    private static Map<Character, Long> calculateCharacterFrequency(String s) {
        return CharBuffer.wrap(s.toCharArray())
                .chars()
                .mapToObj(ch -> (char) ch)
                .filter(c -> Character.isAlphabetic(c))
                .filter(c -> Character.toUpperCase(c) != c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Test
    public void test() {
        System.out.println("Mix Fixed Tests");
        assertEquals("1:aaa/1:nnn/1:gg/2:ee/2:ff/2:ii/2:oo/2:rr/2:ss/2:tt",
                mix(" In many languages", " there's a pair of functions"));
    }
}
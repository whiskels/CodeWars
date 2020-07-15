package main.java.kyu6;

/**
 * 6 kyu - Where is my parent?!(cry)
 *
 * https://www.codewars.com/kata/58539230879867a8cd00011c
 *
 * Details:
 *
 * Mothers arranged a dance party for the children in school. At that party, there are only mothers
 * and their children.All are having great fun on the dance floor when suddenly all the lights went
 * out. It's a dark night and no one can see each other. But you were flying nearby and you can see
 * in the dark and have ability to teleport people anywhere you want.
 *  Legend:
 * -Uppercase letters stands for mothers, lowercase stand for their children, i.e. "A" mother's
 * children are "aaaa".
 * -Function input: String contains only letters, uppercase letters are unique.
 *
 *  Task:
 * Place all people in alphabetical order where Mothers are followed by their children, i.e.
 * "aAbaBb" => "AaaBbb".
 */

import java.util.ArrayList;
import java.util.Collections;

public class WhereIsMyParent {
    public static String findChildren(final String text) {
        final ArrayList<Character> chars = new ArrayList<>();

        for (Character ch : text.toCharArray()) {
            chars.add(ch);
        }

        Collections.sort(chars);

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.size(); i++) {
            final char currChar = chars.get(i);
            final char lowChar = Character.toLowerCase(currChar);
            if (lowChar != currChar) {
                sb.append(currChar);

                if (chars.get(i) != chars.get(i + 1)) {
                    final int frequency = Collections.frequency(chars, lowChar);

                    if (frequency > 0) {
                        for (int j = 0; j < frequency; j++) {
                            sb.append(lowChar);
                        }
                    }
                }
            }
        }
        return new String(sb);
    }
}

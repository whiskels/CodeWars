package main.java.kyu7;

/**
 * 7 kyu - Jaden Case Strings
 *
 * https://www.codewars.com/kata/5390bac347d09b7da40006f6
 *
 * Details:
 *
 * Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010) and
 * After Earth (2013). Jaden is also known for some of his philosophy that he delivers via Twitter.
 * When writing on Twitter, he is known for almost always capitalizing every word. For simplicity,
 * you'll have to capitalize each word, check out how contractions are expected to be in the
 * example below.
 *
 * Your task is to convert strings to how they would be written by Jaden Smith.
 * The strings are actual quotes from Jaden Smith, but they are not capitalized in the same way he
 * originally typed them.
 *
 * Example:
 *
 * Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
 * Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"
 *
 * Note that the Java version expects a return value of null for an empty string or null.
 */

import java.util.ArrayList;
import java.util.List;

public class JadenCasingStrings {

    public String toJadenCase(String phrase) {
        try {
            if (phrase.isEmpty()) {return null;
            } else {
                final List<Character> list = new ArrayList<>();
                final StringBuilder a = new StringBuilder();

                for (int i = 0; i < phrase.length(); i++) {
                    final char c = phrase.charAt(i);
                    char d = 0;

                    if (i > 1) {
                        d = phrase.charAt(i - 1);
                    }

                    if (i == 0 || Character.isWhitespace(d)) {
                        list.add(Character.toUpperCase(c));
                    } else {
                        list.add(c);
                    }
                }

                for (Character character : list) {
                    a.append(character);
                }

                return a.toString();
            }
        }
        catch (RuntimeException e) {
            return null;
        }
    }
}

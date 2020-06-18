package main.java.kyu6;

/**
 * 6 kyu - CamelCase Method
 *
 * https://www.codewars.com/kata/587731fda577b3d1b0001196
 *
 * Details:
 *
 * Write simple .camelCase method (camel_case function in PHP, CamelCase in C# or camelCase in
 * Java) for strings. All words must have their first letter capitalized without spaces.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CamelCaseMethod {
    public static String camelCase(String str) {
        final StringBuilder sb = new StringBuilder();
        boolean isPreviousCharSpace = false;

        for (int i = 0; i < str.length(); i++) {
            final char current = str.charAt(i);

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

    @Test
    public void testTwoWords() {
        assertEquals("TestCase", camelCase("test case"));
    }

    @Test
    public void testThreeWords() {
        assertEquals("CamelCaseMethod", camelCase("camel case method"));
    }

    @Test
    public void testLeadingSpace() {
        assertEquals("CamelCaseWord", camelCase(" camel case word"));
    }

    @Test
    public void testTrailingSpace() {
        assertEquals("SayHello", camelCase("say hello "));
    }

    @Test
    public void testSingleLetter() {
        assertEquals("Z", camelCase("z"));
    }

    @Test
    public void testTwoSpacesBetweenWords() {
        assertEquals("AbC", camelCase("ab  c"));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", camelCase(""));
    }
}

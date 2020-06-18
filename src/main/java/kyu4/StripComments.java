package main.java.kyu4;

/**
 * 4 kyu - Strip Comments
 *
 * https://www.codewars.com/kata/51c8e37cee245da6b40000bd/
 *
 * Details:
 *
 * Complete the solution so that it strips all text that follows any of a set of comment markers
 * passed in. Any whitespace at the end of the line should also be stripped out.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StripComments {
    public static String stripComments(String text, String[] commentSymbols) {
        if (text.length() == 0 || text.isEmpty()) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();
        final String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            final String s = lines[i];
            final StringBuilder helper = new StringBuilder();
            int commentStartPosition = s.length();

            if (commentStartPosition !=0) {
                for (String comment : commentSymbols) {
                    if (s.contains(comment)) {
                        commentStartPosition = Math.min(commentStartPosition, s.indexOf(comment));
                    }
                }
                helper.append(s, 0, commentStartPosition);
            }

            while (helper.length() !=0 && helper.charAt(helper.length() - 1) == ' ') {
                helper.setLength(helper.length() - 1);
            }

            helper.append("\n");
            sb.append(helper);
        }

        if (sb.length() != 0) {
            sb.setLength(sb.length() - 1);
        }

        return new String(sb);
    }

    @Test
    public void stripComments() throws Exception {
        assertEquals(
                "apples, pears\ngrapes\nbananas",
                StripComments.stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"})
        );

        assertEquals(
                "a\nc\nd",
                StripComments.stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"})
        );
    }
}

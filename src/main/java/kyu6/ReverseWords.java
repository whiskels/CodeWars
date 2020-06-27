package main.java.kyu6;

/**
 * 6 kyu - Stop gninnipS My sdroW!
 *
 * https://www.codewars.com/kata/5264d2b162488dc400000001
 *
 * Details:
 *
 * Write a function that takes in a string of one or more words, and returns the same string, but
 * with all five or more letter words reversed (Just like the name of this Kata). Strings passed
 * in will consist of only letters and spaces. Spaces will be included only when more than one
 * word is present.
 *
 * Examples: spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw" spinWords
 * ( "This is a test") => returns "This is a test" spinWords( "This is another test" )
 * => returns "This is rehtona test"
 *
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ReverseWords {
    public String spinWords(String sentence) {
        final String[] words = sentence.split("\\s+");
        final StringBuilder sb = new StringBuilder();

        for (String word : words) {
            final StringBuilder helper = new StringBuilder(word);

            if (helper.length() >= 5) {
                helper.reverse();
            }

            sb.append(helper);
            sb.append(" ");
        }

        return new String(sb).trim();
    }

    @Test
    public void test() {
        assertEquals("emocleW", spinWords("Welcome"));
        assertEquals("Hey wollef sroirraw", spinWords("Hey fellow warriors"));
    }
}

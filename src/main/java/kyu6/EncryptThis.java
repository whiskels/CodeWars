package main.java.kyu6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 6 kyu - Encrypt this!
 *
 * https://www.codewars.com/kata/5848565e273af816fb000449
 *
 * Details:
 * Encrypt this!
 *
 * You want to create secret messages which can be deciphered by the Decipher this! 
 * Here are the conditions:
 *
 *     Your message is a string containing space separated words.
 *     You need to encrypt each word in the message using the following rules:
 *         The first letter needs to be converted to its ASCII code.
 *         The second letter needs to be switched with the last letter
 *     Keepin' it simple: There are no special characters in input.
 */

public class EncryptThis {
    public static String encryptThis(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        final String[] words = text.split(" ");
        final StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                final int firstChar = word.charAt(0);
                sb.append(firstChar);

                if (word.length() > 3) {
                    final String helper = word.charAt(word.length() - 1) +
                            word.substring(2, word.length() - 1) +
                            word.charAt(1);
                    sb.append(helper);
                } else if (word.length() == 3) {
                    sb.append(word.charAt(2));
                    sb.append(word.charAt(1));
                } else if (word.length() == 2) {
                    sb.append(word.charAt(1));
                }

                sb.append(" ");
            }
        }

        sb.setLength(sb.length()-1);

        return new String(sb);
    }

    @Test
    public void exampleTests() {
        assertEquals("",
                encryptThis(""));

        assertEquals("65 119esi 111dl 111lw 108dvei 105n 97n 111ka",
                encryptThis("A wise old owl lived in an oak"));

        assertEquals("84eh 109ero 104e 115wa 116eh 108sse 104e 115eokp",
                encryptThis("The more he saw the less he spoke"));

        assertEquals("84eh 108sse 104e 115eokp 116eh 109ero 104e 104dare",
                encryptThis("The less he spoke the more he heard"));

        assertEquals("87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri",
                encryptThis("Why can we not all be like that wise old bird"));

        assertEquals("84kanh 121uo 80roti 102ro 97ll 121ruo 104ple",
                encryptThis("Thank you Piotr for all your help"));
    }
}

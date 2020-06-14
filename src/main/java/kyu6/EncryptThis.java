package main.java.kyu6;

/**
 * 6 kyu - Encrypt this!
 *
 * https://www.codewars.com/kata/5848565e273af816fb000449
 *
 * Details:
 * Encrypt this!
 *
 * You want to create secret messages which can be deciphered by the Decipher this! kata. Here are the conditions:
 *
 *     Your message is a string containing space separated words.
 *     You need to encrypt each word in the message using the following rules:
 *         The first letter needs to be converted to its ASCII code.
 *         The second letter needs to be switched with the last letter
 *     Keepin' it simple: There are no special characters in input.
 */

public class EncryptThis {
    public static String encryptThis(String text) {
        if (text == null || text.length() == 0) return new String("");
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                int firstChar = word.charAt(0);
                sb.append(firstChar);}
            if (word.length() > 3) {
                String helper = word.charAt(word.length() - 1) +
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
        sb.setLength(sb.length()-1);
        return new String(sb);
    }
}
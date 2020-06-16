package main.java.kyu4;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * 4 kyu - Recover a secret string from random triplets
 *
 * https://www.codewars.com/kata/53f40dff5f9d31b813000774/train/java
 *
 * Details:
 *
 * There is a secret string which is unknown to you. Given a collection of random triplets from the string, recover the
 * original string.
 *
 * A triplet here is defined as a sequence of three letters such that each letter occurs somewhere before the next in
 * the given string. "whi" is a triplet for the string "whatisup".
 *
 * As a simplification, you may assume that no letter occurs more than once in the secret string.
 *
 * You can assume nothing about the triplets given to you other than that they are valid triplets and that they contain
 * sufficient information to deduce the original string. In particular, this means that the secret string will never
 * contain letters that do not occur in one of the triplets given to you.
 */

public class SecretDetective {
    public String recoverSecret(char[][] triplets) {
        ArrayList<Character> result = new ArrayList<>();
        for (char[] triplet : triplets) {
            char x = triplet[0];
            char y = triplet[1];
            char z = triplet[2];
            if (!result.contains(x)) result.add(0, x);
            if (!result.contains(y)) result.add(result.indexOf(x), y);
            if (result.contains(y) && result.indexOf(y) < result.indexOf(x)) {
                int index = result.indexOf(y);
                result.remove(index);
                result.add(result.indexOf(x) + 1, y);
            }
            if (!result.contains(z)) result.add(result.indexOf(y), z);
            if (result.contains(z) && result.indexOf(z) < result.indexOf(y)) {
                int index = result.indexOf(z);
                result.remove(index);
                result.add(result.indexOf(y) + 1, z);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : result) {
            sb.append(c);
        }
        return new String(sb);
    }


    /**
     * Test section
     */

    @Test
    public void secret1() {
        SecretDetective detective = new SecretDetective();
        char[][] triplets = {
                {'t','u','p'},
                {'w','h','i'},
                {'t','s','u'},
                {'a','t','s'},
                {'h','a','p'},
                {'t','i','s'},
                {'w','h','s'}
        };
        assertEquals("whatisup", detective.recoverSecret(triplets));
    }
}
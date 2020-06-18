package main.java.kyu4;

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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SecretDetective {
    public String recoverSecret(char[][] triplets) {
        List<Character> res = new ArrayList<>();

        for (char[] triplet : triplets) {
            final char x = triplet[0];
            final char y = triplet[1];
            final char z = triplet[2];

            if (!res.contains(x)) {
                res.add(0, x);
            }

            if (!res.contains(y)) {
                res.add(res.indexOf(x), y);
            }

            if (res.contains(y) && res.indexOf(y) < res.indexOf(x)) {
                final int index = res.indexOf(y);
                res.remove(index);
                res.add(res.indexOf(x) + 1, y);
            }

            if (!res.contains(z)) {
                res.add(res.indexOf(y), z);
            }

            if (res.contains(z) && res.indexOf(z) < res.indexOf(y)) {
                final int index = res.indexOf(z);
                res.remove(index);
                res.add(res.indexOf(y) + 1, z);
            }
        }

        final StringBuilder sb = new StringBuilder();

        for (Character c : res) {
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

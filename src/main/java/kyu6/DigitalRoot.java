package main.java.kyu6;
/**
 * Digital root
 *
 * https://www.codewars.com/kata/541c8630095125aba6000c00
 *
 * Details:
 *
 * Digital root is the recursive sum of all the digits in a number.
 *
 * Given n, take the sum of the digits of n. If that value has more than one digit,
 * continue reducing in this way until a single-digit number is produced.
 * This is only applicable to the natural numbers.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitalRoot {
    public static int digital_root(int n) {
        int i = n;
        if (i / 10 == 0) {
            return i;
        } else {
            int sum = 0;
            while (i > 0) {
                sum += i % 10;
                i /= 10;
            }
            return digital_root(sum);
        }
    }

    @Test
    public void tests() {
        assertEquals("Nope!", 7, digital_root(16));
        assertEquals("Nope!", 6, digital_root(456));
    }
}

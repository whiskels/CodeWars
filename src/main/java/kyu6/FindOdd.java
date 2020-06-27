package main.java.kyu6;

/**
 * https://www.codewars.com/kata/54da5a58ea159efa38000836
 *
 * Find the odd int
 *
 * Details:
 *
 * Given an array, find the integer that appears an odd number of times.
 *
 * There will always be only one integer that appears an odd number of times.
 */

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class FindOdd {
    public static int findIt(int[] input) {
        return Arrays.stream(input)
                .distinct()
                .filter(a -> Arrays.stream(input)
                        .filter(b -> a == b).count() % 2 > 0)
                .reduce((a, b) -> {
                    throw new IllegalStateException();
                })
                .getAsInt();
    }

    @Test
    public void findTest() {
        assertEquals(5, FindOdd.findIt(new int[]{20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5}));
        assertEquals(-1, FindOdd.findIt(new int[]{1, 1, 2, -2, 5, 2, 4, 4, -1, -2, 5}));
        assertEquals(5, FindOdd.findIt(new int[]{20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5}));
        assertEquals(10, FindOdd.findIt(new int[]{10}));
        assertEquals(10, FindOdd.findIt(new int[]{1, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1}));
        assertEquals(1, FindOdd.findIt(new int[]{5, 4, 3, 2, 1, 5, 4, 3, 2, 10, 10}));
    }
}

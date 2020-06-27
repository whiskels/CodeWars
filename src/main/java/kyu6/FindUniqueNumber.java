package main.java.kyu6;

/**
 * 6 kyu - Find the unique number
 *
 * https://www.codewars.com/kata/585d7d5adb20cf33cb000235/
 *
 * Details:
 *
 * There is an array with some numbers. All numbers are equal except for one. Try to find it!
 *
 * It’s guaranteed that array contains at least 3 numbers.
 *
 * The tests contain some very huge arrays, so think about performance.
 */

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindUniqueNumber {
    private double precision = 0.0000000000001;

    public static double findUniq(double arr[]) {
        return Arrays.stream(arr)
                .distinct()
                .filter(a -> Arrays.stream(arr)
                        .filter(b -> a == b).count() == 1)
                .reduce((a, b) -> {
                    throw new IllegalStateException();
                })
                .getAsDouble();
    }

    @Test
    public void sampleTestCases() {
        assertEquals(1.0, findUniq(new double[]{0, 1, 0}), precision);
        assertEquals(2.0, findUniq(new double[]{1, 1, 1, 2, 1, 1}), precision);
    }
}

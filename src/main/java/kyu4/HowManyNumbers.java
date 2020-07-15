package main.java.kyu4;

/**
 * 4 kyu - How many numbers III?
 *
 * https://www.codewars.com/kata/5877e7d568909e5ff90017e6
 *
 * Details:
 *
 * We want to generate all the numbers of three digits where:
 *  - the sum of their digits is equal to 10.
 *  - their digits are in increasing order (the numbers may have two or more equal contiguous digits)
 *
 * The numbers that fulfill the two above constraints are: 118, 127, 136, 145, 226, 235, 244, 334
 *
 * Make a function that receives two arguments:
 *  - the sum of digits value
 *  - the desired number of digits for the numbers
 *
 * The function should output an array with three values: [1,2,3]
 * 1 - the total number of possible numbers
 * 2 - the minimum number
 * 3 - the maximum number
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HowManyNumbers {
    private static long count;
    private static long min;
    private static long max;

    public static List<Long> findAll(final int sumDigits, final int numDigits) {
        count = 0;
        min = 0;
        max = 0;

        recursiveSearch(0L, 1, sumDigits, numDigits);

        ArrayList<Long> result = new ArrayList<>();

        if (count > 0) {
            result.add(count);
            result.add(min);
            result.add(max);
        }

        return result;
    }

    private static void recursiveSearch(Long currNum, int prevDigit, int sumLeft, int digitsLeft) {
        if (sumLeft == 0 && digitsLeft == 0) {
            if (count == 0) min = currNum;
            min = min < currNum ? min : currNum;
            max = max > currNum ? max : currNum;
            count++;
        } else if (digitsLeft != 0) {
            for (int i = prevDigit; i < 10; i++) {
                recursiveSearch(10 * currNum + i, i,sumLeft - i, digitsLeft - 1);
            }
        }
    }


        /**
         * Though this code works it is not effective
         *
        public static List<Long> findAll(final int sumDigits, final int numDigits) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() != numDigits) {
            sb.append("1");
        }

        long currentLong = Long.parseLong(sb.toString());
        long lastLong = currentLong * 9;
        long count = 0, firstMatch = 0, lastMatch = 0;


        while (currentLong <= lastLong) {
            int currDigit, prevDigit = -1, digitSum = 0;
            // int currentPosition = 1;
            long i = currentLong;
            boolean isValid = true;

            while (i > 0) {
                currDigit = (int) i % 10;
                if (prevDigit != -1 && prevDigit < currDigit) {
                    isValid = false;
                    //currentLong += currentPosition * i % 10;
                    break;
                } else if (digitSum > sumDigits) {
                    isValid = false;
                    break;
                } else {
                    i /= 10;
                    //currentPosition *= 10;
                    digitSum += currDigit;
                    prevDigit = currDigit;
                }
            }
            if (sumDigits != digitSum) {
                isValid = false;
            }

            if (isValid) {
                if (firstMatch == 0) firstMatch = currentLong;
                lastMatch = currentLong;
                count++;
            }
            currentLong++;
        }

        List<Long> result = new ArrayList<>();
        if (count > 0) {
            result.add(count);
            result.add(firstMatch);
            result.add(lastMatch);
        }

        return result;
    }
         */

    @Test
    public void exampleTests() {
        assertEquals(Arrays.asList(8L, 118L, 334L), HowManyNumbers.findAll(10, 3));
        assertEquals(Arrays.asList(1L, 999L, 999L), HowManyNumbers.findAll(27, 3));
        assertEquals(new ArrayList<Long>(), HowManyNumbers.findAll(84, 4));
        assertEquals(Arrays.asList(123L, 116999L, 566666L), HowManyNumbers.findAll(35, 6));
    }

    @Test
    public void hardTests() {
        assertEquals(Arrays.asList(409L, 11112999L, 44444445L),
                HowManyNumbers.findAll(33, 8));
    }
}

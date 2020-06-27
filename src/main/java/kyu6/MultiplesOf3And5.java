package main.java.kyu6;

/**
 * 6 kyu - Multiples of 3 or 5
 *
 * https://www.codewars.com/kata/514b92a657cdc65150000006
 *
 * Details:
 *
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 *
 * Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number
 * passed in.
 *
 * Note: If the number is a multiple of both 3 and 5, only count it once.
 */

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MultiplesOf3And5 {
    public static int solution(int number) {
        return IntStream.range(1, number)
                .filter(n -> n % 3 == 0 || n % 5 == 0)
                .sum();
    }

    @Test
    public void test() {
        assertEquals(1404932684, solution(1000000));
    }
}
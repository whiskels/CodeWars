package main.java.kyu7;

/**
 * 7 kyu - Beginner Series #3 Sum of Numbers
 *
 * https://www.codewars.com/kata/55f2b110f61eb01779000053
 *
 * Details:
 *
 * Given two integers a and b, which can be positive or negative, find the sum of all the numbers
 * between including them too and return it. If the two numbers are equal return a or b.
 *
 * Note: a and b are not ordered!
 */

public class SumOfNumbers {
    public int getSum(int a, int b) {
        int result = 0;

        for (int i = Math.min(a,b); i <= Math.max(a,b); i++) {
            result += i;
        }

        return result;
    }
}

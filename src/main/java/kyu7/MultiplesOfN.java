package main.java.kyu7;

/**
 * 7 kyu - Return the first M multiples of N
 *
 * https://www.codewars.com/kata/593c9175933500f33400003e
 *
 * Details:
 *
 * Implement a function, multiples(m, n), which returns an array of the first m multiples of the
 * real number n.
 * Assume that m is a positive integer.
 */

public class MultiplesOfN {
    public static int[] multiples(int m, int n) {
        int[] result = new int[m];

        for (int i = 0; i < m; ) {
            result[i] = n * ++i;
        }

        return result;
    }
}

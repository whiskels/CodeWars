package main.java.kyu7;

/**
 * 7 kyu - Halving Sum
 *
 * https://www.codewars.com/kata/5a58d46cfd56cb4e8600009d
 *
 * Details:
 *
 * Given a positive integer n, calculate the following sum:
 *
 * n + n/2 + n/4 + n/8 + ...
 *
 * All elements of the sum are the results of integer division.
 */

public class HalvingSum {
    public int halvingSum(int n) {
        return n == 0 ? 0 : n + halvingSum( n / 2);
    }
}

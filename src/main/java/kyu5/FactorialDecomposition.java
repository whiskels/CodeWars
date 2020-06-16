package main.java.kyu5;

/**
 * 5 kyu - Factorial Decomposition
 *
 * https://www.codewars.com/kata/5a045fee46d843effa000070
 *
 * Details:
 *
 * The aim of the kata is to decompose n! (factorial n) into its prime factors.
 *
 * Prime numbers should be in increasing order. When the exponent of a prime is 1 don't put the exponent.
 *
 * Notes
 *
 *  -   the function is decomp(n) and should return the decomposition of n! into its prime factors in increasing order
 * of the primes, as a string.
 *  -   factorial can be a very big number (4000! has 12674 digits, n will go from 300 to 4000).
 *  -   In Fortran - as in any other language - the returned string is not permitted to contain any redundant trailing
 * whitespace: you can use dynamically allocated character strings.
 */

import java.util.*;

public class FactorialDecomposition {
    public static String decomp(int n) {
        if (n == 1) return "1";
        LinkedHashMap<Integer,Integer> decomposition = new LinkedHashMap<>();
        for (int i = 2; i <= n; i++) {
            int currentNum = i;
            int counter = 0;
            for (int k = 2; k <= i; ) {
                int remainder = currentNum % k;
                if (remainder != 0 || currentNum <= 1) {
                    if (counter !=0) {
                        if (decomposition.containsKey(k)) decomposition.put(k,decomposition.get(k) + counter);
                        else decomposition.put(k, counter);
                    }
                    k++;
                    counter = 0;
                } else {
                    counter++;
                    currentNum /= k;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer,Integer> entry : decomposition.entrySet()) {
            if (sb.length() != 0)  sb.append(" * ");
            sb.append(entry.getKey());
            if (entry.getValue() != 1) sb.append("^").append(entry.getValue());
        }
        return new String(sb);
    }
}
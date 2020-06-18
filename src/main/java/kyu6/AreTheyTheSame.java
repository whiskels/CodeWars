package main.java.kyu6;

/**
 * 6 kyu - Are they the "same"?
 *
 * https://www.codewars.com/kata/550498447451fbbd7600041c
 *
 * Details:
 *
 * Given two arrays a and b write a function comp(a, b) (compSame(a, b) in Clojure) that checks
 * whether the two arrays have the "same" elements, with the same multiplicities. "Same" means,
 * here, that the elements in b are the elements in a squared, regardless of the order.
 */

import java.util.Arrays;

public class AreTheyTheSame {
    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null) {
            return false;
        } else if (a.length == 0 || b.length == 0) {
            return a.length == b.length;
        } else {
            for (int i = 0; i < a.length; i++) {
                a[i] = Math.abs(a[i]);
            }

            for (int i = 0; i < b.length; i++) {
                b[i] = (int)Math.sqrt(b[i]);
            }

            Arrays.sort(a);
            Arrays.sort(b);

            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}

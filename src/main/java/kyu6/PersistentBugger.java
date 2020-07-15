package main.java.kyu6;

/**
 * 6 kyu - Persistent Bugger
 *
 * https://www.codewars.com/kata/55bf01e5a717a0d57e0000ec
 *
 * Details:
 *
 * Write a function, persistence, that takes in a positive parameter num and returns its
 * multiplicative persistence, which is the number of times you must multiply the digits in num
 * until you reach a single digit.
 */

import org.junit.Assert;
import org.junit.Test;

public class PersistentBugger {
    public static int persistence(final long n) {
        return persistenceCount(n, 0);
    }

    public static int persistenceCount(long n, int currentCount) {
        long l = n;
        if (l < 10) {
            return currentCount;
        }

        long nextN = 1;
        while (l != 0) {
            nextN *= l % 10;
            l /= 10;
        }

        return persistenceCount(nextN, ++currentCount);
    }

    @Test
    public void basicTests() {
        System.out.println("****** Basic Tests ******");
        Assert.assertEquals(3, persistence(39));
        Assert.assertEquals(0, persistence(4));
        Assert.assertEquals(2, persistence(25));
        Assert.assertEquals(4, persistence(999));
    }
}

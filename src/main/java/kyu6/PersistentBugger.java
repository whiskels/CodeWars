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

import static org.junit.Assert.*;
import org.junit.Test;

public class PersistentBugger {
    public static int persistence(final long n) {
        return persistenceCount(n, 0);
    }

    public static int persistenceCount(long n, int currentCount) {
        if (n < 10) {
            return currentCount;
        }

        long nextN = 1;
        while (n != 0) {
            nextN *= n % 10;
            n /= 10;
        }

        return persistenceCount(nextN, ++currentCount);
    }

    @Test
    public void BasicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals(3, persistence(39));
        assertEquals(0, persistence(4));
        assertEquals(2, persistence(25));
        assertEquals(4, persistence(999));
    }
}

package main.java.kyu6;

/**
 * 6 kyu - Is a number prime?
 *
 * https://www.codewars.com/kata/5262119038c0985a5b00029f
 *
 * Details:
 *
 * Define a function that takes an integer argument and returns logical value true or false
 * depending on if the integer is a prime.
 *
 * Per Wikipedia, a prime number (or a prime) is a natural number greater than 1 that has no
 * positive divisors other than 1 and itself.
 */

public class IsANumberPrime {
    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        } else if (num == 2) {
            return true;
        } else {
            final int limit = (int) Math.sqrt(num);
            for (int i = 2; i <= limit; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

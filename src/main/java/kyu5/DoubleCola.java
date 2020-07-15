package main.java.kyu5;

/**
 * 5 kyu - Double Cola
 *
 * https://www.codewars.com/kata/551dd1f424b7a4cdae0001f0
 *
 * Details:
 *
 * Sheldon, Leonard, Penny, Rajesh and Howard are in the queue for a "Double Cola" drink vending
 * machine; there are no other people in the queue. The first one in the queue (Sheldon) buys a
 * can, drinks it and doubles! The resulting two Sheldons go to the end of the queue. Then the
 * next in the queue (Leonard) buys a can, drinks it and gets to the end of the queue as two
 * Leonards, and so on.
 *
 * For example, Penny drinks the third can of cola and the queue will look like this:
 *
 * Rajesh, Howard, Sheldon, Sheldon, Leonard, Leonard, Penny, Penny
 *
 * Write a program that will return the name of the person who will drink the n-th cola.
 * Input
 *
 * The input data consist of an array which contains at least 1 name, and single integer n which
 * may go as high as the biggest number your language of choice supports (if there's such limit,
 * of course).
 * Output / Examples
 *
 * Return the single line — the name of the person who drinks the n-th can of cola. The cans are
 * numbered starting from 1.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoubleCola {
    public static String WhoIsNext(String[] names, int n) {
        int i = n;
        i--;

        final int len = names.length;
        while (i >= len) {
            i = (int) Math.floor((i - names.length) / 2);
        }

        return names[i];
    }

    @Test
    public void test1() {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = 1;
        assertEquals("Sheldon", WhoIsNext(names, n));
    }

    @Test
    public void test2() {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = 6;
        assertEquals("Sheldon", WhoIsNext(names, n));
    }
}

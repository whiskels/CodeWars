package main.java.kyu6;

/**
 * 6 kyu - Vasya Clerk
 *
 * https://www.codewars.com/kata/555615a77ebc7c2c8a0000b8
 *
 * Details:
 *
 * The new "Avengers" movie has just been released! There are a lot of people at the cinema box
 * office standing in a huge line.
 * Each of them has a single 100, 50 or 25 dollar bill. An "Avengers" ticket costs 25 dollars.
 *
 * Vasya is currently working as a clerk. He wants to sell a ticket to every single person in this
 * line.
 *
 * Can Vasya sell a ticket to every person and give change if he initially has no money and sells
 * the tickets strictly in the order people queue?
 *
 * Return YES, if Vasya can sell a ticket to every person and give change with the bills he has at
 * hand at that moment. Otherwise return NO.
 */

public class VasyaClerk {
    public static final int PRICE = 25;
    
    public static String Tickets(int[] peopleInLine) {
        int countQuarter = 0;
        int countFifty = 0;

        for (int i : peopleInLine) {
            if (i == PRICE) {
                countQuarter++;
            } else if (i == 50) {
                countQuarter--;

                if (countQuarter < 0) {
                    return "NO";
                }

                countFifty++;
            } else if (i == 100) {
                if (countFifty == 0) {
                    countQuarter -= 3;
                } else {
                    countQuarter--;
                    countFifty--;
                }
                if (countQuarter < 0 || countFifty < 0) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}

package main.java.kyu6;

/**
 * 6 kyu - Bying a car
 *
 * https://www.codewars.com/kata/554a44516729e4d80b000012/train/java
 *
 * Details:
 *
 * Let us begin with an example:
 *
 * A man has a rather old car being worth $2000. He saw a secondhand car being worth $8000. He
 * wants to keep his old car until he can buy the secondhand one.
 *
 * He thinks he can save $1000 each month but the prices of his old car and of the new one decrease
 * of 1.5 percent per month. Furthermore this percent of loss increases of 0.5 percent at the end
 * of every two months. Our man finds it difficult to make all these calculations.
 *
 * Can you help him?
 *
 * How many months will it take him to save up enough money to buy the car he wants, and how much
 * money will he have left over?
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuyingACar {
    public static int[] nbMonths(int startPriceOld, int startPriceNew, int savingPerMonth, double percentLossByMonth) {
        if (startPriceNew <= startPriceOld) {
            return new int[]{0, startPriceOld - startPriceNew};
        }

        double lossByMonth = percentLossByMonth;
        int passedMonths = 0;
        double currentDelta = startPriceOld - startPriceNew;
        double savingsLeft;

        do {
            passedMonths++;

            if (passedMonths % 2 == 0) {
                lossByMonth += 0.5;
            }

            currentDelta *= (1 - lossByMonth /   100);
            savingsLeft = passedMonths * savingPerMonth + currentDelta;

            System.out.println(String.format("End month: %d: percentLoss %.2f available %.3f",
                    passedMonths, lossByMonth, savingsLeft));
        } while (savingsLeft < 0);

        return new int[]{passedMonths, (int) Math.round(savingsLeft)};
    }


    @Test
    void test1() {
        int[] r = new int[] { 6, 766 };
        Assertions.assertArrayEquals(r, nbMonths(2000, 8000, 1000, 1.5));
    }
    @Test
    void test2() {
        int[] r = new int[] { 0, 4000 };
        Assertions.assertArrayEquals(r, nbMonths(12000, 8000, 1000, 1.5));
    }
    @Test
    void test3() {
        int[] r = new int[] { 0, 0 };
        Assertions.assertArrayEquals(r, nbMonths(8000, 8000, 1000, 1.5));
    }
}

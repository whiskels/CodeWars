package main.java.kyu3;

/**
 * 3 kyu - Make a spiral
 *
 * https://www.codewars.com/kata/534e01fbbb17187c7e0000c6
 *
 * Details:
 *
 * Your task, is to create a NxN spiral with a given size.
 */

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MakeASpiral {
    public static int[][] spiralize(int size) {
        if (size < 5) {
            return null;
        }

        return spiralize(new int[size][size]);
    }

    public static int[][] spiralize(int[][] arr) {
        arr = fill(arr);

        final int height = arr.length;
        final int width = arr[0].length;
        int lastY = height / 2;
        int lastX = width % 2 == 0 ? width / 2 - 1 : width / 2;

        // Extra variants
        if (height == 5) {
            lastX = 1;
            lastY = 3;
        } else if ((height - 5) % 4 == 0) {
            lastX--;
            lastY++;
        }

        int y = 1, x = 0, dirX = 1, dirY = 0, i = 0;
        int leftBorder = 0, rightBorder = width - 1;
        int upperBorder = 0, lowerBorder = height - 1;

        while (true) {
            if (x == rightBorder && y == upperBorder + 1) {
                x--;
                y++;
                dirX = 0;
                dirY = 1;
                upperBorder +=2;
            } else if (x == leftBorder && y == lowerBorder - 1) {
                x++;
                y--;
                dirX = 0;
                dirY = -1;
                lowerBorder -=2;
            } else if (y == lowerBorder && x == rightBorder - 1) {
                y--;
                x--;
                dirX = -1;
                dirY = 0;
                rightBorder -= 2;
            } else if (y == upperBorder && x == leftBorder + 1) {
                y++;
                x++;
                dirX = 1;
                dirY = 0;
                leftBorder +=2;
            }
            System.out.println(y + " " + x);
           arr[y][x] = 0;

            if (y == lastY && x == lastX) {
                break;
            }

            x += dirX;
            y += dirY;
            i++;
        }

        return arr;
    }

    public static int[][] fill(int[][] arr) {
        for (int[] row: arr) {
            Arrays.fill(row, 1);
        }
        return arr;
    }

    @Test
    public void test5() {
        assertArrayEquals(
                new int[][] {
                        { 1, 1, 1, 1, 1 },
                        { 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1 }
                },
                spiralize(5));
    }

    @Test
    public void test8() {
        assertArrayEquals(
                new int[][] {
                        { 1, 1, 1, 1, 1, 1, 1, 1 },
                        { 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 0, 0, 1, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 1 },
                        { 1, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 1, 1, 1, 1, 1 },
                },
                spiralize(8));
    }

}

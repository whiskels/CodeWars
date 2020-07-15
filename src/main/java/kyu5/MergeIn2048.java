package main.java.kyu5;

/**
 * 5 kyu - Merge in 2048
 *
 * https://www.codewars.com/kata/55e1990978c60e5052000011
 *
 * Details:
 *
 * Remember the game 2048?
 *
 * The main part of this game is merging identical tiles in a row.
 *
 *     Implement a function that models the process of merging all of the tile values in a single
 *     row.
 *     This function takes the array line as a parameter and returns a new array with the tile
 *     values from line slid towards the front of the array (index 0) and merged.
 *     
 *     A given tile can only merge once.
 *     Empty grid squares are represented as zeros.
 *     Your function should work on arrays containing arbitrary number of elements.
 */

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class MergeIn2048 {
    public static int[] merge(int[] line) {
        if (line == null || line.length == 0) {
            return new int[0];
        }

        compress(line);
        final boolean isMerged = add(line);

        if (isMerged) {
            compress(line);
        }

        return line;
    }

    private final static void compress(int[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                for (int j = i; j < row.length; j++) {
                    if (row[j] != 0) {
                        row[i] = row[j];
                        row[j] = 0;
                        break;
                    }
                }
            }
        }
    }

    private final static boolean add(int[] row) {
        boolean isMerged = false;

        for (int i = 0; i < row.length-1 ; i++) {
            if (row[i] == row[i + 1] && row[i] != 0) {
                row[i] = row[i] * 2;
                row[i+1] = 0;
                i++;
                isMerged = true;
            }
        }

        return isMerged;
    }

    @Test
    public void sampleTests() {
        assertArrayEquals(new int[] {4,2,0,0}, merge(new int[] {2,0,2,2}));
        assertArrayEquals(new int[] {4,4,0,0}, merge(new int[] {2,0,2,4}));
        assertArrayEquals(new int[] {4,0,0,0}, merge(new int[] {0,0,2,2}));
    }
}


package main.java.kyu4;

/**
 * 4 kyu - Sudoku Solution Validator
 *
 * https://www.codewars.com/kata/529bf0e9bdf7657179000008/
 *
 * Details:
 *
 * Sudoku is a game played on a 9x9 grid. The goal of the game is to fill all cells of the grid
 * with digits from 1 to 9, so that each column, each row, and each of the nine 3x3 sub-grids
 * (also known as blocks) contain all of the digits from 1 to 9.
 * (More info at: http://en.wikipedia.org/wiki/Sudoku)
 *
 * Sudoku Solution Validator
 *
 * Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array
 * representing a Sudoku board, and returns true if it is a valid solution, or false otherwise.
 * The cells of the sudoku board may also contain 0's, which will represent empty cells. Boards
 * containing one or more zeroes are considered to be invalid solutions.
 *
 * The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.
 */

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuSolutionValidator {
    private static int size;
    private static ArrayList<Integer> values;

    public static boolean check(int[][] sudoku) {
        size = sudoku.length;
        values = new ArrayList<>(size);
        return check(sudoku, true) && check(sudoku, false) && blocksCheck(sudoku);
    }

    private final static boolean blocksCheck(int[][] sudoku) {
        for (int y = 0; y < size; y += 3) {
            for (int x = 0; x < size; x += 3) {
                for (int j = y; j < y + 3; j++) {
                    for (int i = x; i < x + 3; i++) {
                        values.add(sudoku[j][i]);
                    }
                }
                System.out.println(values.size());

                if (!validateSection()) {
                    return false;
                }
            }
        }
        return true;
    }

    private final static boolean validateSection() {
        if (values.size() != size) {
            return false;
        }

        Collections.sort(values);

        if (values.get(0) != 1) {
            return false;
        }

        for (int i = 1; i < size; i++) {
            if (values.get(i) - values.get(i-1) != 1) {
                return false;
            }
        }

        values.clear();

        return true;
    }

    private final static boolean check(int[][] sudoku, boolean isFlipped) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int i;
                int j;

                if (isFlipped) {
                    i = x;
                    j = y;
                } else {
                    i = y;
                    j = x;
                }

                if (sudoku[i][j] == 0) {
                    return false;
                }
                values.add(sudoku[i][j]);
            }

            if (!validateSection()) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void exampleTest() {
        int[][] sudoku = {
                {9, 8, 7, 2, 3, 1, 5, 4, 6},
                {6, 5, 4, 8, 9, 7, 2, 1, 3},
                {3, 2, 1, 5, 6, 4, 8, 7, 9},
                {7, 6, 5, 9, 1, 8, 3, 2, 4},
                {4, 3, 2, 6, 7, 5, 9, 8, 1},
                {1, 9, 8, 3, 4, 2, 6, 5, 7},
                {8, 7, 6, 1, 2, 9, 4, 3, 5},
                {5, 4, 3, 7, 8, 6, 1, 9, 2},
                {2, 1, 9, 4, 5, 3, 7, 6, 8}
        };
        assertEquals(true, check(sudoku));

        sudoku[0][0]++;
        sudoku[1][1]++;
        sudoku[0][1]--;
        sudoku[1][0]--;

        assertEquals(false, check(sudoku));

        sudoku[0][0]--;
        sudoku[1][1]--;
        sudoku[0][1]++;
        sudoku[1][0]++;

        sudoku[4][4] = 0;

        assertEquals(false, check(sudoku));
    }
}

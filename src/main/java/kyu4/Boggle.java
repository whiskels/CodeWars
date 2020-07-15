package main.java.kyu4;

/**
 * 4 kyu - Boggle World Checker
 * <p>
 * https://www.codewars.com/kata/57680d0128ed87c94f000bfd/
 * <p>
 * Details:
 * <p>
 * Write a function that determines whether a string is a valid guess in a Boggle board, as per the
 * rules of Boggle.
 * <p>
 * A Boggle board is a 2D array of individual characters, e.g.:
 * <p>
 * [ ['I','L','A','W'],
 * ['B','N','G','E'],
 * ['I','U','A','O'],
 * ['A','S','R','L'] ]
 * <p>
 * Valid guesses are strings which can be formed by connecting adjacent cells (horizontally,
 * vertically, or diagonally) without re-using any previously used cells.
 * <p>
 * For example, in the above board "BINGO", "LINGO", and "ILNBIA" would all be valid guesses, while
 * "BUNGIE", "BINS", and "SINUS" would not.
 * <p>
 * Your function should take two arguments (a 2D array and a string) and return true or false
 * depending on whether the string is found in the array as per Boggle rules.
 * <p>
 * Test cases will provide various array and string sizes (squared arrays up to 150x150 and strings
 * up to 150 uppercase letters). You do not have to check whether the string is a real word or not,
 * only if it's a valid guess.
 */

public class Boggle {
    private final char[][] board;
    private final String word;

    public Boggle(final char[][] board, final String word) {
        this.board = board;
        this.word = word;
    }

    public boolean check() {
        if (word == null || word.isEmpty()) {
            return false;
        }
        int[][] usedCoords = new int[board.length][board[0].length];

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == word.charAt(0) && isMatch(x, y, 1, usedCoords))
                    return true;
            }
        }
        return false;
    }

    private boolean isMatch(int prevX, int prevY, int currentIndex, int[][] usedCoords) {
        if (currentIndex >= word.length()) return true;

        char currentChar = word.charAt(currentIndex);
        usedCoords[prevY][prevX] = 1;

        for (int y = prevY - 1; y <= prevY + 1; y++) {
            for (int x = prevX - 1; x <= prevX + 1; x++) {
                if (y >= 0 && x >= 0 && y < board.length && x < board[y].length) {
                    if (board[y][x] == currentChar && usedCoords[y][x] != 1 &&
                            isMatch(x, y, currentIndex + 1, usedCoords))
                        return true;
                }
            }
        }

        usedCoords[prevY][prevX] = 0;

        return false;
    }
}

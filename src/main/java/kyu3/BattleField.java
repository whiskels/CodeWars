package main.java.kyu3;

/**
 * 3 kyu - Battleship field validator
 *
 * https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7/java
 *
 * Details:
 *
 * Write a method that takes a field for well-known board game "Battleship" as an argument and
 * returns true if it has a valid disposition of ships, false otherwise. Argument is guaranteed
 * to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and
 * 1 if occupied by ship.
 *
 * Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has
 * a 10x10 grid containing several "ships" and objective is to destroy enemy's forces by targetting
 * individual cells on his field.mThe ship occupies one or more cells in the grid. Size and number
 * of ships may differ from version to version.
 *
 * In this kata we will use Soviet/Russian version of the game.
 *
 * Before the game begins, players set up the board and place the ships accordingly to the
 * following rules:
 *
 *   - There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers
 *   (size 2) and 4 submarines
 *     (size 1). Any additional ships are not allowed, as well as missing ships.
 *   - Each ship must be a straight line, except for submarines, which are just single cell.
 *   - The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.
 */

import org.junit.jupiter.api.*;

public class BattleField {
    private static int submarines;
    private static int destroyers;
    private static int cruiserShips;
    private static int battleShips;
    private static boolean[][] checkedCells;
    private static int[][] game;

    public static boolean fieldValidator(int[][] field) {
        game = field;
        submarines = 4;
        destroyers = 3;
        cruiserShips = 2;
        battleShips = 1;
        checkedCells = new boolean[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (!checkedCells[i][j]) {
                    if (field[i][j] == 1 && !checkPart(i, j)) {
                        return false;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }

        System.out.println("******************");

        return submarines == 0 && cruiserShips == 0 && destroyers == 0 && battleShips == 0;
    }

    private static boolean checkPart(int y, int x) {
        System.out.println(String.format("Ship part found at %d %d", y, x));

        final int neighbors = countNeighbors(y, x);

        if (neighbors > 2) {
            return false;
        } else if (neighbors == 1 && !findShip(y, x)) {
            return false;
        } else if (neighbors == 0) {
            submarines--;
            checkedCells[y][x] = true;
            System.out.println(String.format("\t- submarine count: %d", submarines));
        }

        return true;
    }

    private static int countNeighbors(int y, int x) {
        int neighbors = 0;
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (!(i == y && j == x) && isValidCoodrinates(i, j)) {
                    if (game[i][j] == 1) {
                        neighbors++;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }
        System.out.println(String.format("\t- neighbor count: %d", neighbors));
        return neighbors;
    }

    private static boolean isValidCoodrinates(int y, int x) {
        return y >= 0 &&
               x >= 0 &&
               y < game.length &&
               x < game[y].length;
    }

    private static boolean findShip(int y, int x) {
        System.out.println("\t- estimating ship size");
        int size = 1;

        int dX = 0, dY = 0, currentY = -1, currentX = -1;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (isValidCoodrinates(i, j)) {
                    if (i == y && j == x) {
                    } else if (game[i][j] == 1) {
                        if (isDiagonalNeighborPresent(i, j)) return false;
                        dY = i - y;
                        dX = j - x;
                        currentY = i + dY;
                        currentX = j + dX;
                        size++;
                        System.out.println(String.format(
                                "\t- found ship part at %d %d. Direction is %d %d", i, j, dY, dX));
                        break;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }

        if (currentX != -1) {
            for ( ; currentX >= 0 &&
                    currentX < game[0].length &&
                    currentY >= 0 &&
                    currentY < game.length; currentX += dX, currentY += dY) {
                if (game[currentY][currentX] == 1) {
                    if (isDiagonalNeighborPresent(currentY, currentX)) return false;
                    size++;
                } else {
                    if (size > 4) return false;
                    break;
                }
            }
        }

        removeShipBySize(currentY-dY, currentX-dX, dY, dX, size);

        return submarines >= 0 && cruiserShips >= 0 && destroyers >= 0 && battleShips >= 0;
    }

    private static boolean isDiagonalNeighborPresent(int y, int x) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i != y && j != x && isValidCoodrinates(i, j)) {
                    if (game[i][j] == 1) {
                        return true;
                    } else {
                        checkedCells[i][j] = true;
                    }
                }
            }
        }
        System.out.println(String.format("\t- no diagonal neighbors for %d %d", y, x));
        return false;
    }

    private static void removeShipBySize(int currentY, int currentX, int dY, int dX, int size) {
        System.out.println(String.format("\t- ship size: %d", size));

        if (size == 2) {
            destroyers--;
            System.out.println(String.format("\t- Destroyer count: %d", destroyers));
        }
        else if (size == 3) {
            cruiserShips--;
            System.out.println(String.format("\t- Cruiser count: %d", cruiserShips));
        }
        else if (size == 4) {
            battleShips--;
            System.out.println(String.format("\t- Battleship count: %d", battleShips));
        }

        while (size != 0) {
            checkedCells[currentY][currentX] = true;
            currentX -= dX;
            currentY -= dY;
            size--;
        }
    }

    @Test
    public void SampleTest() {
        int[][] battleField = {
                {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Assertions.assertEquals(true, BattleField.fieldValidator(battleField));
    }

    @Test
    public void SolutionTest() {
        int[][] battleField = {
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0}};
        Assertions.assertEquals(true, BattleField.fieldValidator(battleField));
    }

    @Test
    public void RandomTest1() {
        int[][] battleField = {
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 1, 0, 0}};
        Assertions.assertEquals(true, BattleField.fieldValidator(battleField));
    }

    @Test
    public void RandomTest2() {
        int[][] battleField = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 0, 0, 1}};
        Assertions.assertEquals(true, BattleField.fieldValidator(battleField));
    }
}

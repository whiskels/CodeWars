package main.java.kyu4;

/**
 * 4 kyu - Conway's Game of Life - Unlimited Edition
 * <p>
 * https://www.codewars.com/kata/52423db9add6f6fc39000354
 * <p>
 * Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.
 * <p>
 * The rules of the game are:
 * <p>
 * Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
 * Any live cell with more than three live neighbours dies, as if by overcrowding.
 * Any live cell with two or three live neighbours lives on to the next generation.
 * Any dead cell with exactly three live neighbours becomes a live cell.
 * <p>
 * Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood).
 * The universe is infinite in both the x and y dimensions and all cells are initially dead -
 * except for those specified in the arguments. The return value should be a 2d array cropped
 * around all of the living cells. (If there are no living cells, then return [[]].)
 */

public class ConwayLife {
    private static int height;
    private static int width;

    public static int[][] getGeneration(int[][] cells, int generations) {
        if (generations < 0) {
            return cells;
        }

        height = cells.length;
        width = cells[0].length;
        int[][] result = cells;

        for (int i = 0; i < generations; i++) {
            result = nextGeneration(result);
        }

        return result;
    }

    private static final int[][] nextGeneration(int[][] currGen) {
        int[][] expand = expand(currGen);
        int[][] nextGen = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int neighbors = countAliveNeighbors(expand, x, y);
                if (neighbors == 3 && expand[y][x] == 0) {
                    nextGen[y][x] = 1;
                } else if ((neighbors > 3 || neighbors < 2) && expand[y][x] == 1) {
                    nextGen[y][x] = 0;
                } else {
                    nextGen[y][x] = expand[y][x];
                }
            }
        }
        nextGen = crop(nextGen);

        return nextGen;
    }

    private static final int[][] expand(int[][] cells) {
        height += 2;
        width += 2;
        int[][] cellsExp = new int[height][width];

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cellsExp[y + 1][x + 1] = cells[y][x];
            }
        }

        return cellsExp;
    }

    private static final int countAliveNeighbors(int[][] cells, int currX, int currY) {
        int result = 0;

        for (int y = currY - 1; y <= currY + 1; y++) {
            for (int x = currX - 1; x <= currX + 1; x++) {
                if (y >= 0 && y < cells.length && x >= 0 && x < cells[0].length) {
                    if (y == currY && x == currX) {
                        continue;
                    }
                    if (cells[y][x] == 1) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static int[][] crop(int[][] cells) {
        int minY = cells.length;
        int minX = cells[0].length;
        int maxY = 0;
        int maxX = 0;

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[0].length; x++) {
                if (cells[y][x] == 1) {
                    maxY = y > maxY ? y : maxY;
                    maxX = x > maxX ? x : maxX;
                    minY = y < minY ? y : minY;
                    minX = x < minX ? x : minX;
                }
            }
        }

        maxY++;
        maxX++;
        height = maxY - minY;
        width = maxX - minX;

        int[][] cellsCrop = new int[height][width];
        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                cellsCrop[y - minY][x - minX] = cells[y][x];
            }
        }

        return cellsCrop;
    }
}

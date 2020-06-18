package main.java.kyu6;

/**
 * 6 kyu - Take a Ten Minute Walk
 *
 * https://www.codewars.com/kata/54da539698b8a2ad76000228
 *
 * Details:
 *
 * You live in the city of Cartesia where all roads are laid out in a perfect grid. You arrived ten
 * minutes too early to an appointment, so you decided to take the opportunity to go for a short
 * walk. The city provides its citizens with a Walk Generating App on their phones -- every time
 * you press the button it sends you an array of one-letter strings representing directions to
 * walk (eg. ['n', 's', 'w', 'e']). You always walk only a single block in a direction and you know
 * it takes you one minute to traverse one city block, so create a function that will return true
 * if the walk the app gives you will take you exactly ten minutes (you don't want to be early or
 * late!) and will, of course, return you to your starting point. Return false otherwise.
 *
 *     Note: you will always receive a valid array containing a random assortment of direction
 *     letters ('n', 's', 'e', or 'w' only). It will never give you an empty array (that's not
 *     a walk, that's standing still!).
 */

public class TakeATenMinuteWalk {

    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }

        int x = 0;
        int y = 0;

        for (Character c : walk) {
            switch (c) {
                case 'n':
                    y++;
                    break;
                case 's':
                    y--;
                    break;
                case 'e':
                    x--;
                    break;
                case 'w':
                    x++;
                    break;
                default :
                    break;
            }
        }

        return x == 0 && y == 0;
    }
}

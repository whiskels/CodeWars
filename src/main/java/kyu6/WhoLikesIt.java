package main.java.kyu6;

/**
 * 6 kyu - Who likes it?
 *
 * https://www.codewars.com/kata/5266876b8f4bf2da9b000362
 *
 * Details:
 *
 * ou probably know the "like" system from Facebook and other pages. People can "like" blog posts,
 * pictures or other items. We want to create the text that should be displayed next to such an
 * item.
 *
 * Implement a function likes :: [String] -> String, which must take in input array, containing the
 * names of people who like an item. It must return the display text as shown in the examples
 */

public class WhoLikesIt {
    public static String whoLikesIt(String... names) {
        final int len = names.length;

        switch (len) {
            case 0:
                return "no one likes this";
            case 1:
                return String.format("%s likes this", names[0]);
            case 2:
                return String.format("%s and %s like this", names[0], names[1]);
            case 3:
                return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
            default:
                return String.format("%s, %s and %d others like this", names[0], names[1], len-2);
        }
    }
}

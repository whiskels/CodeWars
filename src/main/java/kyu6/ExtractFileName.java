package main.java.kyu6;

/**
 * 6 kyu - extract file name
 *
 * https://www.codewars.com/kata/597770e98b4b340e5b000071
 *
 * Details:
 *
 * You have to extract a portion of the file name as follows:
 *
 *     Assume it will start with date represented as long number
 *     Followed by an underscore
 *     You'll have then a filename with an extension
 *     it will always have an extra extension at the end
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractFileName {
    public static String extractFileName(String dirtyFileName) {
        final Pattern pattern = Pattern.compile("_(.+)\\.");
        final Matcher matcher = pattern.matcher(dirtyFileName);

        matcher.find();

        return matcher.group(1);
    }
}

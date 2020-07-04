package main.java.kyu4;

/**
 * 4 kyu - Most frequently used words in a text
 * <p>
 * https://www.codewars.com/kata/51e056fe544cf36c410000fb/
 * <p>
 * Details:
 * <p>
 * Write a function that, given a string of text (possibly with punctuation and line-breaks),
 * returns an array of the top-3 most occurring words, in descending order of the number of occurrences.
 * Assumptions:
 * <p>
 * A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in
 * ASCII. (No need to handle fancy punctuation.)
 * Matches should be case-insensitive, and the words in the result should be lowercased.
 * Ties may be broken arbitrarily.
 * If a text contains fewer than three unique words, then either the top-2 or top-1 words
 * should be returned, or an empty array if a text contains no words.
 */

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TopWords {
    public static final int LIMIT = 3;

    public static List<String> top3(String string) {
        final Map<String, Integer> wordsCount = new HashMap<>();

        Pattern pattern = Pattern.compile("[A-Za-z][A-Za-z']*");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String s = matcher.group().toLowerCase();
            wordsCount.put(s, wordsCount.getOrDefault(s, 1) + 1);
        }

        List<Map.Entry<String, Integer>> topWords = new ArrayList<>(wordsCount.entrySet());
        topWords.sort(Map.Entry.comparingByValue());
        ArrayList<String> result = new ArrayList<>();
        int j = 0;
        for (int i = topWords.size() - 1; i >= 0; i--) {
            String s = topWords.get(i).getKey();
            if (j > 2 || s == null || s.length() == 0) {
                break;
            }
            if (Character.isAlphabetic(s.charAt(0))) {
                result.add(j, topWords.get(i).getKey());
                j++;
            }
        }

        result.trimToSize();
        return result;
    }

    public static List<String> top3_streams(String string) {
        final Pattern pattern = Pattern.compile("[A-Za-z][A-Za-z']*");
        final Matcher matcher = pattern.matcher(string.toLowerCase());
        final Map<String, Integer> wordsCount = new HashMap<>();

        while (matcher.find()) {
            String s = matcher.group();
            wordsCount.put(s, wordsCount.getOrDefault(s, 1) + 1);
        }

        return wordsCount.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(LIMIT)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
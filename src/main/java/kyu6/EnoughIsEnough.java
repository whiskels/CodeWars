package main.java.kyu6;

/** 6 kyu - Delete occurrences of an element if it occurs more than n times
 *
 * https://www.codewars.com/kata/554ca54ffa7d91b236000023
 *
 * Details:
 *
 * Alice and Bob were on a holiday. Both of them took many pictures of the places they've been, and
 * now they want to show Charlie their entire collection. However, Charlie doesn't like these
 * sessions, since the motive usually repeats. He isn't fond of seeing the Eiffel tower 40 times.
 * He tells them that he will only sit during the session if they show the same motive at most N
 * times. Luckily, Alice and Bob are able to encode the motive as a number. Can you help them to
 * remove numbers such that their list contains each number only up to N times, without changing
 * the order?
 * Task
 *
 * Given a list lst and a number N, create a new list that contains each number of lst at most N
 * times without reordering. For example if N = 2, and the input is [1,2,3,1,2,1,2,3], you take
 * [1,2,3,1,2], drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times,
 * and then take 3, which leads to [1,2,3,1,2,3].
 */

import java.util.*;

public class EnoughIsEnough {
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        if (elements.length == 0 || maxOccurrences == 0) {
            return new int[0];
        }

        final List<Integer> elemList = new ArrayList<>(elements.length);
        final ArrayList<Integer> filteredElements = new ArrayList<>();

        for (int e : elements) {
            elemList.add(e);
        }

        for (int e : elements) {
            if (filteredElements.contains(e)) {
                final int currentCount = Collections.frequency(filteredElements,e);

                if (currentCount < maxOccurrences) {
                    filteredElements.add(e);
                }
            } else {
                filteredElements.add(e);
            }
        }

        int[] result = new int[filteredElements.size()];

        for (int i = 0; i < filteredElements.size(); i++) {
            result[i] = filteredElements.get(i);
        }

        return result;
    }
}

package main.java.kyu4;

/**
 * 4 kyu - Human readable duration format
 *
 * https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/java
 *
 * Details:
 *
 * our task in order to complete this Kata is to write a function which formats a duration, given
 * as a number of seconds, in a human-friendly way.
 *
 * The function must accept a non-negative integer. If it is zero, it just returns "now".
 * Otherwise, the duration is expressed as a combination of years, days, hours, minutes and
 * seconds.
 *
 * For the purpose of this Kata, a year is 365 days and a day is 24 hours.
 *
 * Note that spaces are important.
 * Detailed rules
 *
 * The resulting expression is made of components like 4 seconds, 1 year, etc. In general, a
 * positive integer and one of the valid units of time, separated by a space. The unit of time is
 * used in plural if the integer is greater than 1.
 *
 * The components are separated by a comma and a space (", "). Except the last component, which is
 * separated by " and ", just like it would be written in English.
 *
 * A more significant units of time will occur before than a least significant one. Therefore, 1
 * second and 1 year is not correct, but 1 year and 1 second is.
 *
 * Different components have different unit of times. So there is not repeated units like in 5
 * seconds and 1 second.
 *
 * A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0
 * seconds is not valid, but it should be just 1 minute.
 *
 * A unit of time must be used "as much as possible". It means that the function should not return
 *  61 seconds, but 1 minute and 1 second instead. Formally, the duration specified by of a
 *  component must not be greater than any valid more significant unit of time.
 */

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeFormatter {
    static class TimeUnit {
        private String name;
        private     int value;

        TimeUnit(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public static String formatDuration(int seconds) {
        if (seconds <= 0) {
            return "now";
        }

        List<TimeUnit> time = new ArrayList<>();
        time.add(new TimeUnit("year", (seconds / (60 * 60 * 24 * 365))));
        time.add(new TimeUnit("day", (seconds / (60 * 60 * 24)) % 365));
        time.add(new TimeUnit("hour", (seconds / (60 * 60)) % 24));
        time.add(new TimeUnit("minute", (seconds / 60) % 60));
        time.add(new TimeUnit("second", seconds % 60));

        time.removeIf(timeUnit -> timeUnit.value == 0);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < time.size(); i++) {
            final TimeUnit unit = time.get(i);

            sb.append(unit.value)
              .append(" ")
              .append(unit.name);

            if (unit.value > 1) {
                sb.append("s");
            } else if (i < time.size() - 2) {
                sb.append(", ");
            } else if (i == time.size() - 2) {
                sb.append(" and ");
            }
        }

        return new String(sb);
    }

    @Test
    public void exampleTests() {
        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("1 hour", TimeFormatter.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
    }
}

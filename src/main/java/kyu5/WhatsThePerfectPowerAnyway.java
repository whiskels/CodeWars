    package main.java.kyu5;

    /**
     * 5 kyu - What's the Perfect Power anyway?
     * <p>
     * https://www.codewars.com/kata/54d4c8b08776e4ad92000835
     * <p>
     * Details:
     * <p>
     * A perfect power is a classification of positive integers:
     * <p>
     * In mathematics, a perfect power is a positive integer that can be expressed as an integer
     * power of another positive integer. More formally, n is a perfect power if there exist
     * natural numbers m > 1, and k > 1 such that mk = n.
     * <p>
     * Your task is to check wheter a given integer is a perfect power. If it is a perfect power,
     * return a pair m and k with mk = n as a proof. Otherwise return Nothing, Nil, null, NULL, None
     * or your language's equivalent.
     * <p>
     * Note: For a perfect power, there might be several pairs. For example 81 = 3^4 = 9^2, so (3,4)
     * and (9,2) are valid solutions. However, the tests take care of this, so if a number is a perfect
     * power, return any pair that proves it.
     */

    import org.junit.Assert;
    import org.junit.jupiter.api.Test;

    import java.util.Random;

    import static java.lang.Math.log;


    public class WhatsThePerfectPowerAnyway {
        public static int[] isPerfectPower(int n) {
            final double sqrt = Math.sqrt(n);
            for (int i = 2, j = 2; i <= sqrt; ) {
                final int currentPow = (int) Math.pow(i, j);

                if (currentPow == n) {
                    return new int[]{i, j};
                } else if (currentPow > n) {
                    i++;
                    j = 2;
                } else {
                    j++;
                }
            }
            return null;
        }

        private static int ipow(int b, int e) {
            int i = b;
            int p = 1;
            for (; e > 0; e >>= 1) {
                if ((e & 1) == 1) p *= i;
                i *= i;
            }
            return p;
        }

        @Test
        public void test0() {
            Assert.assertNull("0 is not a perfect number", isPerfectPower(0));
        }

        @Test
        public void test1() {
            Assert.assertNull("1 is not a perfect number", isPerfectPower(1));
        }

        @Test
        public void test2() {
            Assert.assertNull("2 is not a perfect number", isPerfectPower(2));
        }

        @Test
        public void test3() {
            Assert.assertNull("3 is not a perfect number", isPerfectPower(3));
        }

        @Test
        public void test4() {
            Assert.assertArrayEquals("4 = 2^2", new int[]{2, 2}, isPerfectPower(4));
        }

        @Test
        public void test5() {
            Assert.assertNull("5 is not a perfect power", isPerfectPower(5));
        }

        @Test
        public void test8() {
            Assert.assertArrayEquals("8 = 2^3", new int[]{2, 3}, isPerfectPower(8));
        }

        @Test
        public void test9() {
            Assert.assertArrayEquals("9 = 3^2", new int[]{3, 2}, isPerfectPower(9));
        }

        @Test
        public void testUpTo500() {
            int[] pp = {4, 8, 9, 16, 25, 27, 32, 36, 49, 64, 81, 100, 121, 125, 128, 144, 169, 196, 216, 225, 243, 256, 289, 324, 343, 361, 400, 441, 484};
            for (int i : pp) Assert.assertNotNull(i + " is a perfect power", isPerfectPower(i));
        }

        @Test
        public void testRandomPerfectPowers() {
            Random rnd = new Random();
            for (int i = 0; i < 100; i++) {
                int m = rnd.nextInt(254) + 2;
                int k = (int) (rnd.nextDouble() * (log(Integer.MAX_VALUE) / log(m) - 2.0) + 2.0);
                int l = ipow(m, k);
                int[] r = isPerfectPower(l);
                Assert.assertNotNull(l + " is a perfect power", r);
                Assert.assertEquals(r[0] + "^" + r[1] + "!=" + l, l, ipow(r[0], r[1]));
            }
        }

        @Test
        public void testRandomNumbers() {
            Random rnd = new Random();
            for (int i = 0; i < 100; i++) {
                int l = rnd.nextInt(Integer.MAX_VALUE);
                int[] r = isPerfectPower(l);
                if (r != null) Assert.assertEquals(r[0] + "^" + r[1] + "!=" + l, l, ipow(r[0], r[1]));
            }
        }
    }

package ue_8.s2410238049;

public class Comparison {
    public static boolean arrayCompare(byte[] a1, byte[] a2) {
        int i = 0;
        while (i < a1.length && a1[i] == a2[i]) {
            i = i + 1;
        }
        return i == a1.length;
    }

    public static int minLoopCount(int length) {
        return 0;
    }

    public static int maxLoopCount(int length) {
        return length < 0 ? 0 : length;
    }

    public static double expectedLoopCount(int length, int alphabetSize) {
        if (length <= 0) {
            return 0.0;
        }
        if (alphabetSize <= 0) {
            throw new IllegalArgumentException("alphabetSize must be > 0");
        }
        double p = 1.0 / alphabetSize;
        double exp = 0.0;
        double pk = 1.0;
        for (int k = 0; k < length; k++) {
            exp += k * pk * (1.0 - p);
            pk *= p;
        }
        exp += length * pk;
        return exp;
    }
}

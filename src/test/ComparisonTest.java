package test;

import ue_8.s2410238049.Comparison;

public class ComparisonTest {
    public static void main(String[] args) {
        System.out.println("=== Comparison Test ===");
        testArrayCompare();
        testMinMax();
        testExpected();
        System.out.println("=== Comparison Tests fertig ===");
    }

    private static void testArrayCompare() {
        System.out.println("\n--- testArrayCompare ---");
        byte[] a1 = new byte[]{1, 2, 3, 4};
        byte[] a2 = new byte[]{1, 2, 3, 4};
        byte[] a3 = new byte[]{1, 2, 9, 4};
        byte[] a4 = new byte[]{1, 2, 3, 4, 5};

        assertEquals(true, Comparison.arrayCompare(a1, a2), "arrayCompare equal");
        assertEquals(false, Comparison.arrayCompare(a1, a3), "arrayCompare mismatch");
        assertEquals(true, Comparison.arrayCompare(a1, a4), "arrayCompare prefix-equal (a2 longer)");
    }

    private static void testMinMax() {
        System.out.println("\n--- testMinMax ---");
        assertEquals(0, Comparison.minLoopCount(10), "minLoopCount(10)");
        assertEquals(10, Comparison.maxLoopCount(10), "maxLoopCount(10)");
        assertEquals(0, Comparison.minLoopCount(0), "minLoopCount(0)");
        assertEquals(0, Comparison.maxLoopCount(0), "maxLoopCount(0)");
    }

    private static void testExpected() {
        System.out.println("\n--- testExpected ---");
        double expected = 0.04;
        double actual = Comparison.expectedLoopCount(10, 26);
        assertNearlyEquals(expected, actual, 1e-12, "expectedLoopCount(10,26)");
    }

    private static void assertEquals(Object expected, Object actual, String label) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual);
        }
    }

    private static void assertNearlyEquals(double expected, double actual, double eps, String label) {
        boolean ok = Math.abs(expected - actual) <= eps;
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + ", eps: " + eps + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual + " eps=" + eps);
        }
    }
}

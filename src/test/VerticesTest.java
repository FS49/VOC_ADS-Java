package test;

import ue_7.s2410238049.Vertices;

public class VerticesTest {

    public static void main(String[] args) {
        System.out.println("=== Vertices Test ===");
        testStartEqualsEnd();
        testSimplePaths();
        testNoPath();
        testCycles();
        System.out.println("=== Vertices Tests fertig ===");
    }

    private static void testStartEqualsEnd() {
        System.out.println("\n--- testStartEqualsEnd ---");
        Vertices v = new Vertices();
        int[][] m = new int[][]{
                {0, 1},
                {0, 0}
        };
        assertEquals(true, v.existPath(m, 0, 0), "start==end");
        assertEquals(true, v.existPath(m, 1, 1), "start==end 2");
    }

    private static void testSimplePaths() {
        System.out.println("\n--- testSimplePaths ---");
        Vertices v = new Vertices();
        int[][] m = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        assertEquals(true, v.existPath(m, 0, 3), "0->3");
        assertEquals(true, v.existPath(m, 1, 3), "1->3");
        assertEquals(false, v.existPath(m, 3, 0), "3->0");
    }

    private static void testNoPath() {
        System.out.println("\n--- testNoPath ---");
        Vertices v = new Vertices();
        int[][] m = new int[][]{
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        assertEquals(false, v.existPath(m, 0, 3), "0->3 none");
        assertEquals(true, v.existPath(m, 2, 3), "2->3");
    }

    private static void testCycles() {
        System.out.println("\n--- testCycles ---");
        Vertices v = new Vertices();
        int[][] m = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };
        assertEquals(true, v.existPath(m, 0, 4), "0->4 with cycle");
        assertEquals(false, v.existPath(m, 4, 0), "4->0 none");
    }

    private static void assertEquals(Object expected, Object actual, String label) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual);
        }
    }
}

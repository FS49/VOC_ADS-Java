package test;

import ue_5.s2410238049.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        System.out.println("=== PriorityQueue Test ===\n");

        testEmpty();
        testInsertSingle();
        testPriorityOrdering();
        testFifoSamePriority();
        testMixedPrioritiesFifoInsideGroups();
        testPeekMin();
        testInsertAfterExtract();
        testNegativePriorities();
        testExtremePriorities();
        testSameObjectInsertedMultipleTimes();
        testManyElementsScenario();
        testNullData();

        System.out.println("\n=== Tests fertig ===");
    }

    private static void testEmpty() {
        System.out.println("--- testEmpty ---");

        PriorityQueue pq = new PriorityQueue();

        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println("peekMin: " + pq.peekMin() + " (erwartet: null)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: null)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testInsertSingle() {
        System.out.println("--- testInsertSingle ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("A", 5);

        System.out.println("peekMin: " + pq.peekMin() + " (erwartet: A)");
        System.out.println("empty: " + pq.empty() + " (erwartet: false)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: A)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: null)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testPriorityOrdering() {
        System.out.println("--- testPriorityOrdering ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("low", 10);
        pq.insert("high", 1);
        pq.insert("mid", 5);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: high)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: mid)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: low)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testFifoSamePriority() {
        System.out.println("--- testFifoSamePriority ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("A", 3);
        pq.insert("B", 3);
        pq.insert("C", 3);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: A)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: B)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: C)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testMixedPrioritiesFifoInsideGroups() {
        System.out.println("--- testMixedPrioritiesFifoInsideGroups ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("p1_A", 1);
        pq.insert("p3_A", 3);
        pq.insert("p1_B", 1);
        pq.insert("p2_A", 2);
        pq.insert("p3_B", 3);
        pq.insert("p2_B", 2);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p1_A)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p1_B)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p2_A)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p2_B)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p3_A)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: p3_B)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testPeekMin() {
        System.out.println("--- testPeekMin ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("X", 10);
        pq.insert("Y", 2);
        pq.insert("Z", 7);

        System.out.println("peekMin: " + pq.peekMin() + " (erwartet: Y)");
        System.out.println("peekMin: " + pq.peekMin() + " (erwartet: Y)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: Y)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: Z)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: X)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testInsertAfterExtract() {
        System.out.println("--- testInsertAfterExtract ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("A", 4);
        pq.insert("B", 1);
        pq.insert("C", 3);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: B)");

        pq.insert("D", 2);
        pq.insert("E", 1);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: E)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: D)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: C)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: A)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testNegativePriorities() {
        System.out.println("--- testNegativePriorities ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("P0", 0);
        pq.insert("N1", -1);
        pq.insert("N10", -10);
        pq.insert("P5", 5);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: N10)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: N1)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: P0)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: P5)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testExtremePriorities() {
        System.out.println("--- testExtremePriorities ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("MAX", Integer.MAX_VALUE);
        pq.insert("MIN", Integer.MIN_VALUE);
        pq.insert("ZERO", 0);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: MIN)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: ZERO)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: MAX)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testSameObjectInsertedMultipleTimes() {
        System.out.println("--- testSameObjectInsertedMultipleTimes ---");

        PriorityQueue pq = new PriorityQueue();
        Object shared = new Object();

        pq.insert(shared, 2);
        pq.insert("X", 1);
        pq.insert(shared, 2);
        pq.insert(shared, 2);

        Object a = pq.extractMin();
        Object b = pq.extractMin();
        Object c = pq.extractMin();
        Object d = pq.extractMin();

        System.out.println("extractMin: " + a + " (erwartet: X)");
        System.out.println("extractMin shared: " + (b == shared) + " (erwartet: true)");
        System.out.println("extractMin shared: " + (c == shared) + " (erwartet: true)");
        System.out.println("extractMin shared: " + (d == shared) + " (erwartet: true)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testManyElementsScenario() {
        System.out.println("--- testManyElementsScenario ---");

        PriorityQueue pq = new PriorityQueue();

        pq.insert("e5_1", 5);
        pq.insert("e1_1", 1);
        pq.insert("e4_1", 4);
        pq.insert("e1_2", 1);
        pq.insert("e3_1", 3);
        pq.insert("e2_1", 2);
        pq.insert("e2_2", 2);
        pq.insert("e5_2", 5);

        System.out.println("Reihenfolge extractMin:");
        System.out.println("  " + pq.extractMin() + " (erwartet: e1_1)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e1_2)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e2_1)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e2_2)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e3_1)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e4_1)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e5_1)");
        System.out.println("  " + pq.extractMin() + " (erwartet: e5_2)");

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: null)");
        System.out.println("peekMin: " + pq.peekMin() + " (erwartet: null)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testNullData() {
        System.out.println("--- testNullData ---");

        PriorityQueue pq = new PriorityQueue();
        pq.insert("A", 2);
        pq.insert(null, 1);
        pq.insert("B", 1);

        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: null)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: B)");
        System.out.println("extractMin: " + pq.extractMin() + " (erwartet: A)");
        System.out.println("empty: " + pq.empty() + " (erwartet: true)");
        System.out.println();
    }
}

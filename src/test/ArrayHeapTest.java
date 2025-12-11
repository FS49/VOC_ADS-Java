package test;

import lecture.chapter03.tests.IntegerComparator;
import lecture.chapter03.tests.IntegerKey;
import ue_4.s2410238049.ArrayHeap;

public class ArrayHeapTest {

    public static void main(String[] args) {
        System.out.println("=== ArrayHeap Test ===\n");

        testInsertSingle();
        testInsertMultiple();
        testInsertDescending();
        testInsertRandom();
        testPeekMin();
        testRemoveMin();
        testRemoveMinAll();
        testSearchIndex();
        testSearchIndexNotFound();
        testRemoveElement();
        testRemoveRoot();
        testRemoveNonExisting();
        testRemoveAll();
        testIsHeap();
        testCapacityGrowth();

        System.out.println("\n=== Tests fertig ===");
    }

    private static void testInsertSingle() {
        System.out.println("--- testInsertSingle ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        heap.insert(42);

        System.out.println("Nach insert(42): " + heap);
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 42)");
        System.out.println("size: " + heap.size() + " (erwartet: 1)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testInsertMultiple() {
        System.out.println("--- testInsertMultiple ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(4);

        System.out.println("Nach insert(5,3,8,1,4): " + heap);
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 1)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testInsertDescending() {
        System.out.println("--- testInsertDescending ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        for (int i = 10; i >= 1; i--) {
            heap.insert(i);
        }

        System.out.println("Nach insert(10,9,8,...,1): " + heap);
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 1)");
        System.out.println("size: " + heap.size() + " (erwartet: 10)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testInsertRandom() {
        System.out.println("--- testInsertRandom ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        int[] values = {15, 10, 20, 17, 8, 25, 3, 12};
        for (int v : values) {
            heap.insert(v);
        }

        System.out.println("Nach insert(15,10,20,17,8,25,3,12): " + heap);
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 3)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testPeekMin() {
        System.out.println("--- testPeekMin ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        System.out.println("peekMin bei leerem Heap: " + heap.peekMin() + " (erwartet: null)");

        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

        System.out.println("Nach insert(5,3,7): " + heap);
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 3)");
        System.out.println("size nach peekMin: " + heap.size() + " (erwartet: 3, keine Änderung)");
        System.out.println();
    }

    private static void testRemoveMin() {
        System.out.println("--- testRemoveMin ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Start: " + heap);

        Object min = heap.removeMin();
        System.out.println("removeMin: " + min + " (erwartet: 1)");
        System.out.println("Nach removeMin: " + heap);
        System.out.println("neues peekMin: " + heap.peekMin() + " (erwartet: 3)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testRemoveMinAll() {
        System.out.println("--- testRemoveMinAll ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Start: " + heap);
        System.out.println("Elemente in aufsteigender Reihenfolge extrahieren:");

        StringBuilder extracted = new StringBuilder();
        while (!heap.isEmpty()) {
            Object min = heap.removeMin();
            if (extracted.length() > 0) extracted.append(" ");
            extracted.append(min);
            System.out.println("  removeMin: " + min + " | Rest: " + heap + " | isHeap: " + heap.isHeap());
        }

        System.out.println("Extrahierte Reihenfolge: " + extracted);
        System.out.println("Erwartet sortiert: 1 3 5 7 8 10");
        System.out.println();
    }

    private static void testSearchIndex() {
        System.out.println("--- testSearchIndex ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Heap: " + heap);

        IntegerKey key5 = new IntegerKey(5);
        int idx = heap.searchIndex(key5);
        System.out.println("searchIndex(5): " + idx + " (erwartet: >= 0)");

        IntegerKey key1 = new IntegerKey(1);
        idx = heap.searchIndex(key1);
        System.out.println("searchIndex(1) (Wurzel): " + idx + " (erwartet: 0)");

        IntegerKey key10 = new IntegerKey(10);
        idx = heap.searchIndex(key10);
        System.out.println("searchIndex(10): " + idx + " (erwartet: >= 0)");
        System.out.println();
    }

    private static void testSearchIndexNotFound() {
        System.out.println("--- testSearchIndexNotFound ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Heap: " + heap);

        IntegerKey key99 = new IntegerKey(99);
        int idx = heap.searchIndex(key99);
        System.out.println("searchIndex(99): " + idx + " (erwartet: -1)");

        IntegerKey key0 = new IntegerKey(0);
        idx = heap.searchIndex(key0);
        System.out.println("searchIndex(0): " + idx + " (erwartet: -1)");
        System.out.println();
    }

    private static void testRemoveElement() {
        System.out.println("--- testRemoveElement ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Vor remove(5): " + heap);

        heap.remove(5);
        System.out.println("Nach remove(5): " + heap);
        System.out.println("isHeap: " + heap.isHeap());

        IntegerKey key5 = new IntegerKey(5);
        System.out.println("5 noch vorhanden? " + (heap.searchIndex(key5) != -1) + " (erwartet: false)");
        System.out.println();
    }

    private static void testRemoveRoot() {
        System.out.println("--- testRemoveRoot ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Vor remove(1): " + heap);

        heap.remove(1);
        System.out.println("Nach remove(1) (Wurzel): " + heap);
        System.out.println("neues peekMin: " + heap.peekMin());
        System.out.println("isHeap: " + heap.isHeap());

        IntegerKey key1 = new IntegerKey(1);
        System.out.println("1 noch vorhanden? " + (heap.searchIndex(key1) != -1) + " (erwartet: false)");
        System.out.println();
    }

    private static void testRemoveNonExisting() {
        System.out.println("--- testRemoveNonExisting ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Vor remove(99): " + heap);

        heap.remove(99);
        System.out.println("Nach remove(99): " + heap);
        System.out.println("size: " + heap.size() + " (erwartet: 6, keine Änderung)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testRemoveAll() {
        System.out.println("--- testRemoveAll ---");

        ArrayHeap heap = createTestHeap();
        System.out.println("Start: " + heap);

        int[] toRemove = {5, 1, 10, 3, 8, 7};
        for (int v : toRemove) {
            heap.remove(v);
            System.out.println("Nach remove(" + v + "): " + heap + " | size: " + heap.size() + " | isHeap: " + heap.isHeap());
        }

        System.out.println("isEmpty: " + heap.isEmpty() + " (erwartet: true)");
        System.out.println();
    }

    private static void testIsHeap() {
        System.out.println("--- testIsHeap ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        System.out.println("Leerer Heap isHeap: " + heap.isHeap() + " (erwartet: true)");

        heap.insert(1);
        System.out.println("Ein Element isHeap: " + heap.isHeap() + " (erwartet: true)");

        heap.insert(2);
        heap.insert(3);
        System.out.println("Drei Elemente isHeap: " + heap.isHeap() + " (erwartet: true)");

        for (int i = 4; i <= 15; i++) {
            heap.insert(i);
        }
        System.out.println("15 Elemente isHeap: " + heap.isHeap() + " (erwartet: true)");
        System.out.println("Heap: " + heap);
        System.out.println();
    }

    private static void testCapacityGrowth() {
        System.out.println("--- testCapacityGrowth ---");

        ArrayHeap heap = new ArrayHeap(new IntegerComparator(), 4);
        System.out.println("Initiale Kapazität: 4");

        for (int i = 20; i >= 1; i--) {
            heap.insert(i);
        }

        System.out.println("Nach insert von 20 Elementen: size = " + heap.size());
        System.out.println("peekMin: " + heap.peekMin() + " (erwartet: 1)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println("Heap: " + heap);
        System.out.println();
    }

    private static ArrayHeap createTestHeap() {
        ArrayHeap heap = new ArrayHeap(new IntegerComparator());
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(7);
        heap.insert(10);
        return heap;
    }
}


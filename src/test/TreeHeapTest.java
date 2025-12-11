package test;

import lecture.chapter03.BTree;
import lecture.chapter03.IWorker;
import lecture.chapter03.tests.IntegerComparator;
import lecture.chapter03.tests.IntegerKey;
import ue_4.s2410238049.TreeHeap;

public class TreeHeapTest {

    public static void main(String[] args) {
        System.out.println("=== TreeHeap Test ===\n");

        testInsertSingle();
        testInsertMultiple();
        testInsertDescending();
        testInsertRandom();
        testHeapSearch();
        testHeapSearchNotFound();
        testRemoveLeaf();
        testRemoveRoot();
        testRemoveMiddle();
        testRemoveNonExisting();
        testRemoveAll();
        testRemoveMin();
        testRemoveMinAll();
        testIsHeap();

        System.out.println("\n=== Tests fertig ===");
    }

    private static void testInsertSingle() {
        System.out.println("--- testInsertSingle ---");

        TreeHeap heap = new TreeHeap(new IntegerComparator());
        heap.insert(42);

        System.out.println("Nach insert(42): " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println("Höhe: " + heap.height());
        System.out.println();
    }

    private static void testInsertMultiple() {
        System.out.println("--- testInsertMultiple ---");

        TreeHeap heap = new TreeHeap(new IntegerComparator());
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(4);

        System.out.println("Nach insert(5,3,8,1,4): " + bfs(heap));
        System.out.println("Erwartet: Wurzel = 1 (kleinstes Element)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testInsertDescending() {
        System.out.println("--- testInsertDescending ---");

        TreeHeap heap = new TreeHeap(new IntegerComparator());
        for (int i = 10; i >= 1; i--) {
            heap.insert(i);
        }

        System.out.println("Nach insert(10,9,8,...,1): " + bfs(heap));
        System.out.println("Erwartet: Wurzel = 1");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println("Höhe: " + heap.height());
        System.out.println();
    }

    private static void testInsertRandom() {
        System.out.println("--- testInsertRandom ---");

        TreeHeap heap = new TreeHeap(new IntegerComparator());
        int[] values = {15, 10, 20, 17, 8, 25, 3, 12};
        for (int v : values) {
            heap.insert(v);
        }

        System.out.println("Nach insert(15,10,20,17,8,25,3,12): " + bfs(heap));
        System.out.println("Erwartet: Wurzel = 3 (kleinstes Element)");
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testHeapSearch() {
        System.out.println("--- testHeapSearch ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Heap: " + bfs(heap));

        IntegerKey key5 = new IntegerKey(5);
        Object found = heap.heapSearch(key5);
        System.out.println("Suche nach 5: " + found + " (erwartet: 5)");

        IntegerKey key1 = new IntegerKey(1);
        found = heap.heapSearch(key1);
        System.out.println("Suche nach 1 (Wurzel): " + found + " (erwartet: 1)");

        IntegerKey key10 = new IntegerKey(10);
        found = heap.heapSearch(key10);
        System.out.println("Suche nach 10: " + found + " (erwartet: 10)");
        System.out.println();
    }

    private static void testHeapSearchNotFound() {
        System.out.println("--- testHeapSearchNotFound ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Heap: " + bfs(heap));

        IntegerKey key99 = new IntegerKey(99);
        Object found = heap.heapSearch(key99);
        System.out.println("Suche nach 99: " + found + " (erwartet: null)");

        IntegerKey key0 = new IntegerKey(0);
        found = heap.heapSearch(key0);
        System.out.println("Suche nach 0: " + found + " (erwartet: null)");
        System.out.println();
    }

    private static void testRemoveLeaf() {
        System.out.println("--- testRemoveLeaf ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Vor remove: " + bfs(heap));
        System.out.println("isHeap vor remove: " + heap.isHeap());

        heap.remove(10);
        System.out.println("Nach remove(10): " + bfs(heap));
        System.out.println("isHeap nach remove: " + heap.isHeap());

        IntegerKey key10 = new IntegerKey(10);
        System.out.println("10 noch vorhanden? " + (heap.heapSearch(key10) != null) + " (erwartet: false)");
        System.out.println();
    }

    private static void testRemoveRoot() {
        System.out.println("--- testRemoveRoot ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Vor remove: " + bfs(heap));

        heap.remove(1);
        System.out.println("Nach remove(1) (Wurzel): " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());

        IntegerKey key1 = new IntegerKey(1);
        System.out.println("1 noch vorhanden? " + (heap.heapSearch(key1) != null) + " (erwartet: false)");
        System.out.println();
    }

    private static void testRemoveMiddle() {
        System.out.println("--- testRemoveMiddle ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Vor remove: " + bfs(heap));

        heap.remove(3);
        System.out.println("Nach remove(3): " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());

        heap.remove(5);
        System.out.println("Nach remove(5): " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testRemoveNonExisting() {
        System.out.println("--- testRemoveNonExisting ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Vor remove(99): " + bfs(heap));

        heap.remove(99);
        System.out.println("Nach remove(99): " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testRemoveAll() {
        System.out.println("--- testRemoveAll ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Start: " + bfs(heap));

        int[] toRemove = {1, 3, 5, 7, 8, 10};
        for (int v : toRemove) {
            heap.remove(v);
            System.out.println("Nach remove(" + v + "): " + bfs(heap) + " | isHeap: " + heap.isHeap());
        }

        System.out.println("Höhe am Ende: " + heap.height() + " (erwartet: 0)");
        System.out.println();
    }

    private static void testRemoveMin() {
        System.out.println("--- testRemoveMin ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Start: " + bfs(heap));

        Object min = heap.removeMin();
        System.out.println("removeMin: " + min + " (erwartet: 1)");
        System.out.println("Nach removeMin: " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());

        min = heap.removeMin();
        System.out.println("removeMin: " + min + " (erwartet: 3)");
        System.out.println("Nach removeMin: " + bfs(heap));
        System.out.println("isHeap: " + heap.isHeap());
        System.out.println();
    }

    private static void testRemoveMinAll() {
        System.out.println("--- testRemoveMinAll ---");

        TreeHeap heap = createTestHeap();
        System.out.println("Start: " + bfs(heap));
        System.out.println("Elemente in aufsteigender Reihenfolge extrahieren:");

        StringBuilder extracted = new StringBuilder();
        Object min;
        while ((min = heap.removeMin()) != null) {
            if (extracted.length() > 0) extracted.append(" ");
            extracted.append(min);
            System.out.println("  removeMin: " + min + " | Rest: " + bfs(heap) + " | isHeap: " + heap.isHeap());
        }

        System.out.println("Extrahierte Reihenfolge: " + extracted);
        System.out.println("Erwartet sortiert: 1 3 5 7 8 10");
        System.out.println();
    }

    private static void testIsHeap() {
        System.out.println("--- testIsHeap ---");

        TreeHeap heap = new TreeHeap(new IntegerComparator());
        System.out.println("Leerer Heap isHeap: " + heap.isHeap() + " (erwartet: true)");

        heap.insert(1);
        System.out.println("Ein Element isHeap: " + heap.isHeap() + " (erwartet: true)");

        heap.insert(2);
        heap.insert(3);
        System.out.println("Drei Elemente isHeap: " + heap.isHeap() + " (erwartet: true)");

        for (int i = 4; i <= 20; i++) {
            heap.insert(i);
        }
        System.out.println("20 Elemente isHeap: " + heap.isHeap() + " (erwartet: true)");
        System.out.println("Heap: " + bfs(heap));
        System.out.println();
    }

    private static TreeHeap createTestHeap() {
        TreeHeap heap = new TreeHeap(new IntegerComparator());
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(7);
        heap.insert(10);
        return heap;
    }

    private static String bfs(BTree tree) {
        StringBuilder sb = new StringBuilder();
        tree.breadthFirst(new IWorker() {
            @Override
            public void work(Object data) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(data);
            }
        });
        return sb.length() > 0 ? sb.toString() : "(leer)";
    }
}


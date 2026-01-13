package test;

import ue_6.s2410238049.HashTableAVLTree;

public class HashTableAVLTreeTest {

    public static void main(String[] args) {
        System.out.println("=== HashTableAVLTree Test ===");

        testBasicInsertGetUpdate();
        testCollisionsAndResize();
        testRemove();

        System.out.println("=== HashTableAVLTree Tests fertig ===");
    }

    private static void testBasicInsertGetUpdate() {
        System.out.println("\n--- testBasicInsertGetUpdate ---");

        HashTableAVLTree ht = new HashTableAVLTree(1); 

        ht.insert("alice", 1);
        ht.insert("bob", 2);
        ht.insert("charlie", 3);

        assertEquals(1, ht.get("alice"), "get(alice)");
        assertEquals(2, ht.get("bob"), "get(bob)");
        assertEquals(3, ht.get("charlie"), "get(charlie)");
        assertEquals(null, ht.get("does-not-exist"), "get(does-not-exist)");

        
        ht.insert("bob", 22);
        assertEquals(22, ht.get("bob"), "get(bob) nach update");
    }

    private static void testCollisionsAndResize() {
        System.out.println("\n--- testCollisionsAndResize ---");

        
        HashTableAVLTree ht = new HashTableAVLTree(0);

        
        int n = 50;
        for (int i = 0; i < n; i++) {
            ht.insert("k" + i, "v" + i);
        }

        
        for (int i = 0; i < n; i++) {
            assertEquals("v" + i, ht.get("k" + i), "get(k" + i + ")");
        }

        
        ht.insert("k10", "v10b");
        ht.insert("k20", "v20b");
        assertEquals("v10b", ht.get("k10"), "get(k10) nach update");
        assertEquals("v20b", ht.get("k20"), "get(k20) nach update");
    }

    private static void testRemove() {
        System.out.println("\n--- testRemove ---");

        HashTableAVLTree ht = new HashTableAVLTree(1);

        ht.insert("a", "A");
        ht.insert("b", "B");
        ht.insert("c", "C");

        assertEquals("B", ht.get("b"), "get(b) vor remove");
        ht.remove("b");
        assertEquals(null, ht.get("b"), "get(b) nach remove");

        ht.remove("does-not-exist");
        assertEquals("A", ht.get("a"), "get(a) nach remove(nicht vorhanden)");
        assertEquals("C", ht.get("c"), "get(c) nach remove(nicht vorhanden)");
    }

    private static void assertEquals(Object expected, Object actual, String label) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual);
        }
    }
}

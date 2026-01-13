package test;

import ue_6.s2410238049.HashTableLinear;

public class HashTableLinearTest {

    public static void main(String[] args) {
        System.out.println("=== HashTableLinear Test ===");

        testBasicInsertGetUpdate();
        testCollisionsProbeChainAndResize();
        testRemoveAndClusterRehash();

        System.out.println("=== HashTableLinear Tests fertig ===");
    }

    private static void testBasicInsertGetUpdate() {
        System.out.println("\n--- testBasicInsertGetUpdate ---");

        HashTableLinear ht = new HashTableLinear(2); 

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

    private static void testCollisionsProbeChainAndResize() {
        System.out.println("\n--- testCollisionsProbeChainAndResize ---");

        
        HashTableLinear ht = new HashTableLinear(1);

        int n = 40;
        for (int i = 0; i < n; i++) {
            ht.insert("k" + i, "v" + i);
        }

        
        for (int i = 0; i < n; i++) {
            assertEquals("v" + i, ht.get("k" + i), "get(k" + i + ")");
        }

        
        ht.insert("k0", "v0b");
        ht.insert("k17", "v17b");
        ht.insert("k39", "v39b");
        assertEquals("v0b", ht.get("k0"), "get(k0) nach update");
        assertEquals("v17b", ht.get("k17"), "get(k17) nach update");
        assertEquals("v39b", ht.get("k39"), "get(k39) nach update");
    }

    private static void testRemoveAndClusterRehash() {
        System.out.println("\n--- testRemoveAndClusterRehash ---");

        HashTableLinear ht = new HashTableLinear(2); 

        ht.insert("a", "A");
        ht.insert("b", "B");
        ht.insert("c", "C");
        ht.insert("d", "D");
        ht.insert("e", "E"); 

        assertEquals("A", ht.get("a"), "get(a) vor remove");
        assertEquals("B", ht.get("b"), "get(b) vor remove");
        assertEquals("C", ht.get("c"), "get(c) vor remove");
        assertEquals("D", ht.get("d"), "get(d) vor remove");
        assertEquals("E", ht.get("e"), "get(e) vor remove");

        ht.remove("c");
        assertEquals(null, ht.get("c"), "get(c) nach remove");

        assertEquals("A", ht.get("a"), "get(a) nach remove(c)");
        assertEquals("B", ht.get("b"), "get(b) nach remove(c)");
        assertEquals("D", ht.get("d"), "get(d) nach remove(c)");
        assertEquals("E", ht.get("e"), "get(e) nach remove(c)");
        
        ht.remove("does-not-exist");
        assertEquals("A", ht.get("a"), "get(a) nach remove(nicht vorhanden)");
        assertEquals("B", ht.get("b"), "get(b) nach remove(nicht vorhanden)");
        assertEquals("D", ht.get("d"), "get(d) nach remove(nicht vorhanden)");
        assertEquals("E", ht.get("e"), "get(e) nach remove(nicht vorhanden)");
    }

    private static void assertEquals(Object expected, Object actual, String label) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual);
        }
    }
}

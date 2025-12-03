package test;

import lecture.chapter03.BTree;
import lecture.chapter03.IWorker;
import lecture.chapter03.IKey;
import ue_3.s2410238049.MyBTree;

public class MyBTreeTest {

    public static void main(String[] args) {
        System.out.println("=== MyBTree Integer Test ===");

        testInsertIntegers();
        testRemoveLeaf();
        testRemoveInnerNode();
        testRemoveRoot();
        testRemoveNonExisting();
        testSingleNodeRemove();

        System.out.println("=== Tests fertig ===");
    }

    private static void testInsertIntegers() {
        System.out.println("\n--- testInsertIntegers ---");

        MyBTree tree = new MyBTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        System.out.println("Erwartete Reihenfolge (BFS): 1 2 3 4 5 6 7");
        System.out.println("Tatsächlich: " + bfs(tree));

        System.out.println("Erwartete Höhe: 3");
        System.out.println("Tatsächliche Höhe: " + tree.height());
    }

    private static void testRemoveLeaf() {
        System.out.println("\n--- testRemoveLeaf ---");

        MyBTree tree = fullTree();

        System.out.println("Vor Entfernen von 7: " + bfs(tree));
        tree.remove(7);
        System.out.println("Nach Entfernen von 7: " + bfs(tree));

        System.out.println("7 vorhanden? (erwartet: false) -> " + contains(tree, 7));
    }

    private static void testRemoveInnerNode() {
        System.out.println("\n--- testRemoveInnerNode ---");

        MyBTree tree = fullTree();

        System.out.println("Vor Entfernen von 2: " + bfs(tree));
        tree.remove(2);
        System.out.println("Nach Entfernen von 2: " + bfs(tree));

        System.out.println("2 vorhanden? (erwartet: false) -> " + contains(tree, 2));
    }

    private static void testRemoveRoot() {
        System.out.println("\n--- testRemoveRoot ---");

        MyBTree tree = fullTree();

        System.out.println("Vor Entfernen von 1: " + bfs(tree));
        tree.remove(1);
        System.out.println("Nach Entfernen von 1: " + bfs(tree));

        System.out.println("1 vorhanden? (erwartet: false) -> " + contains(tree, 1));
    }

    private static void testRemoveNonExisting() {
        System.out.println("\n--- testRemoveNonExisting ---");

        MyBTree tree = fullTree();

        System.out.println("Vor Entfernen von 99: " + bfs(tree));
        tree.remove(99);
        System.out.println("Nach Entfernen von 99: " + bfs(tree));
    }

    private static void testSingleNodeRemove() {
        System.out.println("\n--- testSingleNodeRemove ---");

        MyBTree tree = new MyBTree();
        tree.insert(42);

        System.out.println("Vor Entfernen von 42: " + bfs(tree));
        tree.remove(42);
        System.out.println("Nach Entfernen von 42: " + bfs(tree));

        System.out.println("Höhe (erwartet: 0): " + tree.height());
    }

    private static MyBTree fullTree() {
        MyBTree t = new MyBTree();
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);
        t.insert(6);
        t.insert(7);
        return t;
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
        return sb.toString();
    }

    private static boolean contains(BTree tree, Integer value) {
        Object found = tree.search(new IKey() {
            @Override
            public boolean matches(Object data) {
                if (!(data instanceof Integer)) return false;
                return ((Integer) data).intValue() == value;
            }
        });
        return found != null;
    }
}

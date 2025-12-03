package test;

import ue_3.s2410238049.Queue;

public class QueueTest {

    public static void main(String[] args) {
        System.out.println("=== Queue Integer Test ===");

        testEmptyQueue();
        testSingleElement();
        testMultipleElementsFIFO();
        testPeekDoesNotRemove();
        testInterleavedOperations();
        testNullElement();

        System.out.println("=== Queue Tests fertig ===");
    }

    private static void testEmptyQueue() {
        System.out.println("\n--- testEmptyQueue ---");
        Queue q = new Queue();

        System.out.println("empty() erwartet: true, ist: " + q.empty());
        System.out.println("dequeue() erwartet: null, ist: " + q.dequeue());
        System.out.println("peek() erwartet: null, ist: " + q.peek());
        System.out.println("empty() nach Operationen erwartet: true, ist: " + q.empty());
    }

    private static void testSingleElement() {
        System.out.println("\n--- testSingleElement ---");
        Queue q = new Queue();
        q.enqueue(42);

        System.out.println("empty() erwartet: false, ist: " + q.empty());
        System.out.println("peek() erwartet: 42, ist: " + q.peek());
        System.out.println("dequeue() erwartet: 42, ist: " + q.dequeue());
        System.out.println("empty() nach dequeue erwartet: true, ist: " + q.empty());
    }

    private static void testMultipleElementsFIFO() {
        System.out.println("\n--- testMultipleElementsFIFO ---");
        Queue q = new Queue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        System.out.println("Erwarte FIFO-Reihenfolge: 1, 2, 3, 4");
        System.out.println("Tatsächliche dequeue-Reihenfolge: " + dequeueAllToString(q));
        System.out.println("empty() nach allen dequeues erwartet: true, ist: " + q.empty());
    }

    private static void testPeekDoesNotRemove() {
        System.out.println("\n--- testPeekDoesNotRemove ---");
        Queue q = new Queue();

        q.enqueue(10);
        q.enqueue(20);

        System.out.println("peek() erwartet: 10, ist: " + q.peek());
        System.out.println("peek() erneut erwartet: 10, ist: " + q.peek());
        System.out.println("dequeue() erwartet: 10, ist: " + q.dequeue());
        System.out.println("dequeue() erwartet: 20, ist: " + q.dequeue());
        System.out.println("empty() erwartet: true, ist: " + q.empty());
    }

    private static void testInterleavedOperations() {
        System.out.println("\n--- testInterleavedOperations ---");
        Queue q = new Queue();

        q.enqueue(1);
        q.enqueue(2);
        System.out.println("Nach enqueue(1,2): " + dequeuePeekClone(q));

        q.enqueue(3);
        System.out.println("peek() erwartet: 2, ist: " + q.peek());

        q.enqueue(4);
        System.out.println("dequeue() erwartet: 2, ist: " + q.dequeue());

        q.enqueue(5);
        System.out.println("Restliche dequeue-Reihenfolge erwartet: 3, 4, 5");
        System.out.println("Tatsächlich: " + dequeueAllToString(q));
    }

    private static void testNullElement() {
        System.out.println("\n--- testNullElement ---");
        Queue q = new Queue();

        q.enqueue(null);
        q.enqueue(1);

        System.out.println("peek() erwartet: null, ist: " + q.peek());
        System.out.println("dequeue() erwartet: null, ist: " + q.dequeue());
        System.out.println("dequeue() erwartet: 1, ist: " + q.dequeue());
        System.out.println("empty() erwartet: true, ist: " + q.empty());
    }

    private static String dequeueAllToString(Queue q) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!q.empty()) {
            Object val = q.dequeue();
            if (!first) {
                sb.append(", ");
            }
            sb.append(val);
            first = false;
        }
        return sb.toString();
    }

    private static String dequeuePeekClone(Queue original) {
        Queue tmp = new Queue();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!original.empty()) {
            Object v = original.dequeue();
            if (!first) {
                sb.append(", ");
            }
            sb.append(v);
            first = false;
            tmp.enqueue(v);
        }
        while (!tmp.empty()) {
            original.enqueue(tmp.dequeue());
        }
        return sb.toString();
    }
}

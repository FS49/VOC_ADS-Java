package test;

import ue_3.s2410238049.Stack;

public class StackTest {

    public static void main(String[] args) {
        System.out.println("=== Stack Integer Test ===");

        testEmptyStack();
        testSingleElement();
        testMultipleElementsLIFO();
        testPeekDoesNotRemove();
        testInterleavedOperations();
        testNullElement();

        System.out.println("=== Stack Tests fertig ===");
    }

    private static void testEmptyStack() {
        System.out.println("\n--- testEmptyStack ---");
        Stack s = new Stack();

        System.out.println("empty() erwartet: true, ist: " + s.empty());
        System.out.println("pop() erwartet: null, ist: " + s.pop());
        System.out.println("peek() erwartet: null, ist: " + s.peek());
        System.out.println("empty() nach Operationen erwartet: true, ist: " + s.empty());
    }

    private static void testSingleElement() {
        System.out.println("\n--- testSingleElement ---");
        Stack s = new Stack();
        s.push(42);

        System.out.println("empty() erwartet: false, ist: " + s.empty());
        System.out.println("peek() erwartet: 42, ist: " + s.peek());
        System.out.println("pop() erwartet: 42, ist: " + s.pop());
        System.out.println("empty() nach pop erwartet: true, ist: " + s.empty());
    }

    private static void testMultipleElementsLIFO() {
        System.out.println("\n--- testMultipleElementsLIFO ---");
        Stack s = new Stack();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println("Erwarte LIFO-Reihenfolge: 4, 3, 2, 1");
        System.out.println("Tatsächliche pop-Reihenfolge: " + popAllToString(s));
        System.out.println("empty() nach allen pops erwartet: true, ist: " + s.empty());
    }

    private static void testPeekDoesNotRemove() {
        System.out.println("\n--- testPeekDoesNotRemove ---");
        Stack s = new Stack();

        s.push(10);
        s.push(20);

        System.out.println("peek() erwartet: 20, ist: " + s.peek());
        System.out.println("peek() erneut erwartet: 20, ist: " + s.peek());
        System.out.println("pop() erwartet: 20, ist: " + s.pop());
        System.out.println("pop() erwartet: 10, ist: " + s.pop());
        System.out.println("empty() erwartet: true, ist: " + s.empty());
    }

    private static void testInterleavedOperations() {
        System.out.println("\n--- testInterleavedOperations ---");
        Stack s = new Stack();

        s.push(1);
        s.push(2);
        System.out.println("Nach push(1,2): " + peekPopClone(s));

        s.push(3);
        System.out.println("peek() erwartet: 3, ist: " + s.peek());

        s.push(4);
        System.out.println("pop() erwartet: 4, ist: " + s.pop());

        s.push(5);
        System.out.println("Restliche pop-Reihenfolge erwartet: 5, 3, 2, 1");
        System.out.println("Tatsächlich: " + popAllToString(s));
    }

    private static void testNullElement() {
        System.out.println("\n--- testNullElement ---");
        Stack s = new Stack();

        s.push(null);
        s.push(1);

        System.out.println("peek() erwartet: 1, ist: " + s.peek());
        System.out.println("pop() erwartet: 1, ist: " + s.pop());
        System.out.println("pop() erwartet: null, ist: " + s.pop());
        System.out.println("empty() erwartet: true, ist: " + s.empty());
    }

    private static String popAllToString(Stack s) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!s.empty()) {
            Object val = s.pop();
            if (!first) {
                sb.append(", ");
            }
            sb.append(val);
            first = false;
        }
        return sb.toString();
    }

    private static String peekPopClone(Stack original) {
        Stack tmp = new Stack();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!original.empty()) {
            Object v = original.pop();
            if (!first) {
                sb.append(", ");
            }
            sb.append(v);
            first = false;
            tmp.push(v);
        }
        while (!tmp.empty()) {
            original.push(tmp.pop());
        }
        return sb.toString();
    }
}

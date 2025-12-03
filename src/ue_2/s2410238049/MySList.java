package ue_2.s2410238049;

import lecture.chapter03.SList;
import lecture.chapter03.IKey;

public class MySList extends SList {

    public void append(Object data) {
        if (head == null) {
            head = new Node(data, null);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(data, null);
        }
    }

    public boolean insert(Object prev, Object data) {
        Node current = head;
        while (current != null && current.data != prev) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        current.next = new Node(data, current.next);
        return true;
    }

    public SList searchAll(IKey key) {
        MySList result = new MySList();
        Node current = head;
        while (current != null) {
            if (key.matches(current.data)) {
                result.append(current.data);
            }
            current = current.next;
        }
        return result;
    }
}


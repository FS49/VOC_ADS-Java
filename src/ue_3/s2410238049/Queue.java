package ue_3.s2410238049;

import lecture.chapter03.DList;
import lecture.chapter03.IFIterator;

public class Queue {

    private final DList list = new DList();
    private int size = 0;

    public void enqueue(Object data) {
        list.append(data);
        size++;
    }

    public Object dequeue() {
        if (empty()) {
            return null;
        }
        IFIterator it = list.fIterator();
        Object data = it.next();
        list.forwardRemove(data);
        size--;
        return data;
    }

    public Object peek() {
        if (empty()) {
            return null;
        }
        IFIterator it = list.fIterator();
        return it.next();
    }

    public boolean empty() {
        return size == 0;
    }
}

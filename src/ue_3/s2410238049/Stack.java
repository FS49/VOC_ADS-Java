package ue_3.s2410238049;

import lecture.chapter03.SList;
import lecture.chapter03.IFIterator;

public class Stack {

    private final SList list = new SList();
    private int size = 0;

    public void push(Object data) {
        list.prepend(data);
        size++;
    }

    public Object pop() {
        if (empty()) {
            return null;
        }
        IFIterator it = list.iterator();
        Object data = it.next();
        list.remove(data);
        size--;
        return data;
    }

    public Object peek() {
        if (empty()) {
            return null;
        }
        IFIterator it = list.iterator();
        return it.next();
    }

    public boolean empty() {
        return size == 0;
    }
}

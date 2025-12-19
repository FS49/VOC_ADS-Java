package ue_5.s2410238049;

import ue_3.s2410238049.Queue;

public class PriorityQueue extends Queue {

    private static class Entry {
        final Object data;
        final int priority;

        Entry(Object data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    public void insert(Object data, int priority) {
        Queue tmp = new Queue();
        Entry neu = new Entry(data, priority);

        boolean inserted = false;

        while (!this.empty()) {
            Entry cur = (Entry) super.dequeue();

            if (!inserted && neu.priority < cur.priority) {
                tmp.enqueue(neu);
                inserted = true;
            }
            tmp.enqueue(cur);
        }

        if (!inserted) {
            tmp.enqueue(neu);
        }

        while (!tmp.empty()) {
            super.enqueue(tmp.dequeue());
        }
    }

    public Object extractMin() {
        if (this.empty()) {
            return null;
        }
        Entry e = (Entry) super.dequeue();
        return e.data;
    }

    public Object peekMin() {
        if (this.empty()) {
            return null;
        }
        Entry e = (Entry) super.peek();
        return e.data;
    }
}
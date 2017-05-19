package ue.exercise05;

import vl.chapter03.IComparator;

public class MinHeap extends Heap {
    public MinHeap(IComparator comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return +1;
    }

    public Object extractMin() {
        return extractRoot();
    }
}

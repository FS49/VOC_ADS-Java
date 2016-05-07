package kapitel_3.work;

import kapitel_3.work.IComparator;

public class MaxHeap extends Heap {
    public MaxHeap(IComparator comparator) {
        super(comparator);
    }

    protected int comparatorSign() {
        return -1;
    }

    public Object extractMax() {
        return extractRoot();
    }
}

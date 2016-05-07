package kapitel_3.work;

import kapitel_3.work.IComparator;

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

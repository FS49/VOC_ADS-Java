package ue_4.s2410238049;

import lecture.chapter03.IComparator;
import lecture.chapter03.IKey;

public class ArrayHeap {
    private Object[] heap;
    private int size;
    private final IComparator comparator;
    private static final int DEFAULT_CAPACITY = 16;

    public ArrayHeap(IComparator comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be null");
        }
        this.comparator = comparator;
        this.heap = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayHeap(IComparator comparator, int initialCapacity) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be null");
        }
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.comparator = comparator;
        this.heap = new Object[initialCapacity];
        this.size = 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            Object[] newHeap = new Object[heap.length * 2];
            for (int i = 0; i < size; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
    }

    private void swap(int i, int j) {
        Object tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void upHeap(int index) {
        while (index > 0) {
            int p = parent(index);
            if (comparator.compare(heap[index], heap[p]) < 0) {
                swap(index, p);
                index = p;
            } else {
                return;
            }
        }
    }

    private void downHeap(int index) {
        while (left(index) < size) {
            int smallerChild = findSmallerChildIndex(index);
            if (comparator.compare(heap[smallerChild], heap[index]) < 0) {
                swap(index, smallerChild);
                index = smallerChild;
            } else {
                return;
            }
        }
    }

    private int findSmallerChildIndex(int index) {
        int l = left(index);
        int r = right(index);

        if (r >= size) {
            return l;
        }

        return (comparator.compare(heap[l], heap[r]) <= 0) ? l : r;
    }

    public void insert(Object data) {
        ensureCapacity();
        heap[size] = data;
        size++;
        upHeap(size - 1);
    }

    public Object removeMin() {
        if (size == 0) {
            return null;
        }

        Object min = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) {
            downHeap(0);
        }

        return min;
    }

    public Object peekMin() {
        if (size == 0) {
            return null;
        }
        return heap[0];
    }

    public int searchIndex(IKey key) {
        return searchIndex(0, key);
    }

    private int searchIndex(int index, IKey key) {
        if (index >= size) {
            return -1;
        }

        if (key.matches(heap[index])) {
            return index;
        }

        if (comparator.compare(heap[index], key) > 0) {
            return -1;
        }

        int foundIndex = searchIndex(left(index), key);
        if (foundIndex == -1) {
            foundIndex = searchIndex(right(index), key);
        }

        return foundIndex;
    }

    private int searchIndexByData(int index, Object data) {
        if (index >= size) {
            return -1;
        }

        int cmp = comparator.compare(heap[index], data);
        if (cmp == 0) {
            return index;
        }

        if (cmp > 0) {
            return -1;
        }

        int foundIndex = searchIndexByData(left(index), data);
        if (foundIndex == -1) {
            foundIndex = searchIndexByData(right(index), data);
        }

        return foundIndex;
    }

    public void remove(Object data) {
        int index = searchIndexByData(0, data);

        if (index == -1) {
            return;
        }

        Object lastData = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (index < size) {
            heap[index] = lastData;

            int p = parent(index);
            if (index > 0 && comparator.compare(heap[index], heap[p]) < 0) {
                upHeap(index);
            } else {
                downHeap(index);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isHeap() {
        for (int i = 0; i < size; i++) {
            int l = left(i);
            int r = right(i);

            if (l < size && comparator.compare(heap[i], heap[l]) > 0) {
                return false;
            }
            if (r < size && comparator.compare(heap[i], heap[r]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(heap[i]);
        }
        return sb.toString();
    }
}

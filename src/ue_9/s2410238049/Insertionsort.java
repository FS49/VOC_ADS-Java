package ue_9.s2410238049;

import lecture.chapter03.DList;
import lecture.chapter03.IComparator;

public class Insertionsort extends DList {

    public void sort(IComparator comparator) {
        insertionSort(comparator);
    }

    public void insertionSort(IComparator comparator) {
        if (comparator == null || head == null || head.next == null) {
            return;
        }

        Node current = head.next;
        while (current != null) {
            Object key = current.data;
            Node scan = current.prev;
            while (scan != null && comparator.compare(scan.data, key) > 0) {
                scan.next.data = scan.data;
                scan = scan.prev;
            }
            if (scan == null) {
                head.data = key;
            } else {
                scan.next.data = key;
            }
            current = current.next;
        }
    }
}

package ue_9.s2410238049;

import lecture.chapter03.DList;
import lecture.chapter03.IComparator;

public class Bubblesort extends DList {

    public void sort(IComparator comparator) {
        bubbleSort(comparator);
    }

    public void bubbleSort(IComparator comparator) {
        if (comparator == null || head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current != null && current.next != null) {
                if (comparator.compare(current.data, current.next.data) > 0) {
                    Object tmp = current.data;
                    current.data = current.next.data;
                    current.next.data = tmp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}

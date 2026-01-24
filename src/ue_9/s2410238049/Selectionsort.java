package ue_9.s2410238049;

import lecture.chapter03.DList;
import lecture.chapter03.IComparator;

public class Selectionsort extends DList {

    public void sort(IComparator comparator) {
        selectionSort(comparator);
    }

    public void selectionSort(IComparator comparator) {
        if (comparator == null || head == null || head.next == null) {
            return;
        }

        Node start = head;
        while (start != null) {
            Node min = start;
            Node current = start.next;
            while (current != null) {
                if (comparator.compare(current.data, min.data) < 0) {
                    min = current;
                }
                current = current.next;
            }
            if (min != start) {
                Object tmp = start.data;
                start.data = min.data;
                min.data = tmp;
            }
            start = start.next;
        }
    }
}

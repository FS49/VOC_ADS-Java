package kapitel_5.tests;

import java.util.Random;

import kapitel_5.vl.Sorter;

public class TestSorter {

    public static void printArray(String sorter, int a[]) {
        System.out.print(sorter + ": ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] base = new int[100];
        
        Random rand = new Random();
        
        for (int i = 0; i < 100; i++) {
            base[i] = rand.nextInt(200);
        }
        
        int[] a = base.clone();
        Sorter.quickSort(a);
        printArray("Quicksort:     ", a);
        
        a = base.clone();
        Sorter.bubbleSort(a);
        printArray("Bubblesort:    ", a);
        
        a = base.clone();
        Sorter.insertionSort(a);
        printArray("Insertionsort: ", a);
        
        a = base.clone();
        a = Sorter.mergeSort(a);
        printArray("Mergesort:     ", a);
        
        a = base.clone();
        Sorter.selectionSort(a);
        printArray("Selectionsort: ", a);
        
        a = base.clone();
        Sorter.shellSort(a);
        printArray("Shellsort:     ", a);
    }

}

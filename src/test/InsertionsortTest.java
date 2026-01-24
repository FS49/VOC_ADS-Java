package test;

import lecture.chapter03.IComparator;
import lecture.chapter03.IKey;
import lecture.chapter03.IFIterator;
import ue_9.s2410238049.Insertionsort;

public class InsertionsortTest {

    private static class IntComparator implements IComparator {
        public int compare(Object a, Object b) {
            return ((Integer) a).compareTo((Integer) b);
        }
        public int compare(Object data, IKey key) {
            return 0;
        }
    }

    public static void main(String[] args) {
        run("leer", new int[]{}, new int[]{});
        run("ein Element", new int[]{5}, new int[]{5});
        run("sortiert", new int[]{1,2,3,4}, new int[]{1,2,3,4});
        run("reverse", new int[]{4,3,2,1}, new int[]{1,2,3,4});
        run("duplikate", new int[]{3,1,2,1,3}, new int[]{1,1,2,3,3});
    }

    private static void run(String name, int[] input, int[] expected) {
        Insertionsort list = new Insertionsort();
        for (int v : input) list.append(v);
        list.sort(new IntComparator());
        int[] actual = toArray(list);
        print(name, expected, actual);
    }

    private static int[] toArray(Insertionsort list) {
        IFIterator it = list.fIterator();
        int[] tmp = new int[32];
        int n = 0;
        while (it.hasNext()) tmp[n++] = (Integer) it.next();
        int[] out = new int[n];
        for (int i = 0; i < n; i++) out[i] = tmp[i];
        return out;
    }

    private static void print(String name, int[] exp, int[] act) {
        System.out.println("Test: " + name);
        System.out.println("  erwartet: " + arr(exp));
        System.out.println("  erhalten: " + arr(act));
        if (!eq(exp, act)) throw new RuntimeException("FEHLER");
        System.out.println("  OK\n");
    }

    private static boolean eq(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }

    private static String arr(int[] a) {
        String s = "[";
        for (int i = 0; i < a.length; i++) {
            s += a[i];
            if (i < a.length - 1) s += ", ";
        }
        return s + "]";
    }
}

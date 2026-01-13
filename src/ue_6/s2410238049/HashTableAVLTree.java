package ue_6.s2410238049;

import lecture.chapter03.AVLTree;
import lecture.chapter03.IComparator;
import lecture.chapter03.IFIterator;
import lecture.chapter03.IKey;

public class HashTableAVLTree {
    protected AVLTree[] buckets = null;
    protected int size = 0;        
    protected int maxLoad = 0;     
    protected int currentLoad = 0; 

    protected static final class Entry {
        public long hash;
        public String key;
        public Object data;

        public Entry(long hash, String key, Object data) {
            this.hash = hash;
            this.key = key;
            this.data = data;
        }
    }

    protected static final class EntryKey implements IKey {
        private final String key;

        public EntryKey(String key) {
            this.key = key;
        }

        public boolean matches(Object data) {
            return key.equals(((Entry) data).key);
        }
    }

    protected static final class EntryComparator implements IComparator {
        public int compare(Object data1, Object data2) {
            Entry e1 = (Entry) data1;
            Entry e2 = (Entry) data2;
            return e1.key.compareTo(e2.key);
        }

        public int compare(Object data, IKey key) {
            Entry e = (Entry) data;
            EntryKey k = (EntryKey) key;
            return e.key.compareTo(k.key);
        }
    }

    private static final IComparator COMPARATOR = new EntryComparator();

    private static long sdbm(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = s.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return hash;
    }

    public HashTableAVLTree() {
        this(0);
    }

    public HashTableAVLTree(int exponent) {
        size = 1 << exponent;
        maxLoad = (int) (size * 0.75);
        buckets = new AVLTree[size];
    }

    public void insert(String key, Object data) {
        long hash = sdbm(key);
        int idx = (int) (hash & (size - 1));

        if (buckets[idx] == null) {
            buckets[idx] = new AVLTree(COMPARATOR);
        }

        
        EntryKey entryKey = new EntryKey(key);
        Entry existing = (Entry) buckets[idx].search(entryKey);
        if (existing != null) {
            existing.data = data;
            return;
        }

        buckets[idx].insert(new Entry(hash, key, data));
        currentLoad++;

        if (currentLoad >= maxLoad) {
            resize();
        }
    }

    public Object get(String key) {
        if (size == 0) {
            return null;
        }

        long hash = sdbm(key);
        int idx = (int) (hash & (size - 1));

        AVLTree tree = buckets[idx];
        if (tree == null) {
            return null;
        }

        Entry found = (Entry) tree.search(new EntryKey(key));
        return (found != null) ? found.data : null;
    }

    public void remove(String key) {
        if (size == 0) {
            return;
        }

        long hash = sdbm(key);
        int idx = (int) (hash & (size - 1));

        AVLTree tree = buckets[idx];
        if (tree == null) {
            return;
        }

        Entry found = (Entry) tree.search(new EntryKey(key));
        if (found == null) {
            return;
        }

        
        tree.remove(found);
        currentLoad--;
    }

    public void resize() {
        int newSize = size << 1;
        AVLTree[] newBuckets = new AVLTree[newSize];

        for (int i = 0; i < size; i++) {
            AVLTree tree = buckets[i];
            if (tree == null) {
                continue;
            }

            IFIterator it = tree.iterator();
            while (it.hasNext()) {
                Entry e = (Entry) it.next();
                int newIdx = (int) (e.hash & (newSize - 1));

                if (newBuckets[newIdx] == null) {
                    newBuckets[newIdx] = new AVLTree(COMPARATOR);
                }
                newBuckets[newIdx].insert(e);
            }
        }

        size = newSize;
        maxLoad = (int) (size * 0.75);
        buckets = newBuckets;
        
    }
}

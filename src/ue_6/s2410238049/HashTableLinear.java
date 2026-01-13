package ue_6.s2410238049;

public class HashTableLinear {
    protected Entry[] buckets = null;
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

    private static long sdbm(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = s.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return hash;
    }

    public HashTableLinear() {
        this(0);
    }

    public HashTableLinear(int exponent) {
        size = 1 << exponent;
        maxLoad = (int) (size * 0.75);
        buckets = new Entry[size];
    }

    public void insert(String key, Object data) {
        if (currentLoad >= maxLoad) {
            resize();
        }
        putNewOrUpdate(key, data);
    }

    private void putNewOrUpdate(String key, Object data) {
        long hash = sdbm(key);
        int mask = size - 1;
        int idx = (int) (hash & mask);

        
        for (int steps = 0; steps < size; steps++) {
            Entry e = buckets[idx];
            if (e == null) {
                buckets[idx] = new Entry(hash, key, data);
                currentLoad++;
                if (currentLoad >= maxLoad) {
                    resize();
                }
                return;
            }
            if (e.key.equals(key)) {
                e.data = data; 
                return;
            }
            idx = (idx + 1) & mask;
        }

        
        resize();
        putNewOrUpdate(key, data);
    }

    public Object get(String key) {
        if (size == 0) {
            return null;
        }

        long hash = sdbm(key);
        int mask = size - 1;
        int idx = (int) (hash & mask);

        for (int steps = 0; steps < size; steps++) {
            Entry e = buckets[idx];
            if (e == null) {
                return null; 
            }
            if (e.key.equals(key)) {
                return e.data;
            }
            idx = (idx + 1) & mask;
        }
        return null;
    }

    public void remove(String key) {
        if (size == 0) {
            return;
        }

        long hash = sdbm(key);
        int mask = size - 1;
        int idx = (int) (hash & mask);

        
        for (int steps = 0; steps < size; steps++) {
            Entry e = buckets[idx];
            if (e == null) {
                return; 
            }
            if (e.key.equals(key)) {
                
                buckets[idx] = null;
                currentLoad--;
                rehashClusterFrom((idx + 1) & mask);
                return;
            }
            idx = (idx + 1) & mask;
        }
    }

    private void rehashClusterFrom(int startIdx) {
        int mask = size - 1;
        int idx = startIdx;

        while (true) {
            Entry e = buckets[idx];
            if (e == null) {
                return; 
            }

            buckets[idx] = null;
            currentLoad--;

            
            putExisting(e);

            idx = (idx + 1) & mask;
        }
    }

    private void putExisting(Entry e) {
        int mask = size - 1;
        int idx = (int) (e.hash & mask);

        for (int steps = 0; steps < size; steps++) {
            if (buckets[idx] == null) {
                buckets[idx] = e;
                currentLoad++;
                return;
            }
            idx = (idx + 1) & mask;
        }

        
        resize();
        putExisting(e);
    }

    public void resize() {
        int newSize = (size == 0) ? 1 : (size << 1);
        Entry[] newBuckets = new Entry[newSize];
        int newMask = newSize - 1;

        
        for (int i = 0; i < size; i++) {
            Entry e = buckets[i];
            if (e == null) {
                continue;
            }

            int idx = (int) (e.hash & newMask);
            for (int steps = 0; steps < newSize; steps++) {
                if (newBuckets[idx] == null) {
                    newBuckets[idx] = e;
                    break;
                }
                idx = (idx + 1) & newMask;
            }
        }

        buckets = newBuckets;
        size = newSize;
        maxLoad = (int) (size * 0.75);
        
    }
}

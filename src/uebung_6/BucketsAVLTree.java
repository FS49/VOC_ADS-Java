package uebung_6;

import kapitel_3.vl.AVLTree;
import kapitel_3.vl.IComparator;
import kapitel_3.vl.IKey;
import kapitel_3.vl.IWorker;

public class BucketsAVLTree implements IBuckets {
    protected static class Tuple {
        public long hash;
        public String key;
        public Object data;
        
        public Tuple(long hash, String key, Object data) {
            this.hash = hash;
            this.key = key;
            this.data = data;
        }
    }
    
    protected static class Bucket implements IBucket {
        protected static class TupleKey implements IKey {
            private String key;
            
            public TupleKey(String key) {
                this.key = key;
            }

            public boolean matches(Object data) {
                return key.equals(((Tuple) data).key);
            }
        }
        
        protected static class TupleComparator implements IComparator {
            public int compare(Object data1, Object data2) {
                Tuple tuple1 = (Tuple) data1;
                Tuple tuple2 = (Tuple) data2;
                
                return tuple1.key.compareTo(tuple2.key);
            }

            public int compare(Object data, IKey key) {
                Tuple tuple = (Tuple) data;
                TupleKey tupleKey = (TupleKey) key;
                
                return tuple.key.compareTo(tupleKey.key);
            }
        }
        
        private AVLTree tree;
        static private IComparator comparator = new TupleComparator();
        
        public Bucket() {
            tree = new AVLTree(comparator);
        }
        
        /* (non-Javadoc)
         * @see uebung_6.IBucket#insert(java.lang.Object)
         */
        @Override
        public void insert(long hash, String key, Object data) {
            tree.insert(new Tuple(hash, key, data));
        }
        
        /* (non-Javadoc)
         * @see uebung_6.IBucket#search(java.lang.String)
         */
        @Override
        public Object search(String key) {
            TupleKey tupleKey = new TupleKey(key);
            
            return tree.search(tupleKey);
        }
        
        /* (non-Javadoc)
         * @see uebung_6.IBucket#remove(java.lang.Object)
         */
        @Override
        public boolean remove(Object entry) {
            return tree.remove(entry);
        }
        
        /* (non-Javadoc)
         * @see uebung_6.IBucket#empty()
         */
        @Override
        public boolean empty() {
            return !tree.iterator().hasNext();
        }
    }

    Bucket[] buckets = null;
    
    public BucketsAVLTree(int size) {
        buckets = new Bucket[size];
    }
    
    /* (non-Javadoc)
     * @see uebung_6.IBuckets#resize(int)
     */
    @Override
    public void resize(final int newSize) {
        final Bucket[] newBuckets = new Bucket[newSize];
        
        IWorker resizeWorker = new IWorker() {
            public void work(Object data) {
                Tuple entry = (Tuple) data;
                int index = (int) (entry.hash & (newSize - 1));
                
                if (newBuckets[index] == null) {
                    newBuckets[index] = new Bucket();
                }
                newBuckets[(int) (entry.hash & (newSize - 1))].tree.insert(entry);
            }
        };
        
        for (int i = 0; i < newSize; i++) {
            if (buckets[i] != null) {
                AVLTree tree = buckets[i].tree;
                tree.depthFirstPreOrder(resizeWorker);
            }
        }
        
        buckets = newBuckets;
    }
    
    /* (non-Javadoc)
     * @see uebung_6.IBuckets#getBucket(int)
     */
    @Override
    public IBucket getBucket(int hash) {
        return buckets[hash];
    }
    
    /* (non-Javadoc)
     * @see uebung_6.IBuckets#initBucket(int)
     */
    @Override
    public void initBucket(int hash) {
        if (buckets[hash] == null) {
            buckets[hash] = new Bucket();
        }
    }
}

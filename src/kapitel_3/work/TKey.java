package kapitel_3.work;

import kapitel_3.work.generics.IKey;

class TKey<T> implements IKey<T> {
    T data = null;
    
    TKey(T data) {
        this.data = data;
    }
    
    public boolean matches(T data) {
        return this.data == data;
    }   
}
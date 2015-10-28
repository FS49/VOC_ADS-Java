package uebung_6;

interface IBucket {

    public abstract void insert(long hash, String key, Object data);

    public abstract Object search(String key);

    public abstract boolean remove(Object entry);

    public abstract boolean empty();

}
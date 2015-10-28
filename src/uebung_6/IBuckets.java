package uebung_6;


public interface IBuckets {

    public abstract void resize(int newSize);

    public abstract IBucket getBucket(int hash);

    public abstract void initBucket(int hash);

}
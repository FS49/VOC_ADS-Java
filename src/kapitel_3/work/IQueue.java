package kapitel_3.work;

public interface IQueue {
    public void enqueue(Object data);
    
    public Object dequeue();
    
    public Object peek();
    
    public boolean empty();
}

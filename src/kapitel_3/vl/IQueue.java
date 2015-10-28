package kapitel_3.vl;

public abstract class IQueue {
    abstract public void enqueue(Object data);
    
    abstract public Object dequeue();
    
    abstract public Object peek();
    
    abstract public boolean empty();
    
    protected IQueue loadClass(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        IQueue queue = null;
        
        ClassLoader cl = IQueue.class.getClassLoader();
        Class myClass = cl.loadClass(path);
        Object myObject = myClass.newInstance();
        if (myObject instanceof IQueue) {
            queue = (IQueue) myObject;
        }
        
        return queue;
    }
}

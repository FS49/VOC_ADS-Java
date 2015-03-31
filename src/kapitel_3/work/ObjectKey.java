package kapitel_3.work;

class ObjectKey implements IKey {
    Object data = null;
    
    ObjectKey(Object data) {
        this.data = data;
    }
    
    public boolean matches(Object data) {
        return this.data == data;
    }
}

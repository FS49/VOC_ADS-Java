package kapitel_3.work;

class ReferenceKey implements IKey {
    Object data = null;
    
    ReferenceKey(Object data) {
        this.data = data;
    }
    
    public boolean matches(Object data) {
        return this.data == data;
    }
}

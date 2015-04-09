package kapitel_3.vl;

public class ReferenceKey implements IKey {
    private Object data = null;
    
    public ReferenceKey(Object data) {
        this.data = data;
    }
    
    public boolean matches(Object data) {
        return this.data == data;
    }
}

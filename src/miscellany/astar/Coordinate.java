package miscellany.astar;

public class Coordinate {
    private int x = 0;
    private int y = 0;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public double distance(Coordinate c) {
        return (new EuclidianDistance(this, c)).distance();
    }
}

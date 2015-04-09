package miscellany.astar;

public class ManhattenDistance {
    private int distance = 0;
    private int xDistance = 0;
    private int yDistance = 0;
    
    private ManhattenDistance(int xDistance, int yDistance) {
        this.distance = xDistance + yDistance;
        this.xDistance = xDistance;
        this.yDistance = yDistance;
    }
    
    public ManhattenDistance(Coordinate c1, Coordinate c2) {
        this(c1.getX() - c2.getX(), c1.getY() - c2.getY());
    }
    
    public int distance() {
        return distance;
    }
    
    public int xDistance() {
        return xDistance;
    }
    
    public int yDistance() {
        return yDistance;
    }
}

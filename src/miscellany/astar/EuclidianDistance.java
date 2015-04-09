package miscellany.astar;

public class EuclidianDistance {
    private double distance = 0;
    
    private EuclidianDistance(int xDistance, int yDistance) {
        this.distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }
    
    public EuclidianDistance(Coordinate c1, Coordinate c2) {
        this(c1.getX() - c2.getX(), c1.getY() - c2.getY());
    }
    
    public double distance() {
        return distance;
    }
}
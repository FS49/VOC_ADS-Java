package miscellany.astar;

public class AStar {
    private class Node {
        Coordinate parent = null;
        double cost = 0;
        double heuristicCost = 0;
        
    }
    
//    private int[][] field = null;
    private Node[][] nodeField = null;
    public static final int FEMPTY = 0;
    public static final int FBARRIER = 1;
//    private PriorityQueue open = new PriorityQueue();
//    private SList closed = new SList();
    
    
    public AStar(int[][] field) {
        nodeField = new Node[field.length][field[0].length];
        
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                if (field[x][y] != 0) {
                    nodeField[x][y] = new Node();
                } else {
                    nodeField[x][y] = null;
                }
            }
        }
    }
    
    public Coordinate[] findPath(Coordinate start, Coordinate end) {
        Coordinate[] solution = new Coordinate[100];
        
        
        return solution;
    }
    
    public Object getNode(Coordinate coordinate) {
        return null;
    }
    
    
}

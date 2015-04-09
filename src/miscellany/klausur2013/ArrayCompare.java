package miscellany.klausur2013;

public class ArrayCompare {
    static boolean isEqual(int[][] a1, int[][] a2) {
        boolean found = true;
        for (int i = 0; i < a1.length && found; i++) {
            for (int j = 0; j < a1[0].length && found; j++) {
                found = false;
                for (int x = 0; x < a2.length && !found; x++) {
                    for (int y = 0; y < a2[0].length && !found; y++) {
                        found = (a1[i][j] == a2[x][y]);
                    }
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {4, 5}};
        int[][] b = {{2, 1}, {5, 4}};
        
        System.out.println(isEqual(a, b));
    }
}

package ue_7.s2410238049;

public class Vertices {

    public boolean existPath(int[][] adjazenzMatrix, int start, int end) {
        if (adjazenzMatrix == null || adjazenzMatrix.length == 0) {
            return false;
        }
        int n = adjazenzMatrix.length;
        if (start < 0 || start >= n || end < 0 || end >= n) {
            return false;
        }
        if (start == end) {
            return true;
        }
        boolean[] visited = new boolean[n];
        return existPathRec(adjazenzMatrix, start, end, visited);
    }

    private boolean existPathRec(int[][] m, int current, int end, boolean[] visited) {
        if (current == end) {
            return true;
        }
        visited[current] = true;
        int[] row = m[current];
        if (row != null) {
            for (int next = 0; next < row.length && next < m.length; next++) {
                if (row[next] != 0 && !visited[next]) {
                    if (existPathRec(m, next, end, visited)) {
                        visited[current] = false;
                        return true;
                    }
                }
            }
        }
        visited[current] = false;
        return false;
    }
}

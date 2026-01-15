package test;

import ue_7.s2410238049.Chess;

public class ChessTest {

    public static void main(String[] args) {
        System.out.println("=== Chess Test ===");
        testCheckPositionBasic();
        testPlaceQueen4x4WithPositions();
        testEnumerateSolutions4x4();
        testEnumerateSolutionsNoSolution();
        System.out.println("=== Chess Tests fertig ===");
    }

    private static void testCheckPositionBasic() {
        System.out.println("\n--- testCheckPositionBasic ---");

        Chess c = new Chess();
        boolean[][] board = new boolean[4][4];

        board[0][1] = true;

        assertEquals(false, c.checkPosition(board, 1, 2), "same column");
        assertEquals(false, c.checkPosition(board, 0, 1), "diagonal down-left");
        assertEquals(false, c.checkPosition(board, 2, 1), "diagonal down-right");
        assertEquals(true, c.checkPosition(board, 3, 1), "free position");
    }

    private static void testPlaceQueen4x4WithPositions() {
        System.out.println("\n--- testPlaceQueen4x4WithPositions ---");

        Chess c = new Chess();
        boolean[][] board = new boolean[4][4];

        boolean ok = c.placeQueen(board, 0);
        assertEquals(true, ok, "placeQueen(4x4)");
        assertEquals(4, countQueens(board), "queen count");
        assertEquals(true, isValidConfiguration(board), "valid configuration");

        int[] cols = extractColumns(board);
        System.out.println("Gefundene Loesung (1-basiert): " + tuple1Based(cols));
    }

    private static void testEnumerateSolutions4x4() {
        System.out.println("\n--- testEnumerateSolutions4x4 ---");

        Chess c = new Chess();
        int n = 4;

        int[][] solutions = enumerateSolutions(c, n);

        System.out.println("Anzahl Loesungen: " + solutions.length);
        for (int i = 0; i < solutions.length; i++) {
            System.out.println("Loesung " + (i + 1) + " (1-basiert): " + tuple1Based(solutions[i]));
        }

        assertEquals(2, solutions.length, "solutions(4x4) count");
        for (int i = 0; i < solutions.length; i++) {
            assertEquals(true, isValidColumnsSolution(solutions[i], n), "solution validity " + (i + 1));
        }
    }

    private static void testEnumerateSolutionsNoSolution() {
        System.out.println("\n--- testEnumerateSolutionsNoSolution ---");

        Chess c = new Chess();

        int[][] s2 = enumerateSolutions(c, 2);
        System.out.println("2x2 Anzahl Loesungen: " + s2.length);
        assertEquals(0, s2.length, "solutions(2x2) count");

        int[][] s3 = enumerateSolutions(c, 3);
        System.out.println("3x3 Anzahl Loesungen: " + s3.length);
        assertEquals(0, s3.length, "solutions(3x3) count");
    }

    private static int[][] enumerateSolutions(Chess c, int n) {
        boolean[][] board = new boolean[n][n];
        int[] cols = new int[n];
        for (int i = 0; i < n; i++) {
            cols[i] = -1;
        }
        int[][] tmp = new int[64][];
        int[] count = new int[]{0};
        enumerateRec(c, board, 0, cols, tmp, count);
        int[][] out = new int[count[0]][];
        for (int i = 0; i < count[0]; i++) {
            out[i] = tmp[i];
        }
        return out;
    }

    private static void enumerateRec(Chess c, boolean[][] board, int line, int[] cols, int[][] tmp, int[] count) {
        int n = board.length;
        if (line == n) {
            int[] sol = new int[n];
            for (int i = 0; i < n; i++) {
                sol[i] = cols[i];
            }
            if (count[0] >= tmp.length) {
                int[][] grown = new int[tmp.length * 2][];
                for (int i = 0; i < tmp.length; i++) {
                    grown[i] = tmp[i];
                }
                for (int i = 0; i < grown.length; i++) {
                    tmp[i] = grown[i];
                }
            }
            tmp[count[0]] = sol;
            count[0]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (c.checkPosition(board, col, line)) {
                board[line][col] = true;
                cols[line] = col;
                enumerateRec(c, board, line + 1, cols, tmp, count);
                cols[line] = -1;
                board[line][col] = false;
            }
        }
    }

    private static int[] extractColumns(boolean[][] board) {
        int n = board.length;
        int[] cols = new int[n];
        for (int r = 0; r < n; r++) {
            int found = -1;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c]) {
                    found = c;
                    break;
                }
            }
            cols[r] = found;
        }
        return cols;
    }

    private static String tuple1Based(int[] colsZeroBased) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < colsZeroBased.length; i++) {
            if (i > 0) sb.append(", ");
            int v = colsZeroBased[i];
            sb.append(v >= 0 ? (v + 1) : 0);
        }
        sb.append(")");
        return sb.toString();
    }

    private static int countQueens(boolean[][] board) {
        int cnt = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isValidColumnsSolution(int[] cols, int n) {
        for (int r1 = 0; r1 < n; r1++) {
            int c1 = cols[r1];
            if (c1 < 0 || c1 >= n) return false;
            for (int r2 = r1 + 1; r2 < n; r2++) {
                int c2 = cols[r2];
                if (c1 == c2) return false;
                int dr = r2 - r1;
                int dc = c2 - c1;
                if (dc == dr || dc == -dr) return false;
            }
        }
        return true;
    }

    private static boolean isValidConfiguration(boolean[][] board) {
        int n = board.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (!board[r][c]) {
                    continue;
                }

                for (int rr = 0; rr < n; rr++) {
                    if (rr != r && board[rr][c]) {
                        return false;
                    }
                }

                for (int rr = r - 1, cc = c - 1; rr >= 0 && cc >= 0; rr--, cc--) {
                    if (board[rr][cc]) {
                        return false;
                    }
                }
                for (int rr = r + 1, cc = c + 1; rr < n && cc < board[r].length; rr++, cc++) {
                    if (board[rr][cc]) {
                        return false;
                    }
                }
                for (int rr = r - 1, cc = c + 1; rr >= 0 && cc < board[r].length; rr--, cc++) {
                    if (board[rr][cc]) {
                        return false;
                    }
                }
                for (int rr = r + 1, cc = c - 1; rr < n && cc >= 0; rr++, cc--) {
                    if (board[rr][cc]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void assertEquals(Object expected, Object actual, String label) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        System.out.println(label + " erwartet: " + expected + ", ist: " + actual + " -> " + (ok ? "OK" : "FEHLER"));
        if (!ok) {
            throw new RuntimeException("Test fehlgeschlagen: " + label + " erwartet=" + expected + " ist=" + actual);
        }
    }
}

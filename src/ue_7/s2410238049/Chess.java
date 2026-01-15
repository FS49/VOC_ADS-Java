package ue_7.s2410238049;

public class Chess {

    public boolean checkPosition(boolean[][] array, int column, int line) {
        if (array == null || array.length == 0) {
            return false;
        }
        int n = array.length;
        if (line < 0 || line >= n) {
            return false;
        }
        if (array[line] == null) {
            return false;
        }
        int m = array[line].length;
        if (column < 0 || column >= m) {
            return false;
        }
        for (int r = 0; r < n; r++) {
            if (array[r] != null && column < array[r].length && array[r][column]) {
                return false;
            }
        }
        for (int r = line - 1, c = column - 1; r >= 0 && c >= 0; r--, c--) {
            if (array[r] != null && c < array[r].length && array[r][c]) {
                return false;
            }
        }
        for (int r = line + 1, c = column + 1; r < n && c < m; r++, c++) {
            if (array[r] != null && c < array[r].length && array[r][c]) {
                return false;
            }
        }
        for (int r = line - 1, c = column + 1; r >= 0 && c < m; r--, c++) {
            if (array[r] != null && c < array[r].length && array[r][c]) {
                return false;
            }
        }
        for (int r = line + 1, c = column - 1; r < n && c >= 0; r++, c--) {
            if (array[r] != null && c < array[r].length && array[r][c]) {
                return false;
            }
        }
        return true;
    }

    public boolean placeQueen(boolean[][] array, int line) {
        if (array == null || array.length == 0) {
            return false;
        }
        int n = array.length;
        if (n < 4) {
            return false;
        }
        if (line == n) {
            return true;
        }
        if (line < 0 || line > n) {
            return false;
        }
        if (array[line] == null) {
            return false;
        }
        int m = array[line].length;
        for (int column = 0; column < m; column++) {
            if (checkPosition(array, column, line)) {
                array[line][column] = true;
                if (placeQueen(array, line + 1)) {
                    return true;
                }
                array[line][column] = false;
            }
        }
        return false;
    }
}

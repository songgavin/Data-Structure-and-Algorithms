public class Toepliz {

    public boolean isToepliz(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return true;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int k = 0; k < n; k++) {
            int i = 0;
            int j = k;
            while (i < m && j < n) {
                if (i > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
                i++;
                j++;
            }
        }
        
        for (int k = 0; k < m; k++) {
            int i = k;
            int j = i - k;
            while (i < m && j < n) {
                if (j > 0 && matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
                i++;
                j++;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            { 6, 7, 8, 9 },
            { 4, 6, 7, 8 },
            { 1, 4, 6, 7 },
            { 0, 1, 4, 6 },
            { 2, 0, 1, 4 }
        };
        Toepliz t = new Toepliz();
        System.out.println(t.isToepliz(matrix));
    }
}

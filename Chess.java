/*
 * The fourth question from http://www.1point3acres.com/bbs/thread-218628-1-1.html
 */
public class Chess {

    static int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
  

    static int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
  
    public static int numsOfWay(int step, int row, int col) {
        int[][][] dp = new int[step + 1][8][8];
        
        dp[0][row][col] = 1;
        
        for(int k = 1; k < step + 1; k++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (dp[k - 1][i][j] != 0) {
                        for (int[] dir : dirs) {
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
                                dp[k][x][y]++;
                            }
                        }
                    }
                }
            }
        }
      
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               sum += dp[step][i][j];
            }
        }
      
        return sum;
    }
}

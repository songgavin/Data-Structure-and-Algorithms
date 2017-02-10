public class SearchGate {
    static int[][] dirs1 = {{0, 1}, {0, -1}};
    static int[][] dirs2 = {{1, 0}, {-1, 0}};
    
    public searchGate(char[][] rooms) {
        
    }
    
    public boolean dfs(char[][] rooms, int i, int j, boolean isLeftRight, Set<int[]> visited) {
        int orgJ = j;
        
        boolean isFind = false;
        if (isLeftRight) {
            while (j < rooms[0].length) {
                if (j < rooms[0].length - 1 && rooms[i][j + 1] != 'w' && rooms[i][j + 1] != 'g') {
                    j++;
                } else if (j == rooms[0].length - 1 || rooms[i][j + 1] == 'w') {
                    if (visited.contains(new int[]{i, j})) {
                        isFind |= dfs(rooms, i, j, false, visited);
                    }
                } else {
                    return true;
                }
            }
            j = orgJ - 1;
            while  (j >= 0) {
                if (j > 0 && rooms[i][j - 1] != 'w')
            }
        }
    }
}

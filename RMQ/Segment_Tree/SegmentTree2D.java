/*  use zkw segment tree to avoid recursion
*/

import.java.util.*;

class SegmentTree2D {
    int[][] tree;        // segment tree (zkw)
    int M, N;
    public SegmentTree2D(int[][] matrix) {
        if(matrix.length == 0 ||matrix[0].length == 0){    // invalid input
            return;
        }
        int row = matrix.length, col = matrix[0].length;
        M = 1;
        while(M < row + 2) M <<= 1;         // consider the non-recursive segment tree uses open interval
        N = 1;
        while(N < col + 2) N <<= 1;
        tree = new int[M*2][N*2];
        // copy matrix to right bottom submatrix in tree
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                tree[i + M + 1][j + N + 1] = matrix[i][j];
            }
        }
        // horizontal initialize
        for(int i = M + 1; i < 2*M; ++i){
            for(int j = N - 1; j >= 0; --j){                      // pay attention to initialize order, from N-1 to 0 index
                tree[i][j] = tree[i][2*j] + tree[i][2*j + 1];
            }
        }
        // vertical initialize
        for(int j = 0; j < 2*N; ++j){
            for(int i = M - 1; i >= 0; --i){
                tree[i][j] = tree[i*2][j] + tree[i*2 + 1][j];
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int diff = val - tree[row + M + 1][col + N + 1];
        // horizontal and vertical all related node should be updated
        for(int j = col + N + 1; j > 0; j >>= 1){
            for(int i = row + M + 1; i > 0; i >>= 1){
                tree[i][j] += diff;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        int c1 = 0, c2 = 0;
        for(int i = row1; i <= row2; ++i){
            c1 = col1 - 1 + N + 1;
            c2 = col2 + 1 + N + 1;
            while(c1 != c2 - 1){            // until c1 and c2 have same father node;
                // c1 has right brother
                if((c1&1) == 0) sum += tree[i + M + 1][c1 + 1];
                // c1 has left brother
                if((c2&1) == 1) sum += tree[i + M + 1][c2 - 1];
                c1 >>= 1;
                c2 >>= 1;
            }
        }
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

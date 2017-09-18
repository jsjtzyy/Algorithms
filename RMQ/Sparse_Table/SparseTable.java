/*
 *  Time complexity = O(nlogn) + O(m)   n is # of element, m is # of query
 * */
import java.util.*;

public class SparseTable{
    int[][] M;        // M[i][j]:start with index i, the range length is 2^(j). The min value index is M[i][j]
    
    public void preprocess(int[] nums){ // build the sparse table
        int len = nums.length;
        M = new int[len][len];          // M stores the index
        for(int i = 0; i < len; ++i){
            M[i][0] = i;
        }
        for(int j = 1; (1 << j) <= len; ++j){
            for(int i = 0; i + (1 << j) <= len; ++i){
                if(nums[M[i][j - 1]] < nums[M[i + (1 << (j - 1))][j - 1]]){
                    M[i][j] = M[i][j - 1];
                }else{
                    M[i][j] = M[i + (1 << (j - 1))][j - 1];
                }
            }
        }
    }

    public int query(int[] nums, int start, int end){
        int range = end - start + 1;
        int k = 0;
        while(range >= 2){       // or we can use log2(range). The time complexity is still O(1)
            range /= 2;
            ++k;
        }
        return Math.min(nums[M[start][k]], nums[M[end - (1 << k) + 1][k]]);
    }
}

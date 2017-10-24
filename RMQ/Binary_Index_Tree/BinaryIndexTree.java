import java.util.*;

public class BinaryIndexTree{
   int[] rawData;
   int[] tree;
   
   public BinaryIndexTree(int[] nums){
        rawData = new int[nums.length]; // initialize
        tree = new int[nums.length + 1];

        for(int i = 0; i < nums.length; ++i){
            update(i, nums[i]);
        }
   }

   public void update(int i, int val){
       // update all the horizontal successors
        int diff = val - data[i];
        data[i] = val; // modify the raw data
        for(int index = i + 1; index < tree.size(); index = index + (index & (-index))){
            tree[index] += diff;
        }
   }

   public int sumRange(int i, int j){ // return sum of [i, j)
        return prefixSum(j + 1) - prefixSum(i);
   } 

   public int prefixSum(int i){
       int res = 0; 
       for(int index = i; index > 0; index = index & (index - 1)){
           res += tree[index];
       }
       return res;
   }
}

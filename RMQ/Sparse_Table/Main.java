public class Main{
    public static void main(String[] args){
        int[] nums = {4, 2, 3, 1, 5, 7, 0, 6};
        SparseTable lc = new SparseTable();
        lc.preprocess(nums);
        System.out.println("The array is:");
        for(int i = 0; i < nums.length; ++i){
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
        int[][] q = {
            {0, 2},
            {0, 3},
            {1, 2},
            {0, 7},
            {2, 4},
            {2, 6},
            {1, 1}
        };
        int minVal = 0;
        for(int[] pair : q){
            minVal = lc.query(nums, pair[0], pair[1]);
            System.out.println("The range["+ pair[0] + ", "+ pair[1] + "] min value = " + minVal);
        }
    }
}

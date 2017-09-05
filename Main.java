public class Main{
    public static void main(String[] args){
	QuickSort lc = new QuickSort();
        int[] nums = {4, 2, 3, 1,5, 7,6,6};
	lc.quickSort(nums, 0, nums.length - 1);
	for(int i = 0; i < nums.length; ++i){
            System.out.println(nums[i]);
        }
    }
}

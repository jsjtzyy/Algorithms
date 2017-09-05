public class RadixSort{
    public int[] radixSort(int[] nums){
        int[] output = null;
        int[] count = null;
        int digit = 0;
        for(int i = 0; i < 32; ++i){ // int 32bit 
            count = new int[2];      // binary, only 1 or 0
            output = new int[nums.length];
            for(int num : nums){
                digit = (num >> i) & 1;
                count[digit]++;
            }
            for(int j = 1; j < 2; ++j){  // using counting sort (stable)
                count[j] += count[j - 1];
            }
            for(int j = nums.length - 1; j >= 0; --j){ // start from last
                digit = (nums[j] >> i) & 1;
                output[count[digit] - 1] = nums[j];
                count[digit]--;
            }
            nums = output;
        }
        return output;
    }
}

public class TopK{
    public void getTopK(int nums[], int k){
        int start = 0, end = nums.length - 1;
        int pos = partition(nums, start, end);
	while(pos != k - 1){
	    if(pos > k - 1){
		end = pos - 1;
            }else{
		start = pos + 1;
            }
	    pos = partition(nums, start, end);
        }
	for(int i = 0; i < k; ++i)
	    System.out.println(nums[i]);
    }

    public int partition(int[] nums, int start, int end){
        int pivot = nums[end];
	int pos = start - 1, tmp = 0;
	for(int i = start; i < end; ++i){
            if(nums[i] <= pivot){
	        ++pos;
		//swap
		tmp = nums[pos];
		nums[pos] = nums[i];
		nums[i] = tmp;
            }
        }
	// swap
	nums[end] = nums[pos + 1];
	nums[pos + 1] = pivot;
	return pos + 1;
    }
}

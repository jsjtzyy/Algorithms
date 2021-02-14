public class QuickSort{
    public void quickSort(int[] nums, int start, int end){
	if(start >= end) return;
	int pos = partition(nums, start, end);
        quickSort(nums, start, pos - 1);
        quickSort(nums, pos + 1, end);
    }

    /**
    *  maintain two pointers: 
    *      pos points to cur element that <= pivot 
    *.     i  is ahead of pos and tries to find next element <= pivot and swap to nums[pos + 1]
    */
    public int partition(int[] nums, int start, int end){
        int pivot = nums[end];
        int pos = start - 1, tmp = 0;
        for(int i = start; i < end; ++i){
            if(nums[i] <= pivot){
     		++pos;
		tmp = nums[pos];    // swap
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

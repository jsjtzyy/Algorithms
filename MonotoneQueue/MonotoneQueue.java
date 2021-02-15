// Monotone queue is a queue that elements from beginning to end are in either ascending or descending order
// Monotone queue is usually implemented by Deque (linkedlist underlying)
public class MonotoneQueue{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i < nums.length; ++i){
            if(i < k - 1){
                // fill in k - 1 element to deque (fill in index only)
                while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                    dq.pollLast();
                }
                dq.addLast(i);
            }else{
                while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                    dq.pollLast();
                }
                dq.addLast(i);
                res[i - k + 1] = nums[dq.peek()];
                int toRemoveIndex = i - k + 1;
                if(dq.peek() == toRemoveIndex){
                    dq.pollFirst();
                }
            }
        }
        return res;
    }
}

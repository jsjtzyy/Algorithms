/*
You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

problem link: https://leetcode.com/problems/jump-game-vi/
*/
class JumpGameVI {
    public int maxResult(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(nums[0]);
        int cur = 0;
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for(int i = 1; i < nums.length; ++i){
            cur = queue.peekFirst() + nums[i];
            res[i] = cur;
            while(!queue.isEmpty() && cur > queue.peekLast()){
                queue.pollLast();
            }
            queue.addLast(cur);
            if(i - k >= 0){
                if(queue.peekFirst() == res[i - k]){
                    queue.pollFirst();
                }
            }
        }
        return res[nums.length - 1];
    }
}

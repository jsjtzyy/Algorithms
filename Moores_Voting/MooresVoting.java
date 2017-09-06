/* Problem: 
 *     Given an array of size n, find the majority element. 
 *     The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * */

public class MooresVoting{
    public int findMajority(int[] nums){
        int count = 0;
        int candidate = 0;
        for(int num : nums){
            if(count == 0){
                candidate = num;
                ++count;
            }else{
                if(candidate == num) ++count;
                else --count;
            }
        }
        // if we are not sure if such majority elements exists, we need to traverse array again
        count = 0;
        for(int num : nums){
            if(num == candidate){
                ++count;
            }
        }
        if(count > nums.length / 2) return candidate;
        return -1; // not found the element more than [n / 2] times;
    }
}

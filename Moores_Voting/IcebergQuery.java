/* Problem: 
 *      Given an array with length n, find all elements appear more than ⌊ n/k ⌋ times
 * */

import java.util.*;
public class IcebergQuery {
    public List<Integer> findAllMajority(int[] nums, int k){
        List<Integer> res = new ArrayList<>();
        int[] candidates = new int[k - 1]; // at most k - 1 elements can appear more than n/k times
        int[] count = new int[k - 1];
        boolean inCandidate = false;
        // time complexity O(k*n)
        for(int num : nums){
            inCandidate = false;
            for(int i = 0; i < k - 1; ++i){ // check if num equals existing candidate
                if(candidates[i] == num){
                    count[i]++;
                    inCandidate = true;
                    break;
                }
            }
            if(inCandidate) continue;
            for(int i = 0; i < k - 1; ++i){ // check if there is empty candidate position for num
                if(count[i] == 0){
                    candidates[i] = num;
                    ++count[i];
                    inCandidate = true;
                    break;
                }
            }
            if(inCandidate) continue;
            for(int i = 0; i < k - 1; ++i){ // reduce all count by 1. Like the histogram.
                count[i]--;
            }
        }

        // Second traverse to make sure all candidates are valid
        count = new int[k - 1];
        for(int num : nums){
            for(int i = 0; i < k - 1; ++i){
                if(num == candidates[i]){
                    count[i]++;
                    break;
                }
            }
        }

        for(int i = 0; i < count.length; ++i){
            if(count[i] > nums.length / k) res.add(candidates[i]);
        }

        return res;
    }
}


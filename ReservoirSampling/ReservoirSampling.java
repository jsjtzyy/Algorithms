/* Problem:
 *      From an array or stream with N size (the N can be very large or stream can be endless), 
 *      choose k (k < N) elements randomly from array or stream.
 *      Make sure each element is selected with same probability k/N
 * */

import java.util.*;
public class ReservoirSampling{
    public int[] sample(int[] nums, int k){
        int[] res = new int[k];
        for(int i = 0; i < k; ++i){
            res[i] = nums[i];
        }
        Random rand = new Random();
        int index = 0;
        for(int i = k; i < nums.length; ++i){
            index = rand.nextInt(i + 1); // [0, i+1) prob = k/(k + 1) * (k + 1)/(k + 2) * ... (N - 1)/N = k/N
            if(index < k){
                res[index] = nums[i];    // swap the element
            }
        }
        return res;
    }
}

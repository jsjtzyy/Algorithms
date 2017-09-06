import java.util.*;
public class Main{
    public static void main(String[] args){
        int[] nums = {2, 2, 2, 2, 4, 1, 2, 4, 4, 4, 2};
        MooresVoting lc = new MooresVoting();
        IcebergQuery iq = new IcebergQuery();
        System.out.println("The majority element is : " + lc.findMajority(nums));
        List<Integer> res = iq.findAllMajority(nums, 3);
        System.out.println("Iceberg Query find all majority:");
        for(Integer num : res){
            System.out.println(num);
        }
    }
}

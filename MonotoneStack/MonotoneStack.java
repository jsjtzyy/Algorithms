public class MonotoneStack{

/*
* Given an array (i.e. [5, 2, 3, 1, 4]), for each element in the array, find minimum steps to move to find next element larger than itself.
* If such next element doesn't exist, step = -1;
* Using above array as an example, we should return [-1, 1, 2, 1, -1]
*/
  public static int[] monotonousStack(int[] nums){
    int[] res = new int[nums.length];
    Stack<Integer> stack = new Stack<>();
    // maintain a stack ensuring ascending order from top to bottom
    for(int i = 0; i < nums.length; ++i){
      while(!stack.isEmpty() && nums[i] > nums[stack.peek()]){
        int index = stack.pop();
        res[index] = i - index;
      }
      stack.push(i);
    }
    while(!stack.isEmpty()){
      int index = stack.pop();
      res[index] = -1;
    }
    // as each element is pushed and popped once, the time complexity is O(n)
    return res;
  }
}

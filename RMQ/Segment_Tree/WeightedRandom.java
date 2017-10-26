/* implement a data structure to store Object-Weight Pair 
 * it should support operations as follows:
 *  (1) Update
 *  (2) Insert
 *  (3) Remove
 *  (4) GetRandom
 *  The random probability = weight of Object / total sum of weights
 *  Try to reduce time complexity of all operations.
 * */
import java.util.*;

public class WeightedRandom{ // suppose the object-weight is String-int pair
    
    ArrayList<Integer> tree;
    int M;                   // smallest power of 2 which 2^N >= input size
    Queue<Integer> queue;    // track available pos in leaf nodes
    HashMap<String, Integer> str2intMap;
    HashMap<Integer, String> int2strMap;
    public WeightedRandom(){
        M = 2;
        tree = new ArrayList<Integer>();
        queue = new LinkedList<Integer>();
        str2intMap = new HashMap<String, Integer>();
        int2strMap = new HashMap<Integer, String>();
        for(int i = 0; i < M; ++i){
            queue.offer(i);                           // add all available position into queue
        }
        for(int i = 0; i < 2*M; ++i) tree.add(0);     // build tree with zero value
    }

    public void Insert(String str, int weight){
        if(queue.isEmpty()) {
            resize();
            System.out.println("Resize the tree. The input is " + str + ", " + weight);
        }
        int index = queue.poll();
        str2intMap.put(str, index);
        int2strMap.put(index, str);  // update two hashmaps
        index += M;
        int diff = weight;
        for(int i = index; i > 0; i >>= 1){
            tree.set(i, tree.get(i) + diff);     // update the tree
        }
        
    }

    public void Update(String str, int newWeight){
        int index = str2intMap.get(str);
        int diff = newWeight - tree.get(index + M);
        for(int i = index + M; i > 0; i >>= 1){
            tree.set(i, tree.get(i) + diff);
        }
    }

    public void Remove(String str){
        int index = str2intMap.get(str);
        queue.offer(index); // add new avaiable position
        int diff = 0 - tree.get(index + M);
        for(int i = index + M; i > 0; i >>= 2){
            tree.set(i, tree.get(i) + diff);
        }
        str2intMap.remove(str);
        int2strMap.remove(index);
    }

    public String GetRandom(int num){
        int sum = tree.get(1);
        Random rand = new Random();
        //int num = rand.nextInt(sum) + 1; // generate [1, sum] random number
        int i = 1;
        while(i < M){
            i <<= 1;
            if(num > tree.get(i)) {
                num -= tree.get(i);
                i += 1;
            }
        }
        int index = i - M;
        return int2strMap.get(index);
    }

    public void resize(){
        // extend current arrayList, double the size
        for(int i = 0; i < 2*M; ++i){
            tree.add(0);
            if(i < M){
                queue.offer(i + M);    // add new available positions
            }
        }
        int offset = M;
        for(int i = 2*M -1 ; i > 0; --i){
            tree.set(i + offset, tree.get(i));
            if(i == offset) offset >>= 1;
        }
        M = M * 2;  // update the number of elements in the tree
    }
}

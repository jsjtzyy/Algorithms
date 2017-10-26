import java.util.*;

public class Main{

    public static void main(String[] args){
        WeightedRandom wr = new WeightedRandom();
        wr.Insert("a", 4);
        wr.Insert("b", 2);
        wr.Insert("c", 1);
        wr.Insert("d", 3);
        wr.Update("c", 3);
        wr.Remove("c");
        wr.Insert("e", 1);
        wr.Insert("f", 2);
        //wr.Update("d", 1);
        for(int i = 1; i <= 12; ++i)
            System.out.println(wr.GetRandom(i));
    }
}

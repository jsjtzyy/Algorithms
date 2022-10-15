import java.util.*;

// 时间复杂度 O(ElogE) + O(V + E) alpha（v）(具体见算法导论查并集).  最终复杂度是去大的一项，也就是 O(ElogE) 

public class Kruskal{
    private class Edge implements Comparable<Edge>
    {
        int start;
        int end;
        int weight;
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge that){
            return this.weight - that.weight;
        }
    }

    private static int[][] edges = {  // a bunch of edges in format of {start, end, weight}
        {1, 2, 4}, {1, 8, 8}, {2, 8, 11}, {2, 3, 8}, {3, 9, 2}, {9, 8, 7},
        {8, 7, 1}, {9, 7, 6}, {3, 4, 7}, {3, 6, 4}, {7, 6, 2}, {4, 5, 9},
        {4, 6, 14}, {5, 6, 10}
    };
    
    public void kruskal(){
        Queue<Edge> pq = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>(); // used to count how many vertices in graph
        for(int[] tuple : edges){
            set.add(tuple[0]);
            set.add(tuple[1]);
            pq.add(new Edge(tuple[0], tuple[1], tuple[2]));
        }
        int vNum = set.size();        // number of vertices in graph
        int eNum = 0;                 // number of edge which has been selected for MST
        int[] parent = new int[vNum + 1]; // vertex id starts from 1, so if parent[i] = 0, means root;
        int[] size = new int[vNum + 1];   // union find based on size of sub-tree
        Edge edge = null;
        int v1 = 0, v2 = 0, p1 = 0, p2 = 0;
        int totalWeight = 0;
        while(!pq.isEmpty() && eNum < vNum - 1){
            edge = pq.poll();
            v1 = edge.start;
            v2 = edge.end;
            p1 = v1;
            while(parent[p1] != 0) p1 = parent[p1];   // find root ancestor of v1
            p2 = v2;
            while(parent[p2] != 0) p2 = parent[p2];   // find root ancestor of v2
            if(p1 == p2) continue;            // v1 and v2 are in same sub-tree
            System.out.println("Connect Edge : " + v1 + "---" + v2);
            totalWeight += edge.weight;
            ++eNum;
            size[p1] = (size[p1] > 0) ? size[p1] : 1;
            size[p2] = (size[p2] > 0) ? size[p2] : 1;
            if(size[p1] <= size[p2]){
                parent[p1] = p2;
                size[p2] += size[p1];
            }else{
                parent[p2] = p1;
                size[p1] += size[p2];
            }
        }
        System.out.println("Total length of MST = " + totalWeight);
    }
}

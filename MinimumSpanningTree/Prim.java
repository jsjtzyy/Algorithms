import java.util.*;

// 时间复杂度 O(V^2) 适合稠密图
public class Prim {
    class Edge implements Comparable<Edge>{
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

    private static int[][] edges = {   // a bunch of edges in format of {start, end , weight}
        {1, 2, 4}, {1, 8, 8}, {2, 8, 11}, {2, 3, 8}, {3, 9, 2}, {9, 8, 7},
        {8, 7, 1}, {9, 7, 6}, {3, 4, 7}, {3, 6, 4}, {7, 6, 2}, {4, 5, 9},
        {4, 6, 14}, {5, 6, 10}
    };
    
    HashMap<Integer, List<Edge>> graph;

    public void buildGraph(){
        int v1 = 0, v2 = 0, w = 0;
        List<Edge> list = null;
        graph = new HashMap<>();
        for(int[] tuple : edges){
            v1 = tuple[0];
            v2 = tuple[1];
            w = tuple[2];
            if(graph.containsKey(v1)){
                list = graph.get(v1);
            }else{
                list = new ArrayList<>();
            }
            list.add(new Edge(v1, v2, w));
            graph.put(v1, list);
             if(graph.containsKey(v2)){
                list = graph.get(v2);
            }else{
                list = new ArrayList<>();
            }
            list.add(new Edge(v2, v1, w));
            graph.put(v2, list);
        }
    }
    
    public void prim(){
        buildGraph();   // build graph based on edges
        HashSet<Integer> set = new HashSet<>(); // record vertices which have been connected
        int vNum = graph.size();                // number of total vertices
        int totalWeight = 0;                    // the length of MST
        Edge edge = null;
        List<Edge> list = null;
        Queue<Edge> pq = new PriorityQueue<>(); // min heap
        
        // add first vertex into set
        set.add(1);
        list = graph.get(1);
        for(Edge e : list){
            pq.add(e);
        }
        while(set.size() < vNum){
            edge = pq.poll();
            if(set.contains(edge.end)) continue;
            System.out.println("Connect edge: " + edge.start + "---" + edge.end);
            totalWeight += edge.weight;
            list = graph.get(edge.end);
            set.add(edge.end);           // end new vertex into set
            for(Edge e : list){
                if(!set.contains(e.end)){// only when the edge is connected to a new vertex
                    pq.add(e); 
                }
            }
        }
        System.out.println("The total length of MST = " + totalWeight);
    }
}

import java.util.*;
public class Dijkstra{
    private class Edge{
        String start;
        String end;
        int weight;
        public Edge(String start, String end, int weight){
            this.start = new String(start);
            this.end = new String(end);
            this.weight = weight;
        }
    }

    private class Vertex implements Comparable<Vertex>{
        String id;
        int dist;             // distance from source
        List<Edge> outDegree; // out degree edges from this vertex
        public Vertex(String str){
            this.id = new String(str);
            dist = Integer.MAX_VALUE;
            outDegree = new ArrayList<>();
        }
        @Override
        public int compareTo(Vertex that){
            return this.dist - that.dist;
        }
    }

    private static String[][] edges = {  // a bunch of edges in format of {start, end, weight}
        {"s", "t", "10"}, {"t", "x", "1"}, {"t", "y", "2"}, {"s", "y", "5"}, {"y", "t", "3"},
        {"y", "x", "9"}, {"x", "z", "4"}, {"z", "x", "6"}, {"y", "z", "2"}, {"z", "s", "7"}
    };
    
    private HashMap<String, Vertex> vMap;

    public void buildMap(){
       vMap = new HashMap<>();
       Edge edge = null;
       Vertex vertex = null;
       for(String[] tuple: edges){
           edge = new Edge(tuple[0], tuple[1], Integer.parseInt(tuple[2]));
           if(!vMap.containsKey(tuple[0])){
               vertex = new Vertex(tuple[0]);
           }else{
               vertex = vMap.get(tuple[0]);
           }
           vertex.outDegree.add(edge);
           vMap.put(tuple[0], vertex);
           if(!vMap.containsKey(tuple[1])){
               vertex = new Vertex(tuple[1]);
               vMap.put(tuple[1], vertex);
           }
       }
    }

    public void dijkstra(){
        buildMap();
        Vertex vertex = vMap.get("s");  // source vertex
        vertex.dist = 0;
        vMap.put("s", vertex);
        Queue<Vertex> pq = new PriorityQueue<>();
        for(Vertex v : vMap.values()){
            pq.add(v);
        }
        List<Edge> list = null;
        Vertex tmp = null;                  // used to update dist of intermediate vertex
        while(!pq.isEmpty()){
            vertex = pq.poll();
            System.out.println(vertex.id + " shortest distance = " + vertex.dist);
            vMap.remove(vertex.id);
            list = vertex.outDegree;
            for(Edge e : list){
                if(!vMap.containsKey(e.end)) continue;// if vertex's min distance has been determined, continue
                tmp = vMap.get(e.end);
                if(tmp.dist > vertex.dist + e.weight){
                    tmp.dist = vertex.dist + e.weight;  // update the dist of end point vertex
                    vMap.put(e.end, tmp);
                }
            }
            pq = new PriorityQueue<>();
            for(Vertex v : vMap.values()){   // rebuild the heap, hence time complexity is O(n^2)
                pq.add(v);
            }
        }
    }
}

/*
 * Tarjan is used to find Least Common Ancestor in a Tree (off-line) 
 *
 * Note that time complexity is approximately O(N + Q), where N = # of node
 * Q = # of queries. It is not exact linear.
 * 
 * Tarjan algorithm read all the query requests at once and print out the
 * result during traversing the tree.
 * */
import java.util.*;

public class Tarjan{
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        boolean visited;
        int rank;
        TreeNode parent;
        TreeNode ancestor;
        public TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
            this.visited = false;
            this.rank = 1;
            this.ancestor = null;
            this.parent = null;
            this.ancestor = null;
        }
    }

    public void Union(TreeNode u, TreeNode v){ // union by rank
        while(u.parent != u){
            u = u.parent;
        }
        while(v.parent != v){
            v = v.parent;
        }
        if(u == v) return;
        if(u.rank > v.rank){
            v.parent = u;
        }else if(u.rank < v.rank){
            u.parent = v;
        }else{
            v.parent = u;
            u.rank += 1;
        }
    }
    
    public void setAncestor(TreeNode node){ 
        TreeNode tmp = node;
        while(tmp.parent != tmp) tmp = tmp.parent;
        tmp.ancestor = node;
    }

    public int getAncestor(TreeNode node){
        while(node.parent != node) node = node.parent;
        return node.ancestor.val;
    }

    public void LCA(TreeNode node, HashMap<TreeNode, List<TreeNode>> map){
        node.parent = node; // make set u
        node.ancestor = node;
        
        if(node.left != null){
            LCA(node.left, map);
            Union(node, node.left);
            setAncestor(node);
        }

        if(node.right != null){
           LCA(node.right, map);
           Union(node, node.right);
           setAncestor(node);
        }
        node.visited = true;
        List<TreeNode> list = map.get(node);
        for(TreeNode tn : list){
            if(tn.visited){
                System.out.println("LCA("+ node.val + "," + tn.val + ") ---> " + getAncestor(tn));
            }
        }
    }
    
    public void execute(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        HashMap<TreeNode, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> list = new ArrayList<>();
        list.add(node3);
        map.put(node1, list);
        list = new ArrayList<>();
        list.add(node3);
        map.put(node2, list);
        list = new ArrayList<>();
        list.add(node1); list.add(node2);
        map.put(node3, list);
        list = new ArrayList<>();
        list.add(node4);
        map.put(node5, list);
        list = new ArrayList<>();
        list.add(node5);
        list.add(node6);
        map.put(node4, list);
        list = new ArrayList<>();
        list.add(node4);
        map.put(node6, list);
        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node5.right = node6;
        LCA(node1, map);
    }
 }

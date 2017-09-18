/*
 *   Use Leetcode 307 as example. The segment tree supports update and query range sum.
 *   Build segment tree is O(N), update and query is O(logN)
 * */

import java.util.*;

public class SegmentTree{
    class SegTreeNode{
        int start;
        int end;
        int sum; // the information maintained by segment tree
        SegTreeNode left;
        SegTreeNode right;
        public SegTreeNode(int start, int end){
            this.start = start;
            this.end = end;
            sum = 0;
            left = null;
            right = null;
        }
    }
    SegTreeNode root;
    
    public void buildTree(int[] nums){
        root = buildTree(nums, 0, nums.length - 1);    
    }

    public SegTreeNode buildTree(int[] nums, int start, int end){
        if(start > end) return null;
        SegTreeNode node = new SegTreeNode(start, end);
        if(start == end){
            node.sum = nums[start];
            return node;
        }
        int mid = (start + end) / 2;
        node.left = buildTree(nums, start, mid);
        node.right = buildTree(nums, mid + 1, end);
        node.sum = node.left.sum + node.right.sum;
        return node;
    }
   
    public void update(int index, int val){ // update segment tree, wrapper function
        update(root, index, val);
    }

    public void update(SegTreeNode node, int index, int val){
        if(node.start == node.end && node.start == index){
            node.sum = val;
            return;
        }
        int mid = (node.start + node.end) / 2;
        if(index <= mid){
            update(node.left, index, val);
        }else{
            update(node.right, index, val);
        }
        node.sum = node.left.sum + node.right.sum;  // update the information
    }
    
    public int sumRange(int start, int end){ // query, wrapper function
        return sumRange(root, start, end);
    }

    public int sumRange(SegTreeNode node, int start, int end){
        if(node.start == start && node.end == end) return node.sum;
        int mid = (node.start + node.end) / 2;
        if(end <= mid){
            return sumRange(node.left, start, end);
        }else if(start > mid){
            return sumRange(node.right, start, end);
        }
        return sumRange(node.left, start, mid) + sumRange(node.right, mid + 1, end);
    }
}

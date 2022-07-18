package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import BinaryTreesDSA450plus.ZigZagTraversalOfBinaryTree.Node;
import BinaryTreesDSA450plus.ZigZagTraversalOfBinaryTree.Pair;

public class BoundaryTraversalOfBinaryTree {
	public static class Node{
		Node left;
		Node right;
		int data;
		public int hd;
		Node(int data,Node left,Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
    public static class Pair{
    	Node node;
    	int state;
    	Pair(Node node , int state){
    		this.node = node;
    		this.state = state;
    	}
    }
    public static void display(Node node) {
    	if(node == null) return;
    	
    	String str = "";
    	str += node.left==null?".":node.left.data;
    	str += "<-" + node.data + "->";
    	str += node.right==null?".":node.right.data;
    	System.out.println(str);
    	display(node.left);
    	display(node.right);
    }
    
    static boolean isLeaf(Node root) {
    	return (root.left==null) && (root.right==null);
    	
    }
    static void addLeftBoundary(Node root,ArrayList<Integer> res) {
    	Node cur = root.left;
    	while(cur!=null) {
    		if(isLeaf(cur)==false) res.add(cur.data);
    		if(cur.left!=null) cur = cur.left;
    		else cur = cur.right;
    	}
    }
    static void addRightBoundary(Node root,ArrayList<Integer>res) {
    	Node cur = root.right;
    	ArrayList<Integer> temp = new ArrayList<Integer>();
    	while(cur!=null) {
    		if(isLeaf(cur)==false) temp.add(cur.data);
    		if(cur.right!=null) cur = cur.right;
    		else cur = cur.left;
    	}
    	int i;
    	for(i=temp.size()-1;i>=0;i--) {
    		res.add(temp.get(i));
    	}
    }
    static void addLeaves(Node root,ArrayList<Integer> res) {
    	if(isLeaf(root)) {
    		res.add(root.data);
    		return;
    	}
    	if(root.left!=null) addLeaves(root.left,res);
    	if(root.right!=null) addLeaves(root.right,res);
    	
    }
    
    static ArrayList<Integer> printBoundary(Node node){
    	ArrayList<Integer> ans =new ArrayList<Integer>();
    	if(isLeaf(node)==false) ans.add(node.data);
    	addLeftBoundary(node,ans);
    	addLeaves(node,ans);
    	addRightBoundary(node,ans);
    	return ans;
    	
    }
    
    public static void main(String[] args) {
		Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
		
		Node root = new Node(arr[0],null,null);
		
		Pair rp =new Pair(root,1);
		
		Stack<Pair> st = new Stack<>(); 
		
		st.push(rp);
		int idx=0;

		while(st.size()>0) {
			Pair top = st.peek();
			if(top.state==1) {
				idx++;
				if(arr[idx]!=null) {
				top.node.left = new Node(arr[idx],null,null);
				Pair lp = new Pair(top.node.left,1);
				st.push(lp);
			}
				else {
					top.node.left = null;
				}
				top.state++;
		}
			else if(top.state==2) {
				idx++;
				if(arr[idx]!=null) {
					top.node.right = new Node(arr[idx],null,null);
					Pair rp1= new Pair(top.node.right,1);
					st.push(rp1);
					
				}
				else {
					top.node.right = null;
				}
				top.state++;
			}
			else {
				st.pop();
			}
		}
	
		display(root);
		System.out.println(printBoundary(root));
	}
}

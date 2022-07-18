package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.tree.TreeNode;


public class VerticalOrderTraversalOfABinaryTree {
	public static class Node{
		//public static Object ;
		Node left;
		Node right;
	    int data;
	    Node(int data,Node left,Node right){
	    	this.data=data;
	    	this.left = left;
	    	this.right = right;
	    }
	}
	public static class Pair{
		Node node;
		int state;
		Pair(Node node,int state){
			this.node=node;
			this.state = state;
		}
	}
	public static void display(Node node) {
		if(node==null) return;
		
		String str = "";
		
		str += node.left==null?".":node.left.data;
		str += "<-" + node.data + "->";
		str += node.right==null?".":node.right.data;
		
		System.out.println(str);
		display(node.left);
		display(node.right);
				
	}
	
	static class Tuple{
		Node node;
		int row;
		int col;
		int data;
		public Tuple(Node _node,int _row,int _col) {
			node = _node;
			row = _row;
			col = _col;
			
		}
	}
	
	public static List<List<Integer>> verticalTraversal(Node root){
		//if(root==null) return;
		TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();
		Queue<Tuple> q = new LinkedList<Tuple>();
		q.offer(new Tuple(root,0,0));
		while(!q.isEmpty()) {
			Tuple tuple = q.poll();
			Node node = tuple.node;
			int x = tuple.row;
			int y = tuple.col;
			
			if(!map.containsKey(x)) {
				map.put(x, new TreeMap<>());
			}
			if(!map.get(x).containsKey(y)) {
				map.get(x).put(y, new PriorityQueue<Integer>());
			}
			map.get(x).get(y).offer(node.data);
			if(node.left!=null) {
				q.offer(new Tuple(node.left,x-1,y+1));
			}
			else if(node.right!=null) {
				q.offer(new Tuple(node.right,x+1,y+1));
			}
		}
		List<List<Integer>> list = new ArrayList();
		for(TreeMap<Integer,PriorityQueue<Integer>> ys : map.values()) {
			list.add(new ArrayList<>());
			for(PriorityQueue<Integer> nodes : ys.values()) {
				while(!nodes.isEmpty()) {
					list.get(list.size()-1).add(nodes.poll());
				}
			}
		}
		return list;
	}
	
public static void main(String[] args) {
	Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
    
	Node root = new Node(arr[0],null,null);
	Pair rp = new Pair(root,1);
	
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
				Pair rp1 = new Pair(top.node.right,1);
				st.push(rp1);
			}
			else {
				top.node.right=null;
			}
			top.state++;
		}
		else {
			st.pop();
		}
	}
	//printKNodesFar(root,75,3);
	System.out.println(verticalTraversal(root));
	display(root);
}
}

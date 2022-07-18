package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import BinaryTreesDSA450plus.BottomViewOfATree.Node;
import BinaryTreesDSA450plus.LeftViewOfATree.Pair;

public class ZigZagTraversalOfBinaryTree {
	
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
	    
	    public static List<List<Integer>> zigZag(Node root){
	    	
	    	Queue<Node> queue = new LinkedList<Node>();
	    	List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
	    	if(root==null) return wrapList;

	    	queue.offer(root);
	    	boolean flag = true;
	    	
	    	while(!queue.isEmpty()) {
	    		int levelNum = queue.size();
	    		List<Integer> subList = new ArrayList<Integer>(levelNum);
	    		for(int i=0;i<levelNum;i++) {
	    			//int index=i;
	    			if(queue.peek().left!=null) queue.offer(queue.peek().left);
	    			if(queue.peek().right!=null) queue.offer(queue.peek().right);
	    			if(flag==true) 
	    				subList.add(queue.poll().data);
	    			else 
	    				subList.add(0,queue.poll().data);
	    		
	    			
	    		}
	    		flag = !flag;
	    		wrapList.add(subList);
	    	}
	    	return wrapList;
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
			System.out.println(zigZag(root));
		}
	}


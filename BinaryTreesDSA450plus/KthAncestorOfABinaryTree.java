package BinaryTreesDSA450plus;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class KthAncestorOfABinaryTree {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	public static class Pair{
		Node node;
		int state;
		Pair(Node node,int state){
			this.node = node ;
			this.state = state;
			
		}
	}
	static void display(Node node) {
		if(node==null) return;
		
		String str = "";
		 
		str += node.left==null? ".":node.left.data + "";
		str += " <- " + node.data +  " -> ";
		str += node.right==null?"." : node.right.data + "";
		
		System.out.println(str);
		display(node.left);
		display(node.right);
		
	}
	
	public static int kthAncestorDFS(Node root,int value,int k) {
		if(root==null) return -1;
		
		if(root.data == value ) {
			int left  = kthAncestorDFS(root.left,value,k);
			int right = kthAncestorDFS(root.right,value,k);
			if(k>0) {
				k--;
			}
			else if(k==0) {
				System.out.println(root.data);
				return root.data;
			}
		}
//		System.out.println(root.data);
		return root.data;

	}
	public static void main(String[] args) {
		Integer arr[] = {1,2,4,null,null,5,6,null,null,7,null,null,3,8,null,null,9,null,null};
		
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
				else top.node.left =null;
				top.state++;
			}
			else if(top.state==2) {
				idx++;
				if(arr[idx]!=null) {
				top.node.right = new Node(arr[idx],null,null);
				Pair rp1 = new Pair(top.node.right,1);
				st.push(rp1);
			}
			else top.node.right=null;
			top.state++;
			}
			else {
				st.pop();
			}
		}
		System.out.println(kthAncestorDFS(root,4,2));
	//	display(root);
	}
}

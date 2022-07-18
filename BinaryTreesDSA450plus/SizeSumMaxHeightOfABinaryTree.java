                    package BinaryTreesDSA450plus;

import java.util.Stack;

public class SizeSumMaxHeightOfABinaryTree {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.left = left;
			this.right = right;
			this.data = data;
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
		if(node==null) return;
		
		String str = "";
		
		str += node.left==null?".":node.left.data + "";
		
		str += "<-" + node.data + "->";
		
		str += node.right==null?".":node.right.data+"";
		
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	
	public static int size(Node node) {
		//left size,right size
		if(node==null) return 0;
		
		int ls = size(node.left);
		int rs = size(node.right);
		int ts = ls + rs + 1;
		return ts;
	}
	
	public static int sum(Node node) {
		if(node==null) return  0 ;
		      
		int lsum = sum(node.left);
		int rsum = sum(node.right);
		int tsum = lsum+rsum+node.data;
		return tsum;    
	}
	
	public static int max(Node node) {
		 
		if(node == null) {
			return Integer.MIN_VALUE;
		}
		//left max , right max 
		int lm = max(node.left);
		int rm = max(node.right);
	    int tm = Math.max(node.data,Math.max(lm, rm))	;
	    return tm ; 
	}
	
	public static int height(Node node) {
		 
		if(node==null) {
			return -1;  //-1 for edges, 0 are nodes 
		}
		int lh = height(node.left);
		int rh = height(node.right);
		int th = Math.max(lh, rh)+1;
		return th;
	}
public static void main(String[] args) {
	Integer[] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
	
	Node root = new Node(arr[0],null,null);
	
	Pair rp = new Pair(root,1);
	
	Stack st = new Stack();
	
	st.push(rp);
	int idx=0;
	
	while(st.size()>0) {
		Pair top = (Pair) st.peek();
		
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
				top.node.right = null;
			}
			top.state++;
		}
		else st.pop();
	}
	display(root);
	System.out.println(size(root));
}
}

package BinaryTreesDSA450plus;

import java.util.Stack;

public class MirrorTree {
	static class Node{
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
		Pair(Node node ,int state){
			this.node = node;
			this.state = state;
		}
	}
	public static void display(Node node) {
		if(node==null) return;
		
		String str = "";
		str += node.left ==null? ".":node.left.data;
		str += "<-" + node.data + "->";
		str += node.right==null ? ".": node.right.data;
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	
	public static Node mirrorTree(Node root) {
		if(root==null) return null;
		
		Node left = mirrorTree(root.left);
		Node right = mirrorTree(root.right);
		
		root.right = left;
		root.left = right;
		return root;
	}
public static void main(String[] args) {
	Integer[] arr = {5,3,2,null,null,4,null,null,6,null,null};
	Node root = new Node(arr[0],null,null);
	Pair rp = new Pair(root,1);
	Stack<Pair> st = new Stack<>();
	st.push(rp);
	int idx=0;

	while(st.size()>1) {
		Pair top = st.peek();
		if(top.state==1) {
			idx++;

	    	if(arr[idx]!=null) {
			     top.node.left = new Node(arr[idx],null,null);
			      Pair lp = new Pair(top.node.left,1);
			      st.push(lp);
		}
		else top.node.left=null;
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
	display(root);
}
}

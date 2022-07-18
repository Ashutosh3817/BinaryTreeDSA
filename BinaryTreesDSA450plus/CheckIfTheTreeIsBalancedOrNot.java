package BinaryTreesDSA450plus;

import java.util.Stack;

public class CheckIfTheTreeIsBalancedOrNot {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node right,Node left){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	public static class Pair{
		int state;
		Node node;
		Pair(Node node,int state){
			this.node = node;
			this.state = state;
		}
	}
	public static void display(Node node) {
		if(node == null ) return;
		 String str = "";
		 
		 str += node.left==null?".":node.left.data + " ";
		 str += "<-" + node.data + "->";
		 str += node.right==null ? "." : node.right.data + " ";
	     System.out.println(str);
	     display(node.left);
	     display(node.right);
	}
	static boolean isBal=true;
	public static int isBalanced(Node node) {
		if(node==null) return 0;
		
		int lh = isBalanced(node.left);
		int rh = isBalanced(node.right);
		
		int gap = Math.abs(lh-rh);
		if(gap>1) {
			isBal = false;
		}
		int th = Math.max(lh, rh) + 1;
		return th;
	}
	public static void main(String[] args) {
		Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
		Node root = new Node(arr[0],null,null);
		Pair rp = new Pair(root,1);
		Stack<Pair> st = new Stack<Pair>();
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
				else top.node.left=null;
				top.state++;
			}
			else if(top.state==2) {
				idx++;
				if(arr[idx]!=null) {
					top.node.right = new Node(arr[idx],null,null);
					Pair rp1 = new Pair(top .node.right,1);
					st.push(rp1);
				}
				else top.node.right = null;
				top.state++;
			}
			else st.pop();			
		}
		display(root);
		isBalanced(root);
        System.out.println(isBal);
	}
}

package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.Stack;


public class CheckIf2TreesAreMirrorOrNot {
	public static class Node{
		Node right;
		Node left;
		int data;
		Node(int data,Node left , Node right){
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
	static void display(Node node) {
		if(node==null) return ;
		 String str = "";
		 
		 str += node.left==null?".":node.left.data+"";
		 str += "<-" + node.data + "->";
		 str += node.right==null ? "." : node.right.data;
		 
		 System.out.println(str);
		 
		 display(node.left);
		 display(node.right);
	}
	
	//static ArrayList<Integer> path;
	static boolean areMirror(Node root,Node root1) {
		if(root ==null && root1==null) {
			return true;
		}
		if(root==null || root1==null) return false;
		
		return (root.data == root1.data && areMirror(root.left,root1.right) && areMirror(root.right,root1.left));
	}

	public static void main(String[] args) {
		Integer arr[]= {1,3,null,null,2,5,null,null,4,null,null};
		Integer arr1[]= {1,2,4,null,null,5,null,null,3,null,null};
		
		Node root = new Node(arr[0],null,null);
		Node root1 = new 	Node(arr1[0],null,null);
		
		Pair rp =new Pair(root,1);
		Pair rp2 = new Pair(root1,1);
		
		Stack<Pair> st = new Stack<>(); 
		Stack<Pair> st1 = new Stack<>();
		
		st.push(rp);
		st1.push(rp2);
		
		int idx=0;
		int idx1=0;

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
		System.out.println("1st Binary Tree : ");
		display(root);
		
		while(st1.size()>0) {
			Pair top1 = st1.peek();
			if(top1.state==1) {
				idx1++;
				if(arr1[idx1]!=null) {
				top1.node.left = new Node(arr1[idx1],null,null);
				Pair lp1 = new Pair(top1.node.left,1);
				st1.push(lp1);
			}
				else {
					top1.node.left = null;
				}
				top1.state++;
		}
			else if(top1.state==2) {
				idx1++;
				if(arr1[idx1]!=null) {
					top1.node.right = new Node(arr1[idx1],null,null);
					Pair rp3= new Pair(top1.node.right,1);
					st1.push(rp3);
					
				}
				else {
					top1.node.right = null;
				}
				top1.state++;
			}
			else {
				st1.pop();
			}
		}
		System.out.println("Second Binary Tree : ");
		display(root1);
		System.out.println(areMirror(root,root1));
	}
}


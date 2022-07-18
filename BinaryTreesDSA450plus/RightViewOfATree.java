package BinaryTreesDSA450plus;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import BinaryTreesDSA450plus.LeftViewOfATree.Node;



public class RightViewOfATree {
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
	
	public static void rightViewOfATree(Node node) {
		if(node==null) return;
		Queue<Node> mq = new ArrayDeque<Node>();
		
		mq.add(node);
		
		while(mq.size()>0) {
			int count = mq.size();
			System.out.println(((ArrayDeque<Node>) mq).getLast().data);
			//System.out.println(mq.peek().data);
			for(int i=0;i<count;i++) {
				node = mq.remove();
				//System.out.print(node.data + " ");
				if(node.left!=null) mq.add(node.left);
				if(node.right!=null) mq.add(node.right);
			}
			System.out.println();
			}		
	
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
		rightViewOfATree(root);
		display(root);
	}
}

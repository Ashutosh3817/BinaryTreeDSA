package BinaryTreesDSA450plus;

import java.util.Stack;

public class PrintKLevelsDown {
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
			this.node = node;
			this.state = state;
		}
	}
	public static void display(Node node) {
		if(node==null) return;
		
		String str = "";
		
		str += node.left==null?".":node.left.data + "";
		str += "<-" + node.data + "->";
		str += node.right==null? "." : node.right.data + "";
		
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	
	static void printKLevelDown(Node node, int k ) {
		
		if(node==null && k<0) {
			return ;
		}
		if(k==0) {
			System.out.println(node.data);
		}
		printKLevelDown(node.left,k-1);
		printKLevelDown(node.right,k-1);
	}
public static void main(String[] args) {
	Integer arr[] = {50,25,12,null,null,37,30,null,null,40,null,null,75,62,60,null,null,70,null,null,87,null,null};
	
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
				Pair rp1 = new Pair(top.node.right,1);
				st.push(rp1);
			}
			else {
				top.node.right=null;
			}
			top.state++;
		}
		else st.pop();
	}
	printKLevelDown(root,2);
	display(root);
}
}

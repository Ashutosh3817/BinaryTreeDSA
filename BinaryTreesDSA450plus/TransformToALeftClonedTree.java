package BinaryTreesDSA450plus;

import java.util.Stack;

public class TransformToALeftClonedTree {
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
		
		str += node.left==null?".":node.left.data +  "" ;
		str += "<-" + node.data + "->";
		str += node.right==null?".":node.right.data +"";
		
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	public static Node CreateLeftCloneTree(Node node) {
		if(node==null) return null;
		 Node lcr = CreateLeftCloneTree(node.left);
		 Node rcr = CreateLeftCloneTree(node.right);
		 //lcr - left cloned root
		 //rcr - right cloned root
		 // nn - new node
		 Node  nn = new Node(node.data,lcr,null);
		 node.left = nn;
		 node.right=rcr;
		 
		 return node;
	}
public static void main(String[] args) {
	Integer arr[] = {1,2,4,null,null,5,null,null,3,6,null,null,7,null,null};
	
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
			else 
				top.node.left=null;	
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
	CreateLeftCloneTree(root);
	display(root);

}
}

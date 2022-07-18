package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.Stack;

public  class NodeToRootPath {
	static ArrayList<Integer> path = new ArrayList<Integer>();
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
	public static boolean find(Node node , int data) {
		if(node == null) return false;
		
		if(node.data == data) {
			path.add(node.data);
			System.out.println(node.data + " node ") ;
            return true;
}
		boolean filc = find(node.left,data);
		//find in left child
		if(filc) {
			path.add(node.data);
			System.out.println(node.data + " lc ");
			return true;
		}
		//find in right child
		boolean firc = find(node.right,data);
		
		if(firc) {
			path.add(node.data);
			System.out.println(node.data + " rc ");

			return true;
		}
		return false;

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
		path = new ArrayList();
		
		System.out.println(path.toString());
		
		System.out.println(find(root,30));
		//display(root);
	}
}

package BinaryTreesDSA450plus;

import java.util.Stack;

public class DiameterOfATree {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.data= data;
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
		if(node == null) return;
		
		String str = "";
		
		str += node.left==null?" . ":node.left.data + " ";
		str += "<-" + node.data + "->";
		str += node.right==null ?" . " : node.right.data + " ";
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	public static int height(Node node) {
		if(node==null) return -1;
		
		int lh = height(node.left);
		int rh = height(node.right);
		int th = Math.max(lh,rh) + 1;
		
		return th; 
	}
	public static int diameter1(Node node){
		if(node==null) return 0;
		//maxm distance between two nodes of lhs
		 int ld = diameter1(node.left);
		 //maxm distance between two nodes of rhs
		 int rd = diameter1(node.right);
		 //maxm distance between left's deepest and right's deepest
		 int f = height(node.left) + height(node.right)+2;
		 
		 int dia = Math.max(f,Math.max(ld, rd));
		 return dia;
		 
	}
	
	public static class DiaPair{
		int ht;
		int dia;
	}
	
	public static DiaPair diameter2(Node node) {
		if(node==null) {
			DiaPair bp = new DiaPair();
			bp.ht = -1;
			bp.dia = 0 ;
			return bp;
			//base pair
		}
		DiaPair lp = diameter2(node.left);
		DiaPair rp = diameter2(node.right);
		//my pair
		DiaPair mp = new DiaPair();
		
		mp.ht = Math.max(lp.ht, rp.ht) + 1;
		//fes => factor on either side
		int fes = lp.ht + rp.ht +2;
		mp.dia = Math.max(fes,Math.max(lp.dia, rp.dia));
		
		return mp;
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
				Pair rp1 = new Pair(top.node.right,1);
				st.push(rp1);
			}
			else top.node.right=null;
			top.state++;
		}
		else
			st.pop();
	}
	//System.out.println(diameter1(root));
	DiaPair p = diameter2(root);
	System.out.println(p.dia);
	display(root);
}
}

package BinaryTreesDSA450plus;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConstructTreeFromInorderAndPreorder {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.left = left;
			this.right = right;
			this.data = data;
			
		}
		Node(int data){
			this.data = data;
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
	public static void display(Node root) {
		if(root==null) return ;
		
		String s = "";
		
		s+= root.left==null ? "."  : root.left.data;
		s+= "<-" + root.data + "->";
		s+= root.right==null?".":root.right.data;
		
		System.out.println(s);
		display(root.left);
		display(root.right);
	}
	
	public Node buildTree(int[] preorder , int[] inorder) {
		Map<Integer,Integer> inMap = new HashMap<Integer,Integer>();
		for(int i=0;i<inorder.length;i++) {
			inMap.put(inorder[i],i);
		}
		Node root = buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1,inMap);
		return root;
	}
	
	public Node buildTree(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd,Map<Integer,Integer> inMap) {
		if(preStart<preEnd && inStart<inEnd) return null;
		
		Node root = new Node(preorder[preStart]);
		
		int inRoot = inMap.get(root.data);
		int numsLeft = inRoot - inStart;
		
		root.left = buildTree(preorder,preStart+1,preStart+numsLeft,inorder,inStart,inRoot-1,inMap);
		root.right = buildTree(preorder,preStart+numsLeft+1,preEnd,inorder,inRoot+1,inEnd,inMap);
	    return root;
	}
	
	public static void main(String[] args) {
		int[] preorder = {10,20,40,50,30,60};
		int[] inorder = {40,20,50,10,60,30};
		ConstructTreeFromInorderAndPreorder s = new ConstructTreeFromInorderAndPreorder();
		System.out.println(s.buildTree(preorder, inorder));
	}
	
//	public static void main(String[] args) {
//		Integer arr[]= {1,3,null,null,2,5,null,null,4,null,null};
//		
//		Node root = new Node(arr[0],null,null);
//		
//		Pair rp = new Pair(root,1);
//		
//		Stack<Pair> st = new Stack<>();
//		st.push(rp);
//		int idx = 0 ; 
//
//		while(st.size()>0) {
//			Pair top = st.peek();
//			if(top.state==1) {
//				idx++;
//				if(arr[idx]!=null) {
//					top.node.left = new Node(arr[idx],null,null);
//					Pair lp = new Pair(top.node.left,1);
//					st.push(lp);
//				}
//				else top.node.left = null;
//				top.state++;
//			}
//			else if(top.state==2) {
//				idx++;
//				if(arr[idx]!=null) {
//					top.node.right = new Node(arr[idx],null,null);
//					Pair rp1 = new Pair(top.node.right,1);
//					st.push(rp1);
//				}
//				else top.node.right = null;
//				top.state++;
//			}
//			else {
//				st.pop();
//			}
//		}
//		display(root);
//	}
}
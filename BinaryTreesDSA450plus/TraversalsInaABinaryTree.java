package BinaryTreesDSA450plus;

import java.util.Stack;

public class TraversalsInaABinaryTree {
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
    public static class Pair {
    	Node node;
    	int state;
    	
    	Pair(Node node  , int state){
    		this.node= node;
    		this.state = state;
    	}
    }
    
    public static void display(Node node) {
    	if(node == null) return;
    	
    	String str = "";
    	
    	str += node.left==null? ".":node.left.data + "";
    	str += "<-" + node.data + "->";
    	str += node.right == null ? "." : node.right.data + "";
    	
    	System.out.println(str);
    	display(node.left);
    	display(node.right);
    }
    
    public static void traversalPreorder(Node node) {
    	
    	if(node==null) return ;
    	System.out.println(node.data + " PreOrder"); //euler left -> pre
    	traversalPreorder(node.left);
    	traversalPreorder(node.right);
    	
    }
    
    public static void postOrder(Node node) {
    	if(node==null) return ; 
   
    	postOrder(node.left);
    	postOrder(node.right);
    	System.out.println(node.data + " PostOrder");// euler right -  post
    }
    
    public static void Inorder(Node node) {
    	if(node==null) return ;
  
    	Inorder(node.left);   
    	System.out.println(node.data + " Inorder ");// euler between  -  node
    	Inorder(node.right);
    } 
    public static void traversal(Node node) {
    	if(node==null) return;
    	
    	System.out.println(node.data + " in preorder");
    	traversal(node.left);
    	System.out.println(node.data + " in Inorder");
    	traversal(node.right);
    	System.out.println(node.data + " in postorder");
    }
   public static void main(String[]  args) {
	   Integer[] arr = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
	   
	   Node root = new Node(arr[0],null,null);
	   
	   Pair rp = new Pair(root,1);
	   
	   Stack<Pair> st = new Stack<Pair>();
	   
	   //rp->root pair
	  
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
		   else if(top.state ==2) {
			   idx++;
			   if(arr[idx]!=null) {
				   top.node.right = new Node(arr[idx],null,null);
				   //right pair1
				   Pair rp1 = new Pair(top.node.right,1);
				   st.push(rp1);
			   }
			   else top.node.right = null;
			   top.state++;
		   }
		   else st.pop();
	   }
	   display(root);
	   traversalPreorder(root);
	   postOrder(root);
	   Inorder(root);
   }
}

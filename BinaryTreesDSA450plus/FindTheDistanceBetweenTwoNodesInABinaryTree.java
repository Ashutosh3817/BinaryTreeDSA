package BinaryTreesDSA450plus;

import java.util.Stack;

public class FindTheDistanceBetweenTwoNodesInABinaryTree {
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
		int state ;
		Node node;
		Pair(Node node,int state){
			this.node = node;
		    this.state = state;
		}
	}
	static void display(Node node) {
		if(node ==null) return;
		
		String str = "";
		str += node.left==null?".":node.left.data;
		str += "<-" + node.data + "->";
		str += node.right ==null ? ".":node.right.data;
		
		System.out.println(str);
		display(node.left);
		display(node.right);
	}
	
	public static Node findLCA(Node root,int p,int q) {
		if(root==null) return null;
		
		if(root.data ==p || root.data ==q) return root;
		Node left = findLCA(root.left,p,q);
		Node right = findLCA(root.right,p,q);
		
		if(left==null) return right;
		if(right==null) return left;
		else return root;
	}
	
	public static int findLevel(Node root,int a,int level) {
		if(root==null) return -1;
		if(root.data == a) 
			return level;
		
		int left = findLevel(root.left,a,level+1);
		if(left==-1)
			return findLevel(root.right,a,level+1);
		
		return left;
	}
	
	public static int findDistance(Node root,int a,int b) {
		Node lca = findLCA(root,a,b);
		int d1 = findLevel(lca,a,0);
		int d2 = findLevel(lca,b,0);
		
		return d1+d2;
	}
	
public static void main(String[] args) {
	Integer arr[] = {1,2,4,null,null,5,6,null,null,7,null,null,3,8,null,null,9,null,null};
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
		else {
			st.pop();
		}
	}
	//System.out.println(findLCA(root,4,7));
	System.out.println(findDistance(root,4,7));
	display(root);

}
}

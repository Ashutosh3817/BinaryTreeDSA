package BinaryTreesDSA450plus;

import java.util.Map;
import java.util.Stack;


public class MaximumSumOfNodesInBinaryTreeSuchThatNoTwoNodesAreAdjacent {
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
static Map<Node,Integer> m ;
	
	public static int getMaxSum(Node root) {
		if(root==null) return 0 ;
		//case1
		int withNode  = root.data;
		//call our grand children
		if(root.left!=null) {
		withNode += getMaxSum(root.left.left);
		withNode += getMaxSum(root.left.right);
		}
		if(root.right!=null) {
		withNode += getMaxSum(root.right.left);
		withNode += getMaxSum(root.right.right);
		}
		//case 2
		//without Node
		//call your children
		
		int withOutNode = getMaxSum(root.left) + getMaxSum(root.right);
		m.put(root, Math.max(withNode, withOutNode));
		if(m.get(root)!=null) return m.get(root);
		
		
	}
	//static Map<Node,Integer> m ;
	
	//public static int getMaxSum(Node root) {
		///if(root==null) return 0 ;
		//case1
		//int withNode  = root.data;
		//call our grand children
		//if(root.left!=null) {
		//withNode += getMaxSum(root.left.left);
		//withNode += getMaxSum(root.left.right);
		//}
		//if(root.right!=null) {
		//withNode += getMaxSum(root.right.left);
		//withNode += getMaxSum(root.right.right);
		//}
		//case 2
		//without Node
		//call your children
		//int withOutNode = getMaxSum(root.left) + getMaxSum(root.right);
		//m.put(root, Math.max(withNode, withOutNode));
		//if(m.get(root)!=null) return m.get(root);
		
		
	///}
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
	getMaxSum(root);
	display(root);
}
}


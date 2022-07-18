package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.Stack;

import BinaryTreesDSA450plus.BoundaryTraversalOfBinaryTree.Node;
import BinaryTreesDSA450plus.BoundaryTraversalOfBinaryTree.Pair;

public class SumOfNodedOnTheLongestPathFromRootToLeafNode {
	public static class Node{
		Node left;
		Node right;
		int data;
		public int hd;
		Node(int data,Node left,Node right){
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
    public static void display(Node node) {
    	if(node == null) return;
    	
    	String str = "";
    	str += node.left==null?".":node.left.data;
    	str += "<-" + node.data + "->";
    	str += node.right==null?".":node.right.data;
    	System.out.println(str);
    	display(node.left);
    	display(node.right);
    }
    static int maxLen = 0;
    static int maxSum = Integer.MIN_VALUE;
    static void sumOfLongRootToLeafPath(Node root,int sum,int len) {
    	
    	if(root==null) {
    		if(maxLen<len) {
    			maxLen  = len;
    			maxSum = sum;
    		}
    		else if(maxLen==len && sum>maxSum) {
    			maxSum = sum;
    		}
    		return ;
    	}
    	sumOfLongRootToLeafPath(root.left,sum+root.data,len+1);
    	sumOfLongRootToLeafPath(root.right,sum+root.data,len+1);
    	

    }
    
    static int sumOfLongRootToLeafPathUtil(Node root) {
    	if(root==null) 
    		return 0;
    	
    	//int maxSum = Integer.MIN_VALUE;
    	//int maxLen = 0 ;
    	sumOfLongRootToLeafPath(root,0,0);
    	return maxSum;

    }
    
    
    public static void main(String[] args) {
		//Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
		Integer[] arr = {4,2,null,1,null,null,5,null,null};
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
	
		display(root);
		System.out.println(sumOfLongRootToLeafPathUtil(root));
	}
}


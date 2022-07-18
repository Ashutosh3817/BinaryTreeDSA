package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.Stack;

import BinaryTreesDSA450plus.NodeToRootPath.Node;
import BinaryTreesDSA450plus.NodeToRootPath.Pair;

public class PathToLeafFromRoot {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.data=data;
			this.left = left;
			this.right = right;
		}
	}
	public static class Pair{
		int state;
		Node node;
		Pair(Node node , int state){
			this.node = node;
			this.state = state;
		}
	}
	public static void display(Node node) {
		if(node==null) {
			return ;
		}
		String str = "";
		str += node.left==null ? "." :node.left.data + "";
		str += "<-" + node.data + "->";
		str += node.right ==null ? "." : node.right.data + "";
		System.out.println(str);
		display(node.left);
		display(node.right);
		
	}
	
	public static void pathToLeafFromRoot(Node node,String path,int sum,int low,int high) {
		if(node==null) return;
		 if(node.left==null && node.right==null) {
			 sum += node.data;
			 if(sum>=low && sum<=high) {
				 System.out.println(path + node.data);
			 }
			 return;
		 }
		 pathToLeafFromRoot(node.left,path+node.data +" " , sum + node.data ,low,high);
		 pathToLeafFromRoot(node.right,path+node.data + " ",sum+node.data,low,high);
	}
public static void main(String[] args) {
	//Integer arr[] = {50,25,12,null,null,37,30,null,null,40,null,75,62,60,null,null,70,null,null,87,null,null};
	Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
	Node root = new Node(arr[0],null,null);
	
	Pair rp =new Pair(root,1);
	
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
	pathToLeafFromRoot(root,"",0,10,100);
	//display(root);
}
}

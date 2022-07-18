package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class WidthOfABinaryTree {
	public static class Node{
		Node left;
		Node right;
		int data;
		Node(int data,Node left,Node right){
			this.data = data;
			this.left = left;
			this.right =right;
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
		if(node==null) return ;
		
		String str = "";
		
		str += node.left==null?".":node.left.data + " ";
		str += "<-" + node.data + "->";
		str += node.right==null?".":node.right.data +" ";
		System.out.println(str);
		display(node.left);
		display(node.right);
		
	}
	public static class Pairr{
		Node  node;
		int num;
		Pairr(Node node,int num){
			this.node = node;
			this.num = num;
		}
	}
			
	
	
	public static int widthOfABinaryTree(Node root) {
		if(root==null) return 0;
		int ans = 0 ;
		Queue<Pairr> q = new LinkedList<>();
		q.offer(new Pairr(root,0));
		while(!q.isEmpty()) {
			int size=q.size();
			int mmin = q.peek().num;
			int first = 0 ,last = 0 ;
			for(int i=0;i<size;i++) {
				int cur_id = q.peek().num-mmin;
				Node node = q.peek().node;
				q.poll();
				if(i==0) first = cur_id;
				if(i==size-1) last = cur_id;
				if(node.left!=null) q.offer(new Pairr(node.left,cur_id*2+1));
				if(node.right!=null) q.offer(new Pairr(node.right,cur_id*2+2));
			}
				ans = Math.max(ans,last-first+1);
			}
		return ans;
		}
	
	public static void main(String[] args) {
		Integer arr[] = {50,25,12,null,null,37,null,null,75,62,null,70,null,null,87,null,null};
		Node root= new Node(arr[0],null,null);
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
			else st.pop();
			
		}
		System.out.println(widthOfABinaryTree(root));
		display(root);
	}
}

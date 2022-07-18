package BinaryTreesDSA450plus;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class TopViewOfATree {
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
	public static ArrayList<Integer> topView(Node node) {
		ArrayList<Integer> ans = new ArrayList<>();

		if(node==null) return ans ;
		
		Map<Integer,Integer> map = new TreeMap<>();
		Queue<Pair> mq = new LinkedList<Pair>();
		mq.add(new Pair(node,0));
		while(mq.size()>0) {
			Pair it = mq.remove();
			int hd = it.state;
		//	int count = mq.size();
			Node temp = it.node;

			    if(map.get(hd)==null) map.put(hd, temp.data);
				if(temp.left!=null) mq.add(new Pair(temp.left,hd-1));
				if(temp.right!=null) mq.add(new Pair(temp.right,hd+1));
			}
			for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
				ans.add(entry.getValue());
		}
			return ans;
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
		System.out.println(topView(root));
		display(root);
	}
}

package BinaryTreesDSA450plus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import BinaryTreesDSA450plus.WidthOfABinaryTree.Node;

public class BottomViewOfATree {
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
   
    	public static ArrayList<Integer> bottomView(Node root){
    		int hd=0;
    		ArrayList<Integer> ans = new ArrayList<Integer>();
    		if(root==null) return ans;
    		Map<Integer,Integer> map = new TreeMap<>();
    		Queue<Node> q = new LinkedList<Node>();
    		root.hd = 0 ;
    		System.out.println(root.hd);
    		q.add(root);
    		while(!q.isEmpty()) {
    			Node temp = q.remove();
    			hd = temp.hd;
    			map.put(hd, temp.data);
    			if(temp.left!=null) {
    				temp.left.hd = hd-1;
    				q.add(temp.left);
    			}
    			if(temp.right!=null) {
    				temp.right.hd = hd+1;
    				q.add(temp.right);
    			}
    		}
    		for(Map.Entry<Integer,Integer>entry :map.entrySet()) {
    			ans.add(entry.getValue());
    		}
    		return ans;
    }
    public static void main(String[] args) {
		Integer arr[] = {50,25,12,null,null,37,null,null,75,62,null,70,null,null,87,null,null};
		
		Node root = new Node(arr[0],null,null);
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
				else top.node.left = null;
				top.state++;
			}
			else if(top.state==2) {
				idx++;
				if(arr[idx]!=null) {
					top.node.right = new Node(arr[idx],null,null);
					Pair rp1 = new Pair(top.node.right,1);
					st.push(rp1);
				}
				else top.node.right = null;
				top.state++;
			}
			else
				st.pop();

		}
		System.out.println(bottomView(root));
		display(root);  
		}

}

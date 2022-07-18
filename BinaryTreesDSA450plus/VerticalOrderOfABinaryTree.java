package BinaryTreesDSA450plus;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
public class VerticalOrderOfABinaryTree {
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
	str += node.left==null?".":node.left.data + " ";
	str += "<-" + node.data + "->";
	str += node.right==null?".":node.right.data +  " ";
	System.out.println(str);
	display(node.left);
	display(node.right);
}
//hd is the horizontal distance of current node from the root
static void getVerticalOrder(Node root,int hd,TreeMap<Integer,Vector<Integer>>m) {
	if(root==null) return;
	//get the vector list at hd
	Vector<Integer> get = m.get(hd);
	//store current node in map m 
	if(get==null) {
		get = new Vector<>();
		get.add(root.data);
	}
	else
		get.add(root.data);
	
	m.put(hd, get);
	
	getVerticalOrder(root.left,hd-1,m);
	getVerticalOrder(root.right,hd+1,m);
}
static void printVerticalOrder(Node root) {
	TreeMap<Integer,Vector<Integer>>m = new TreeMap<>();
	int hd=0;
	getVerticalOrder(root,hd,m);
	
	for(Entry<Integer, Vector<Integer>> entry:m.entrySet()) {
		System.out.println(entry.getValue());
	}
}
public static void main(String[] args) {
	Integer arr[] = {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
	
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
			else {
				top.node.left=null;
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
			top.node.right = null;
		}
		top.state++;
	}
		else st.pop();

}
	printVerticalOrder(root);
	display(root);
}
}
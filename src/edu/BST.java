package edu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	public Key key;
	public Integer value;
	public int n;
	public Node left;
	public Node right;

	public Node(Key key, Integer value) {
		this.key = key;
		this.value = value;
		n = 1;
		left = null;
		right = null;
	}

	@Override
	public String toString() {
		return "key: " + key + " value: " + value;
	}
}

public class BST {
	static Node root;

	public BST() {
		root = new Node(new Key(0), 1);
		root.left = null;
		root.right = null;
	}

	public BST(Key key, Integer value) {
		root = new Node(key, value);
		root.left = null;
		root.right = null;
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		return node.n;
	}

	// 使用put的时候不需要返回值, 使用这样调用可以隐藏函数调用, 也可以让调用简洁
	public void put(Key key, Integer value) {
		put(root, key, value);
	}

	private Node put(Node node, Key key, Integer value) {
		if (node == null)
			return new Node(key, value);

		if (node.key.compareTo(key) > 0) {
			if (node.left == null)
				node.left = new Node(key, value);
			else
				put(node.left, key, value);
		} else if (node.key.compareTo(key) < 0) {
			if (node.right == null)
				node.right = new Node(key, value);
			else
				put(node.right, key, value);
		} else
			node.value = value;
		node.n = size(node.left) + size(node.right) + 1;
		return null;
	}

	// 下面是书上的内容
//	public Node push1(Node node, Key key, Integer value) {
//		if (node == null) 
//			return new Node(key, value);
//		int cmp = node.key.compareTo(key);
//		if (cmp > 0) 
//			node.left = push1(node.left, key, value);
//		else if (cmp < 0)
//			node.right = push1(node.right, key, value);
//		else
//			node.value = value;
//		return node;
//	}

	public Integer get(Key key) {
		Integer result = get(root, key).value;
		if (result == null) {
			System.out.println("no result");
			return null;
		} else
			return get(root, key).value;
	}

	private Node get(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = node.key.compareTo(key);
		if (cmp > 0)
			return get(node.left, key);
		else if (cmp < 0)
			return get(node.right, key);
		else
			return node;
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node node) {
		return node.right == null ? node : max(node.right);
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node node) {
		return node.left == null ? node : min(node.left);
	}

	//向上下取整, 书上原话
	//如果给定的键key小于二叉查找树的根结点的键, 那么小于等于key的最大键floor(key)一定在
	//根节点的左子树中; 如果给定的键key大于二叉查找树的根结点, 那么只有当根结点右子树中存在
	//小于等于key的结点时, 小于等于key的最大键才会出现在右子树中, 否则根结点就是小于等于key
	//的最大键
	public Key floor(Key key) {
		Node node = floor(root, key);
		if(node == null)
			return null;
		return node.key;
	}
	
	private Node floor(Node node, Key key) {
		if(node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if(cmp == 0)
			return node;
		if(cmp < 0)
			return floor(node.left, key);
		Node t = floor(node.right, key);
		return t != null ? t : node;
	}
	
	public Key ceiling(Key key) {
		Node node = ceiling(root, key);
		if(node == null)
			return null;
		return node.key;
	}
	
	private Node ceiling(Node node, Key key) {
		if(node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if(cmp == 0)
			return node;
		if(cmp > 0)
			return ceiling(node.right, key);
		Node t = ceiling(node.left, key);
		return t != null ? t : node;
	}

	public int rank(int r) {
		return rank(root, r);
	}

	private int rank(Node node, int r) {
		if (node == null)
			return 0;
		int cmp = node.key.compareTo(new Key(r));
		if (cmp > 0)
			return rank(node.left, r);
		else if (cmp < 0)
			return 1 + size(node.left) + rank(node.right, r);
		else
			return size(node.left);
	}

	public Key select(int r) {
		return select(root, r).key;
	}

	public Node select(Node node, int r) {
		if (node == null)
			return null;
		int t = size(node.left);
		if (t > r)
			return select(node.left, r);
		else if (t < r)
			return select(node.right, r - t - 1);
		else
			return node;
	}

	// node.left = deleteMin(node.left)删除后要更新原父结点的左子树
	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node.left == null)
			return node.right;
		else {
			node.left = deleteMin(node.left);
			node.n = size(node.left) + size(node.right) + 1;
			return node;
		}
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if (node.right == null)
			return node.left;
		else {
			node.right = deleteMax(node.right);
			node.n = size(node.left) + size(node.right) + 1;
			return node;
		}
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = node.key.compareTo(key);
		if (cmp > 0)
			node.left = delete(node.left, key);
		else if (cmp < 0)
			node.right = delete(node.right, key);
		else {
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;
			Node t = node;//保存要删除的节点信息
			node = min(node.right);//找到后继结点, 就是右子树的最小结点
			node.right = deleteMin(t.right);//删除右子树最小结点, 把原右子树赋给后继结点
			node.left = t.left;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Iterator<Key> keys() {
		return keys(min(), max());
	}
	
	public Iterator<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new LinkedList<Key>();
		keys(root, q, lo, hi);
		return q.iterator();
	}
	
	private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
		if(node == null)
			return;
		int cmplo = lo.compareTo(node.key);
		int cmphi = hi.compareTo(node.key);
		if(cmplo < 0)
			keys(node.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >=0)
			queue.add(node.key);
		if(cmphi > 0)
			keys(node.right, queue, lo, hi);
	}
	
	public void midPrint() {
		midPrint(root);
	}
	
	private void midPrint(Node node) {
		if(node == null)
			return;
		midPrint(node.left);
		System.out.println(node.value);
		midPrint(node.right);
	}

	public void printTree() {
		printTree(root);
	}

	private void printTree(Node node) {
		System.out.println("node: " + node);
		System.out.println("	left: " + node.left);
		System.out.println("	right: " + node.right);
		System.out.println("	size: " + node.n);
		if (node.left != null)
			printTree(node.left);
		if (node.right != null)
			printTree(node.right);
	}

}

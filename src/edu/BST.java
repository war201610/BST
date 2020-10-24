package edu;

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

	private Node getNode(Node node, Key key) {
		if (node == null)
			return null;
		int cmp = node.key.compareTo(key);
		if (cmp > 0)
			return getNode(node.left, key);
		else if (cmp < 0)
			return getNode(node.right, key);
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

	public Key ceiling(Key key) {
		Node target = getNode(root, key);
		if (target == null) {
			System.out.println("key not found");
			return null;
		}
		return min(target.right).key;
	}

	public Key floor(Key key) {
		Node target = getNode(root, key);
		if (target == null) {
			System.out.println("key not found");
			return null;
		}
		return max(target.left).key;
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

	// node.left = deleteMin(node.left)删除后要更新原父节点的左子树
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
			node.left = t;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
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

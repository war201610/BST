package edu;

public class Run {

	public static void main(String[] arg0) {
		Key key1 = new Key(1);
		Key key2 = new Key(2);
		Key key3 = new Key(3);
		Key key4 = new Key(4);
		Key key5 = new Key(5);
		Key key6 = new Key(6);
		BST bst = new BST(key3, 3);
		bst.put(key1, 1);
		bst.put(key2, 2);
		bst.put(key5, 5);
		bst.put(key4, 4);
		bst.put(key6, 6);

		bst.printTree();
//		System.out.println(bst.get(new Key(100)));
//		System.out.println("max: " + bst.max());
//		System.out.println("min: " + bst.min());
//		System.out.println(bst.floor(key3));
//		System.out.println(bst.ceiling(key3));
	}

}

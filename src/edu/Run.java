package edu;

import java.util.Iterator;

public class Run {

	public static void main(String[] arg0) {
		Key key4 = new Key(4);		
		Key key5 = new Key(5);
		Key key6 = new Key(6);
		Key key7 = new Key(7);
		Key key8 = new Key(8);
		Key key9 = new Key(9);
		Key key = new Key(10);
		BST bst = new BST(key, 10);

		bst.put(key5, 5);
		bst.put(key4, 4);
		bst.put(key8, 8);
		bst.put(key6, 6);
		bst.put(key9, 9);
		bst.put(key7, 7);
		
		Iterator<Key> iterator = bst.keys(key4, key6);
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
//		bst.deleteMin();
//		bst.deleteMax();
//		bst.delete(key5);
//		System.out.println(bst.get(new Key(100)));
//		System.out.println("max: " + bst.max());
//		System.out.println("min: " + bst.min());
//		System.out.println(bst.floor(key3));
//		System.out.println(bst.ceiling(key3));
//		bst.midPrint();
//		bst.printTree();
	}

}

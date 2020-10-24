package edu;

public class Key implements Comparable<Key> {
	private int key;

	public Key(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public int compareTo(Key o) {
		if (key > o.key)
			return 1;
		else if (key < o.key)
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "" + key;
	}

}

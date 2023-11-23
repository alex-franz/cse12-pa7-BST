import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	Node<K,V> root;
	int size;
	Comparator<K> comparator;

	public BST() {
		this.root = null;
		this.size = 0;
	}

	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		if (key == null) { 
			throw new IllegalArgumentException("Key is null.");
		}
		int beforeSize = this.size();
		this.root = put(this.root, key, value);
		return this.size() != beforeSize;
	}
	/*
	 * Helper method for put() to traverse the tree and insert 
	 * the specified key value pair
	 */
	private Node<K,V> put(Node<K,V> node, K key,V value) {
		if ( node == null ) {
			size++;
			node = new Node<K,V>(key, value, null, null);
			return node;
		}
		int comp = node.getKey().compareTo(key);
		if ( comp < 0 ) {
			node.right = this.put(node.right,key,value);
			return node;
		} else if ( comp > 0 ) {
			node.left = this.put(node.left, key, value);
			return node;
		} else {
			return node; 
		}
	}

	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if ( key == null ) { throw new IllegalArgumentException("Key is null.");}
		this.root = this.replace(this.root, key, newValue);
		return get(this.root, key) != null;
	}
	/*
	 * Helper method for replace() method
	 */
	private Node<K,V> replace(Node<K,V> node , K key, V newValue) {
		if ( node == null ) {
			return null; 
		}
		int comp = node.getKey().compareTo(key);
		if ( comp > 0 ) {
			node.left = this.replace(node.left, key, newValue);
			return node;
		} else if ( comp < 0 ) {
			node.right = replace(node.right, key, newValue);
			return node;
		} else {
			node.setValue(newValue);
			return node;
		}
	}

	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if ( key == null ) {
			throw new IllegalArgumentException("Key is null.");
		}
		if ( this.containsKey(key) ) {
			this.root = this.remove(this.root, key);
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Remove helper method that searches through the tree. 
	 */

	private Node<K,V> remove( Node<K,V> node, K key ) {
		if ( node == null ) {
			return null;
		}
		int comp = node.getKey().compareTo(key);
		if ( comp > 0 ) {
			node.left = this.remove(node.left,key);
			return node;
		} else if ( comp < 0 ) {
			node.right = this.remove(node.right,key);
			return node; 
		} 
		// Current node is the node we need to remove
		if ( node.left == null && node.right == null ) { // Case for when node is a leaf 
			return null;
		}
		if ( node.left == null ) { // Case for when node has one child
			Node<K,V> temp = node.right;
			size--;
			return temp;
		} else if ( node.right == null ) { // Case for when node has one child
			Node<K,V> temp = node.left;
			size--;
			return temp;
		} else { // Case for node with two children
			Node<K,V> succ = findRightMin(node.right);
			node.key = succ.getKey();
			node.setValue(succ.getValue());
			node.right = this.remove(node.right, succ.getKey());
			size--;
			return node;
		}
	}

	private Node<K,V> findRightMin(Node<K,V> node) {
		if ( node.left == null ) {
			return node;
		} else {
			return findRightMin(node.left);
		}		
	}

	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if (key == null) { throw new IllegalArgumentException("Key is null.");}
		this.root = this.set(this.root,key, value);
	}
	/*
	 * Helper method for set method  
	 */
	private Node<K,V> set(Node<K,V> node,K key,V value) {
		if ( node == null ) {
			this.size++;
			return new Node<K,V>(key, value, null, null);
		}
		int comp = node.getKey().compareTo(key);
		if ( comp < 0 ) {
			node.right = this.set(node.right, key, value);
			return node;
		} else if ( comp > 0 ) {
			node.left = this.set(node.left, key, value);
			return node;
		} else {
			node.value = value;
			return node; 
		}

	}

	@Override
	public V get(K key) throws IllegalArgumentException {
		if ( key == null ) { 
			throw new IllegalArgumentException("Key is null.");
		} 
		return get(this.root, key);
	}
	/*
	 * Helper method for get.
	 */
	private V get( Node<K,V> node, K key ) {
		if ( node == null ) {
			return null;
		}
		int comp = node.getKey().compareTo(key);
		if ( comp > 0 ) {
			return get(node.left,key);
		} else if ( comp < 0 ) {
			return get(node.right,key);
		} else {
			return node.value;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if ( key == null ) {
			throw new IllegalArgumentException("Key is null.");
		}
		return get(this.root, key) != null;
	}
	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	@Override
	public List<K> keys() {
		List<K> keys = new ArrayList<K>();
		findKeys(this.root, keys);
		return keys;
	}
	/*
	 * Helper method for keys() method.
	 */
	private void findKeys(Node<K,V> node, List<K> keys) {
		if ( node != null ) {
			findKeys(node.left, keys);
			keys.add(node.getKey());
			findKeys(node.right, keys);
		}
	}
	
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {

		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;

		private Node(K key, V value, Node<K,V> left, Node<K,V> right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right; 

		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}		
	}
	 
}
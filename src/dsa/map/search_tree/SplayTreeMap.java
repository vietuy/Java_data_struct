package dsa.map.search_tree;

import java.util.Comparator;

import dsa.Position;

/**
 * SplayTreeMap implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {
    /**
     * SplayTreeMap constructor with null comparator
     */
	public SplayTreeMap() {
		super(null);
	}
	
    /**
     * SplayTreeMap constructor with given comparator
     * @param compare comparator
     */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	private void splay(Position<Entry<K, V>> p) {
		
		// Continue until node is the root
		while (!isRoot(p)) {
		   // Track the parent and grandparent nodes
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grandparent = parent(parent); 
		   
		   if (grandparent == null) {
		      // ZIG
		      // Perform a single rotation if there is no grandparent
		      rotate(p);
		   } else if ((parent == left(grandparent )) == (p == left(parent))) {
		      // ZIG-ZIG
		      // Rotate the parent around grandparent first
		      rotate(parent);
		      // Then rotate the node around the parent
		      rotate(p);
		   } else {
		      // ZIG-ZAG
		      // Rotate node around parent
		      rotate(p);
		      // Then rotate node around grandparent
		      rotate(p);
		   }
		}  
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}

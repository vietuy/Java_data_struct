package dsa.map.search_tree;

import java.util.Comparator;

import dsa.Position;

/**
 * AVL Tree map implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

    /**
     * AVLTreeMap constructor with null comparator
     */
	public AVLTreeMap() {
		super(null);
	}
    /**
     * AVLTreeMap constructor with given comparator
     * @param compare comparator
     */
	public AVLTreeMap(Comparator<K> compare) {
		super(compare);
	}

	/* Citing Help from the Textbooks
	 * The code for this method is based on the rebalance algorithm on page 487 in the course textbook
	 * "Data Structures & Algorithms" by Goodrich, Tamassia, Goldwasser.
	 */
    // Helper method to trace upward from position p checking to make
    // sure each node on the path is balanced. If a rebalance is necessary,
    // trigger a trinode resturcturing
	private void rebalance(Position<Entry<K, V>> p) {
		int newHeight = 0;
		int oldHeight  = 0;
		do {
			oldHeight = getProperty(p);
			if(!isBalanced(p)) {
				p = restructure(tallerChild(tallerChild(p)));
				recomputeHeight(left(p));
				recomputeHeight(right(p));
			}
			recomputeHeight(p);
			newHeight = getProperty(p);
			p = parent(p);
		} while(oldHeight != newHeight && p != null);
	}
	
    // Returns the child of p that has the greater height
    // If both children have the same height, use the child that 
    // matches the parent's orientation	
	private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
		if(getProperty(left(p)) > getProperty(right(p))) return left(p); 
		if(getProperty(left(p)) < getProperty(right(p))) return right(p); 
		
		if(isRoot(p)) return left(p);
		if(p == left(parent(p))) {
			return left(p);
		} else { return right(p); }
	}	

	private boolean isBalanced(Position<Entry<K, V>> p) {
		return Math.abs(getProperty(left(p)) - getProperty(right(p))) <= 1;
	}
	
	private void recomputeHeight(Position<Entry<K, V>> p) {
		int h = 1 + Math.max(getProperty(left(p)), getProperty(right(p)));
		setProperty(p, h);
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		rebalance(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> node) {
		if(!isRoot(node))
		{
			rebalance(parent(node));
		}
	}
}
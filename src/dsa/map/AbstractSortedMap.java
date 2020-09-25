package dsa.map;

import java.util.Comparator;

/**
 * Abstract Sorted Map
 * @author Viet Dinh
 *
 * @param <K> key 
 * @param <V> value
 */
public abstract class AbstractSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {

	private Comparator<K> compare;

	/**
	 * AbstractSortedMap constructor and tell it to Use the given comparator
	 * @param compare comparator
	 */
	public AbstractSortedMap(Comparator<K> compare) {
		if (compare == null) {
			this.compare = new NaturalOrder();
		} else {
			this.compare = compare;
		}
	}

	/**
	 * Used to compare two key
	 * @param key1 key #1
	 * @param key2 key #2
	 * @return the result of the comparation
	 */
	public int compare(K key1, K key2) {
		return compare.compare(key1, key2);
	}

	/**
	 * Comparator for the two element
	 * @author Viet Dinh
	 *
	 */
	private class NaturalOrder implements Comparator<K> {
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}
}
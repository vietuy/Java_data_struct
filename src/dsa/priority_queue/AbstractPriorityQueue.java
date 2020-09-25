package dsa.priority_queue;

import java.util.Comparator;

/**
 * AbstractPriorityQueue implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	private Comparator<K> comparator;

	/**
	 * AbstractPriorityQueue constructor with given comparator
	 * @param c comparator
	 */
	public AbstractPriorityQueue(Comparator<K> c) {
		setComparator(c);
	}
	
	private void setComparator(Comparator<K> c) {
		if(c == null) {
			c = new NaturalOrder();
		}
		comparator = c;
	}

	/**
	 * Comparator Class
	 * @author Viet Dinh
	 *
	 */
	public class NaturalOrder implements Comparator<K> {
		/**
		 * Compare the two given element
		 *@param first first element to compare
		 *@param second second element to compare
		 *@return result of the comparison
		 */
		public int compare(K first, K second) {
			return ((Comparable<K>) first).compareTo(second);
		}
	}

	/**
	 * Compare the two given element
	 *@param data1 first element to compare
	 *@param data2 second element to compare
	 *@return result of the comparison
	 */
	public int compare(K data1, K data2) {
		return comparator.compare(data1, data2);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

    // Make sure you import PriorityQueue.Entry and NOT Map.Entry!
	/**
	 * Priority Queue Entry
	 * @author Viet Dinh
	 *
	 * @param <K> key value
	 * @param <V> value value
	 */
	public static class PQEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		/**
		 * PriorityQueue Entry constructor
		 * @param key key
		 * @param value value
		 */
		public PQEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		/**
		 * Set the given key
		 * @param key key
		 */
		public void setKey(K key) {
			this.key = key;
		}
		/**
		 * Set the given value
		 * @param value value
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
	}
	
    // factory method for constructing a new priority queue entry object	
	/**
	 * Create a new entry and return it
	 * @param key key
	 * @param value value
	 * @return the new entry
	 */
	protected Entry<K, V> createEntry(K key, V value) {
		return new PQEntry<K, V>(key, value);
	}
}

package dsa.priority_queue;

/**
 * PriorityQueue interface
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public interface PriorityQueue<K, V> {

	/**
	 * Entry of PriorityQueue
	 * @author Viet Dinh
	 *
	 * @param <K> key 
	 * @param <V> value
	 */
	interface Entry<K, V> {
		/**
		 * return key of entry
		 * @return key of entry
		 */
		K getKey();
		/**
		 * return value of entry
		 * @return value of entry
		 */
		V getValue();
	}
	
	/**
	 * Adds the entry with key K and value V to the priority queue
	 * @param key key
	 * @param value value
	 * @return return the added entry
	 */
	Entry<K, V> insert(K key, V value);
	/**
	 * Returns, but does not remove, the entry with the smallest key
	 * @return entry with the smallest key
	 */
	Entry<K, V> min();
	/**
	 * Removes and returns the entry with the smallest key
	 * @return entry with the smallest key
	 */
	Entry<K, V> deleteMin();
	/**
	 * Returns the number of entries in the priority queue
	 * @return number of entries in the priority queue
	 */
	int size();
	/**
	 * Returns true if the priority queue is empty; otherwise, returns false
	 * @return true if the priority queue is empty; otherwise, returns false
	 */
	boolean isEmpty();
}


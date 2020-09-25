package dsa.priority_queue;

/**
 * AdaptablePriorityQueue interface
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {

	/**
	 * Removes an entry e from the priority queue
	 * @param entry queue's entry
	 */
	void remove(Entry<K, V> entry);
	/**
	 * Replaces the key of existing entry 
	 * @param entry queue's entry
	 * @param key key to be replaced
	 */
	void replaceKey(Entry<K, V> entry, K key);
	/**
	 * Replaces the value of the existing entry 
	 * @param entry queue's entry
	 * @param value value to be replace
	 */
	void replaceValue(Entry<K, V> entry, V value);	
}

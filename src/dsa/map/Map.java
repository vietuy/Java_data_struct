package dsa.map;


/**
 * Map interface
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public interface Map<K, V> extends Iterable<K> {
	/**
	 * Return the iterable of the map
	 * @return iterable of the map
	 */
	Iterable<Entry<K, V>> entrySet();
	/**
	 * Return the value at the given key
	 * @param key key
	 * @return the value at the given key
	 */
	V get(K key);
	/**
	 * Return true if map is empty, false otherwise
	 * @return true if map is empty, false otherwise
	 */
	boolean isEmpty();
	/**
	 * Adds the entry with key K and value V to the map, 
	 * if the key does not exist already (returns null), if already exists return the
	 * original value
	 * @param key key
	 * @param value value
	 * @return null if key does not exist, if exist, return original value of key  
	 */
	V put(K key, V value);
	/**
	 * Removes the entry with the given key 
	 * and returns the value V associated with that key K. If the key does not exist, returns null
	 * @param key key to be removed
	 * @return value of the key after removed
	 */
	V remove(K key);
	/**
	 * Return map's size
	 * @return map's size
	 */
	int size();
	/**
	 * return iterable of the value
	 * @return iterable of the value
	 */
	Iterable<V> values();
	
	/**
	 * Interface for entry
	 * @author Viet Dinh
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	interface Entry<K, V> {
		/**
		 * Return entry's key
		 * @return key
		 */
		K getKey();
		/**
		 * Return entry's value
		 * @return entry's value
		 */
		V getValue();
		/**
		 * Set the entry's key
		 * @param key key to be set
		 * @return key after set
		 */
		K setKey(K key);
		/**
		 * Set the entry's value
		 * @param value value to be set
		 * @return value after set
		 */
		V setValue(V value);
	}
}
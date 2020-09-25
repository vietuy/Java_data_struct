package dsa.map;

import java.util.Iterator;

import dsa.list.ArrayBasedList;

/**
 * ArrayBasedList that implement Map in an unordered way
 * @author Viet Dinh
 *
 * @param <K> key 
 * @param <V> value
 */
public class UnorderedArrayMap<K, V> extends AbstractMap<K, V> {

	// Use the adapter pattern to delegate to our existing
	// array-based list implementation
	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * Iniatilize the ArrayBasedList of Map
	 */
	public UnorderedArrayMap() {
		this.list = new ArrayBasedList<Entry<K, V>>();
	}
	
	// LookUp is a core behavior of all maps
    // This lookup should perform a sequential search
    // and return the index where the entry
    // is located. If the entry is not in the map, return -1
	private int lookUp(K key)
	{
        // You can use your ArrayBasedList's iterator to help
        // locate the entry with the specified Key
		Iterator<Entry<K, V>> it = list.iterator();
		int index = -1;
		while(it.hasNext()) {
			index += 1;
			if(it.next().getKey() == key) {
				return index;
			}
		}
		return -1;
	}
	
	@Override
	public V get(K key) {
		int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		if(index == 0) return list.get(index).getValue();
		return transpose(index);
	}
	
	@Override
	public V put(K key, V value) {
		int index = lookUp(key);
		if(index == -1) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			MapEntry<K, V> m = new MapEntry(key, value); 
			list.addFirst(m);
			return null;
		}
		V temp = list.get(index).getValue();
		list.get(index).setValue(value);
		transpose(index);
		return temp;
	}
	
	@Override
	public V remove(K key) {
       int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		V temp = list.get(index).getValue();
		list.remove(index);
		return temp;
	}

	@Override
	public int size() {
		return list.size();
	}
	
	private V transpose(int index)
	{ 
		V current = list.get(index).getValue();
		Entry<K, V> m =  list.get(index);
		if(index == 0) {
			return current;
		}
		list.set(index, list.get(index - 1));
		list.set(index - 1, m);
	//		return list.get(index++).getValue();
		return current;
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return list;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}

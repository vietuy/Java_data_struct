package dsa.map;

import java.util.Comparator;
import java.util.Iterator;

import dsa.list.ArrayBasedList;

/**
 * SearchTableMap implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {

	private ArrayBasedList<Entry<K, V>> list;

	/**
	 * SearchTableMap constructor with null comparator
	 */
	public SearchTableMap() {
		this(null);
	}
	
	/**
	 * SearchTableMap constructor with given comparator
	 * @param compare comparator
	 */
	public SearchTableMap(Comparator<K> compare) {
		super(compare);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	private int lookUp(K key) {
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
	public V put(K key, V value) {
		int index = lookUp(key);
		if(index == -1) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			MapEntry<K, V> m = new MapEntry(key, value); 
			if(list.isEmpty()) {
				list.addFirst(m);
			}
			int i = binarySearchHelper(0, list.size() - 1, key);
			if( i > 0) {
				list.add(i + 1, m);
			} else if(i < 0) {
				list.add(-1 * (i + 1), m);
			}
			
			return null;
		}
		V temp = list.get(index).getValue();
		list.get(index).setValue(value);
		return temp;
	}
	
	private int binarySearchHelper(int min, int max, K key) {
//		Algorithm binarySearchHelper(L, min, max, key)
//		Input a sorted list L with n elements
//		      a min index to consider
//		      a max index to consider   
//		      a target key value to locate
//		if min > max then
//		    return -1 * (min + 1)
//		mid = (max + min) / 2
//		if key( L.get(mid) ) = key then
//		    return mid
//		else if key( list.get(mid) ) > key then
//		    return binarySearchHelper(L, min, mid - 1, key)
//		else
//		    return binarySearchHelper(L, mid + 1, max, key)
		if (min > max) return -1 * (min + 1);
		int mid = (max + min) / 2;
		if (compare(list.get(mid).getKey(), key) == 0) {
		    return mid;
		} else if (compare(list.get(mid).getKey(), key) > 0) {
		    return binarySearchHelper( min, mid - 1, key);
		} else {
		    return binarySearchHelper( mid + 1, max, key);	
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public V get(K key) {
		int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		return list.get(index).getValue();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayBasedList<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}

	@Override
	public V remove(K key) {
		int index = lookUp(key);
		if(index == -1) {
			return null;
		}
		V temp = get(key);
		list.remove(index);
		return temp;
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
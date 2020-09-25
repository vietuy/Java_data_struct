package dsa.priority_queue;

import java.util.Comparator;

/**
 * HeapAdaptablePriorityQueue implementation
 * @author Viet Dinh
 *
 * @param <K> key 
 * @param <V> value
 */
public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

    // Adaptable PQ Entries must be location-aware so that the 
    // performance of replaceKey, replaceValue, and remove are O(log n)
	/**
	 * AdaptablePQEntry
	 * @author Viet Dinh
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	public static class AdaptablePQEntry<K, V> extends PQEntry<K, V> {

		private int index;

		/**
		 * AdaptablePQEntry constructor
		 * @param key key
		 * @param value value
		 * @param index index
		 */
		public AdaptablePQEntry(K key, V value, int index) {
			super(key, value);
			setIndex(index);
		}

		/**
		 * Return AdaptablePQEntry's index
		 * @return AdaptablePQEntry's index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Set AdaptablePQEntry's index
		 * @param index AdaptablePQEntry's index
		 */
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	/**
	 * HeapAdaptablePriorityQueue constructor with given constructor
	 * @param c comparator
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> c) {
		super(c);
	}
	
	/**
	 * HeapAdaptablePriorityQueue constructor with null constructor
	 */
	public HeapAdaptablePriorityQueue() {
		this(null);
	}
	
	// Factory method for creating a new adaptable PQ entry
	@Override
	protected AdaptablePQEntry<K, V> createEntry(K key, V value) {
		// A new adaptable PQ Entry added to the heap will be at index size()
		AdaptablePQEntry<K, V> temp = new AdaptablePQEntry<K, V>(key, value, size());
		return temp;
	}
	
    // Make sure the entry is a valid Adaptable PQ Entry and is located within the heap structure	
	private AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
		if(!(entry instanceof AdaptablePQEntry)){
			throw new IllegalArgumentException("Entry is not a valid adaptable priority queue entry.");
		}
		AdaptablePQEntry<K, V> temp = (AdaptablePQEntry<K, V>)entry;
		if(temp.getIndex() >= list.size() || list.get(temp.getIndex()) != temp) {
			throw new IllegalArgumentException("Invalid Adaptable PQ Entry.");
		}
		return temp;
	}
	
	@Override
	public void swap(int index1, int index2) {
		// Delegate to the super class swap method
		super.swap(index1, index2);
		// But then update the index of each entry so that they remain location-aware
		((AdaptablePQEntry<K, V>)list.get(index1)).setIndex(index1);
		((AdaptablePQEntry<K, V>)list.get(index2)).setIndex(index2);
	}

	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		int index = temp.getIndex();
		if(index == list.size() - 1)
			list.remove(list.size() - 1);
		else {
			swap(index, list.size() - 1);
			list.remove(list.size() - 1);
			bubble(index);
		}
	}
	
	private void bubble(int index) {
		if(index > 0 && compare(list.get(index).getKey(), list.get(parent(index)).getKey()) < 0) {
			upHeap(index);
		} else {
			downHeap(index);
		}
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setKey(key);
		bubble(temp.getIndex());
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> temp = validate(entry);
		temp.setValue(value);
	}
}

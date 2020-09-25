package dsa.priority_queue;

import java.util.Comparator;

import dsa.list.ArrayBasedList;

/**
 * HeapPriorityQueue implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

    // Remember that heaps can be easily implemented using an internal array representation
    // versus a linked representation.
	/**
	 * heap list
	 */
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * HeapPriorityQueue constructor with given comparator
	 * @param comparator comparator
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * HeapPriorityQueue constructor with null comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> answer = list.get(0);
		swap(0, list.size() - 1);
		list.remove(list.size() - 1);
		downHeap(0);
		return answer;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Up heap
	 * @param index index
	 */
	protected void upHeap(int index) {
		while(index > 0) {
			int parent = parent(index);
			if(compare(list.get(index).getKey(), list.get(parent).getKey()) >= 0) break;
			swap(index, parent);
			index = parent;
		}
        // The textbook has a non-recursive version of 
        //    the recursive algorithms presented in the lecture slides		
	}

	/**
	 * Swap the two entry at the given index
	 * @param index1 first index
	 * @param index2 second index
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * Down heap
	 * @param index index
	 */
	protected void downHeap(int index) {
        // The textbook has a non-recursive version of
        //    the recursive algorithms presented in the lecture slides
		while(hasLeft(index)) {
			int leftIndex = left(index);
			int smallChilIndex = leftIndex;
			if(hasRight(index)) {
				int rightIndex = right(index);
				if(compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0)
					smallChilIndex = rightIndex;
			}
			if(compare(list.get(smallChilIndex).getKey(), list.get(index).getKey()) >= 0) break;
			swap(index, smallChilIndex);
			index = smallChilIndex;
		}
	}
	
    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in jumping to parent or children
    //////////////////////////////////////////////////	

	/**
	 * Return the parent index 
	 * @param index index
	 * @return index of the parent
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}
	/**
	 * Return the left index 
	 * @param index index
	 * @return index of the left element
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * Return the right index 
	 * @param index index
	 * @return index of the right element
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * Return true if there's left element, false otherwise
	 * @param index index
	 * @return true if there's a left element, false otherwise
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}
	/**
	 * Return true if there's right element, false otherwise
	 * @param index index
	 * @return true if there's a left element, false otherwise
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}
}

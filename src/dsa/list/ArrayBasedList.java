/**
 * 
 */
package dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom ArrayList 
 * @author Viet Dinh
 *@param <E> generics
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	private final static int DEFAULT_CAPACITY = 10;
	private E[] data;

	private int size;

	/**
	 * Constructor for the Array
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Create an array with the given capacity and set size to 0
	 * @param capacity array's capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		
		if(size == data.length) {
			@SuppressWarnings("unchecked")
			E[] newList = (E[])(new Object[data.length * 2]);
			for(int i = 0; i < size; i++) {
				newList[i] = data[i];
			}
			data = newList;
		}
		size++;
		for(int i = size - 1; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = (E)value;
	}
	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		
//		if(index == size) {
//			temp = data[index];
//			size--;
//			return temp;
//		}
//		else {
		checkIndex(index);
			E temp = data[index];
			for(int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			data[size - 1 ] = null;
			size--;
//		}
		return temp;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E old = data[index];
		data[index] = value;
		return old;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Simple Iterator implementation
	 * @author Viet Dinh
	 */
	private class ElementIterator implements Iterator<E> {
	    private int position = 0;
	    private boolean removeOK;

	    public ElementIterator() {
	       position = 0;
	       removeOK = false;
	    }

	    public boolean hasNext() {
			return position < size;
	    }

	    public E next() {
	    	if(position == size()) throw new NoSuchElementException();
	    	removeOK = true;
	        return data[position++];
	    }
	        
	    public void remove() {
	    	if(!removeOK) throw new IllegalStateException();
	    	ArrayBasedList.this.remove(position - 1);
	    	position--;
	    	removeOK = false;
	    }
	}
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
	
}

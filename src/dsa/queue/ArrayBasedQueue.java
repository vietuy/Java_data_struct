
package dsa.queue;

import java.util.NoSuchElementException;

/**
 * Array Based Qeue functionality
 * @author Viet Dinh
 *
 * @param <E> generic
 */
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	private E[] data;
	@SuppressWarnings("unused")
	private int front;
	@SuppressWarnings("unused")
	private int rear;
	private int size;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Constructor for the queue wit given capacity. Set size to 0
	 * @param initialCapacity initial capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity)
	{
		data = (E[])(new Object[initialCapacity]);
		size = 0;
	}
	
	/**
	 * Constructor without  the parameter
	 */
	public ArrayBasedQueue()
	{
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(E value) {
		if(size == data.length) {
			@SuppressWarnings("unchecked")
			E[] newList = (E[])(new Object[data.length * 2]);
			for(int i = 0; i < size; i++) {
				newList[i] = data[i];
			}
			data = newList;
		}
		data[size] = value;
		size++;
	}

	@Override
	public E dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		E temp = data[0];
		for(int i = 0; i < size; i++ ) {
			data[i] = data[i + 1];
		}
		size--;
		return temp;
	}

	@Override
	public E front() {
		if(isEmpty()) throw new NoSuchElementException();
		return data[0];
	}

	@Override
	public int size() {
		return size;
	}
}
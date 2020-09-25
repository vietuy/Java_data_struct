package dsa.queue;


/**
 * Queue Interface
 * @author Viet Dinh
 *
 * @param <E> generics
 */
public interface Queue<E> {

	/**
	 * Add the given value to the queue
	 * @param value value to be add
	 */
	void enqueue(E value);
	/**
	 * Remove and return the first value
	 * @return the first value before remove
	 */
	E dequeue();
	/**
	 * Return the first element
	 * @return first element
	 */
	E front();
	/**
	 * Return the queue's size
	 * @return queue's size
	 */
	int size();
	/**
	 * Check if queue is empty
	 * @return true if queue is empty, false otherwise
	 */
	boolean isEmpty();
}
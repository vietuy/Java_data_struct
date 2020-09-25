package dsa.queue;


/**
 * Abstract Queue checking if queue is empty
 * @author Viet Dinh
 *
 * @param <E> generic
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
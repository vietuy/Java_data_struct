/**
 * 
 */
package dsa.list;

/**
 * Interface for list
 * @author Viet Dinh
 * @param <E> generics
 */
public interface List<E> extends Iterable<E> {
	/**
	 * Added the given value at the given index
	 * @param index index to add
	 * @param value value to add
	 */
	void add(int index, E value);
	/**
	 * Add the given value at first
	 * @param value value to be add at first
	 */
	void addFirst(E value);
	/**
	 * Add the given value at last
	 * @param value value to be add at last
	 */
	void addLast(E value);
	/**
	 * return the fist element
	 * @return first element
	 */
	E first();
	/**
	 * Find and return the element at the given index
	 * @param index of the element
	 * @return the element at the given index
	 */
	E get(int index);
	/**
	 * Check if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	boolean isEmpty();
	/**
	 * Return the last element
	 * @return last element
	 */
	E last();
	/**
	 * REmove the element at the given index
	 * @param index index of element
	 * @return the removed elements
	 */
	E remove(int index);
	/**
	 * Remove the first element and return it
	 * @return the first element
	 */
	E removeFirst();
	/**
	 * Remove the last element and return it
	 * @return last element
	 */
	E removeLast();
	/**
	 * Set the given value at the given index
	 * @param index index to set value
	 * @param value value to be set
	 * @return the given value
	 */
	E set(int index, E value);
	/**
	 * Return the size of list
	 * @return the size of list
	 */
	int size();
}

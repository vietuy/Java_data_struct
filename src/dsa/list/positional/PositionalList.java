/**
 * 
 */
package dsa.list.positional;
import dsa.Position;
/**
 * Interface for Position List
 * @author Viet Dinh
 *@param <E> generic
 */
public interface PositionalList<E> extends Iterable<E> {
	
	/**
	 * Add the given value after the given position
	 * @param p position
	 * @param value value to be add
	 * @return the position of the given value after add
	 */
	Position<E> addAfter(Position<E> p, E value);
	/**
	 * Add the given value before the given position
	 * @param p position
	 * @param value value to be add
	 * @return the position of the given value after add
	 */
	Position<E> addBefore(Position<E> p, E value);
	/**
	 * Add the given value at the first position
	 * @param value value to add
	 * @return the position of the given value after add
	 */
	Position<E> addFirst(E value);
	/**
	 * Add the given value at the last position
	 * @param value value to add
	 * @return the postion of the given value after add
	 */
	Position<E> addLast(E value);
	/**
	 * Find and return the position after the given position
	 * @param p position
	 * @return the position after the given position
	 */
	Position<E> after(Position<E> p);
	/**
	 * Find and return the position before the given position
	 * @param p position
	 * @return the position before the given position
	 */
	Position<E> before(Position<E> p);
	/**
	 * Return the first position
	 * @return first position
	 */
	Position<E> first();
	/**
	 * Check if the list is empty
	 * @return true if list is empty, false otherwise
	 */
	boolean isEmpty();
	/**
	 * Return the last position
	 * @return the last position
	 */
	Position<E> last();
	/**
	 * Return the Iterable object
	 * @return the object Iterable
	 */
	Iterable<Position<E>> positions();
	/**
	 * Remove the given element from the list
	 * @param p element to be removed
	 * @return the removed element
	 */
	E remove(Position<E> p);
	/**
	 * Set the given value at the given position
	 * @param p position to set the given value
	 * @param value value to be set
	 * @return the set value
	 */
	E set(Position<E> p, E value);
	/**
	 * Return size
	 * @return size
	 */
	int size();
}

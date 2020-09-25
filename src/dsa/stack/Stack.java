package dsa.stack;

/**
 * Stack interface
 * @author Viet Dinh
 *@param <E> generics
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack
	 * @param value to be push
	 */
	void push(E value);
	/**
	 * Removes and returns the element at the top of the stack
	 * @return the elements at the top
	 */
	E pop();
	/**
	 * Returns, but does not remove, the element at the top of the stack
	 * @return the elements at the top
	 */
	E top();
	/**
	 * Returns the number of elements in the stack
	 * @return stack's size
	 */
	int size();
	/**
	 * Returns true if the stack is empty; otherwise, returns false
	 * @return true if stack is empty, false otherwise
	 */
	boolean isEmpty();
}


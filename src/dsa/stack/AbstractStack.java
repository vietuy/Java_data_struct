package dsa.stack;

import dsa.stack.Stack;

/**
 * Abstract class for Stack
 * @author Viet Dinh
 *
 * @param <E> generics
 */
public abstract class AbstractStack<E> implements Stack<E> {
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}
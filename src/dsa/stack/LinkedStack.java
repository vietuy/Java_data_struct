package dsa.stack;

import java.util.EmptyStackException;

import dsa.list.SinglyLinkedList;

/**
 * LinkedStack functionality
 * @author Viet Dinh
 *
 * @param <E> generics
 */
public class LinkedStack<E> extends AbstractStack<E> {
	
	private SinglyLinkedList<E> list;
	
	/**
	 * Linked Stack constructor
	 */
	public LinkedStack()
	{
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void push(E value) {
		list.addFirst(value);
	}

	@Override
	public E pop() {
		if(isEmpty()) throw new EmptyStackException();
		return list.removeFirst();
	}

	@Override
	public E top() {
		if(isEmpty()) throw new EmptyStackException();
		return list.get(0);
	}

	@Override
	public int size() {
		return list.size();
	}
}
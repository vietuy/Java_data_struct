package dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list that has a pointer to the front and tail
 * @author Michael Dacanay
 *
 * @param <E> generic element
 */
public class SinglyLinkedList<E> extends AbstractList<E> {
	/** first node of the list */
	private LinkedListNode<E> front;
	/** last node of the list */
	private LinkedListNode<E> tail;
	
	/** number of elements in the list */
	private int size;
	
	/**
	 * Constructs SinglyLinkedList
	 */
	public SinglyLinkedList() {
//		Let front be a dummy (sentinel) node
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		if (value == null)
			throw new NullPointerException();
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (index == 0) {
			front.next = new LinkedListNode<E>(value, front.next);
			if(size == 0) 
				tail = front.next;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			LinkedListNode<E> temp = new LinkedListNode<E>(value, current.next);
			current.next = temp;
			if (index == size)
				tail = temp;
		}
		size += 1;
	}

	/**
	 * Returns, but does not remove, the element at the index
	 * @throws IndexOutOfBoundsException if the client tries to access an invalid index
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.next.data;
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next = current.next.next;
		if (index == size - 1)
			tail = current;
		size--;
		return temp;
	}

	/**
	 * Replaces the element at the specified index with the new element e
	 * @throws IndexOutOfBoundsException if the client tries to access an invalid index
	 */
	@Override
	public E set(int index, E value) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next.data = value;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns, but does not remove, the last element in the list
	 * @throws IllegalStateException if list is empty
	 */
	@Override
    public E last() {
		if (size == 0)
			throw new IllegalStateException();
        return tail.getElement();
    }
    
    @Override
    public void addLast(E value) {
        LinkedListNode<E> node = new LinkedListNode<E>(value);
        if (isEmpty()) {
    		front.next = node;
        } else {
        	tail.setNext(node); 
    	}
        tail = node;
        size++;
    }

	@Override
	public Iterator<E> iterator() {
		// We need to tell the iterator to skip the dummy/sentinel node
		return new ElementIterator(front); //.getNext()
	}
	
	/**
	 * Represents a node in linked list
	 * @author Michael Dacanay
	 *
	 * @param <E> element
	 */
	private static class LinkedListNode<E> {
//		the generic E type is from the class header of the LinkedListNode, not from the outer class
		/** element contained within node */
		private E data;
		/** reference to the next node in list */
        private LinkedListNode<E> next;
        
        /**
         * Constructs LinkedListNode
         * @param element to be contained in node
         */
        public LinkedListNode(E element) {
        	data = element;
        	next = null;
        }
        
        /**
         * Constructs LinkedListNode
         * @param element to be contained in node
         * @param next the next node
         */
        public LinkedListNode(E element, LinkedListNode<E> next) {
        	setElement(element);
        	setNext(next);
        }
        
        /**
         * Returns next node in list
         * @return next the next node
         */
        public LinkedListNode<E> getNext() {
        	return next;
        }
        
        /**
         * Returns the element contained in node
         * @return data the element in the node
         */
        public E getElement() {
        	return data;
        }
        
        /**
         * Set next node in the list
         * @param next the next node
         */
        public void setNext(LinkedListNode<E> next) {
        	this.next = next;
        }
        
        /**
         * Set data of the node
         * @param element to be set in node
         */
        public void setElement(E element) {
        	data = element;
        }
        
	}
	
	/**
	 * An iterator over a collection
	 * @author Michael Dacanay
	 *
	 */
	private class ElementIterator implements Iterator<E> {
	    // Keep track of the next node that will be processed
	    private LinkedListNode<E> current;
	    // Keep track of the node that was processed on the last call to 'next'
	    private LinkedListNode<E> previous;
	    // Keep track of the previous-previous node that was processed so that we can update 'next' links when removing
	    private LinkedListNode<E> previousPrevious;
	    /** true if able to remove element */
	    private boolean removeOK;

	    /**
	     * Constructs ElementIterator
	     * @param start
	     */
	    public ElementIterator(LinkedListNode<E> start) {
	        current = start;
	        removeOK = false;
	    }

	    /**
	     * Returns true if the iteration has more elements
	     */
	    public boolean hasNext() {
	        return current != null && current.getNext() != null; // && current != null
	    }

	    /**
	     * Returns the next element in the iteration.
	     */
	    public E next() {
	        if (!hasNext())
	        	throw new NoSuchElementException();
	        previous = current.next;
	        previousPrevious = current;
	        current = current.next;
	        removeOK = true;
	    	return previous.data;
	    }
	    
	    /**
	     * Removes from the underlying collection the last element returned by this iterator (optional operation). This method can be called only once per call to next(). The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
	     * @throws UnsupportedOperationException - if the remove operation is not supported by this iterator
	     * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after the last call to the next method
	     */
	    public void remove() {
	    	if (!removeOK)
	    		throw new IllegalStateException();
	    	previousPrevious.next = current.next;
	    	size--;
	    	removeOK = false;
	    }
	}
}

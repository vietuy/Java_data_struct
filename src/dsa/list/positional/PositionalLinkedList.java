package dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import dsa.Position;

/**
 * Positional Linked List functionality and its iterator
 * @author Viet Dinh
 * @param <E> generics
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	private PositionalNode<E> front;
	private PositionalNode<E> tail;
	private int size;

	/**
	 * Constructor for list and set size to 0
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}
	/**
	 * Positional Node functionality
	 * @author Viet Dinh
	 * @param <E> generics
	 */
	private static class PositionalNode<E> implements Position<E> {

        private E element;
        private PositionalNode<E> next;
        private PositionalNode<E> previous;

        public PositionalNode(E value) {
            this(value, null);
        }

        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }
	
	 /**
	 * Valid the position and return the positional node
	 * @param p position
	 * @return position node
	 */
	private PositionalNode<E> validate(Position<E> p) {
	        if (p instanceof PositionalNode) {
	            return (PositionalNode<E>) p;
	        }
	        throw new IllegalArgumentException("Position is not a valid positional list node.");
	}
	   
	private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
	     PositionalNode<E> newnode = new PositionalNode<E> (value, next, prev);
	     next.setPrevious(newnode);
	     prev.setNext(newnode);
	     size++;
	     return newnode;
	}
	   
	/**
	 * Position Iterator for Positional Linked List
	 * @author Viet Dinh
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> current;
		private boolean removeOK;
	    public PositionIterator(PositionalNode<E> start) {
	    	current = start;
	    	removeOK = false;
	    }

	    @Override
	    public boolean hasNext() {
	    	return !(current.getElement() == null);
	     }

	    @Override
	    public Position<E> next() {
	    	if(current == null) throw new IllegalStateException();
	    	Position<E> temp = current; 
	    	removeOK = true;
	    	current = after(current);
	    	
	    	return temp;
	    }

	    @Override
	    public void remove() {
	            // TODO your code here
	            // You should consider delegating to
	            // the outer class's remove(position) method,
	            // similar to:
	            // PositionalLinkedList.this.remove(position);
	    	if(!removeOK) throw new IllegalStateException();
	    		PositionalNode<E> currentnode = validate(current);
	    		PositionalLinkedList.this.remove(currentnode.previous);
	    		removeOK = true;
	    }
	}
	    
	/**
	 * Element Iterator for Positional Linked List
	 * @author Viet Dinh
	 */
	private class ElementIterator implements Iterator<E> {

		private Iterator<Position<E>> it;
		
		public ElementIterator(PositionalNode<E> start) {
			it = new PositionIterator(start);
	    }

		@Override
		public boolean hasNext() {
	        return it.hasNext() ;
	    }

		@Override
		public E next() {
			return it.next().getElement();
	    }

		@Override
		public void remove() {
	            it.remove();
		}
	}
	@Override
	public Iterator<E> iterator() {
		// we start at front.getNext() because front is a dummy/sentinel node
		return new ElementIterator(front.getNext());
	}
	/**
	 * Wrapper method that return the position iterator
	 * @author Viet Dinh
	 *
	 */
	private class PositionIterable implements Iterable<Position<E>> {
	        
		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator(front.getNext());
	    }
	}

	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> p2 = validate(p);
        Position<E> after = addBetween(value, p2.next, p2);
		return after;
	}

	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> p2 = validate(p);
		Position<E> before = addBetween(value, p2, p2.previous);
		return before;
	}

	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.next, front);
	}

	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.previous);
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> after = validate(p);
		if(after.next == null) {
			throw new NoSuchElementException();
		}
		return after.next;
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> before = validate(p);
		if(before.previous == null) {
			throw new NoSuchElementException();
		}
		return before.previous;
	}

	@Override
	public Position<E> first() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.next;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.previous;
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable(); 
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> pred = node.getPrevious();
		PositionalNode<E> succ = node.getNext();
		pred.setNext(succ);
		succ.setPrevious(pred);
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrevious(null);
		return temp;
	}

	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> p2 = validate(p);
		E temp = p2.getElement();
		p2.setElement(value);
		return temp;
	}

	@Override
	public int size() {
		return size;
	}
}
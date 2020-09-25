package dsa.tree;

import java.util.Iterator;

import dsa.Position;
import dsa.list.List;
import dsa.list.SinglyLinkedList;
import dsa.queue.ArrayBasedQueue;
import dsa.queue.Queue;

/**
 * AbstractTree with behaviors
 * @author Viet Dinh
 *
 * @param <E> element
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public Iterable<Position<E>> preOrder() {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for(Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }    
    
    @Override
    public Iterable<Position<E>> postOrder() {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            postOrderHelper(root(), list);
        }
        return list;
    }
    
    private void postOrderHelper(Position<E> p, List<Position<E>> list) {
//    	list.addFirst(p);
//    	if(isInternal(p)) {
//			for(Position<E> c : children(p)) {
//				postOrderHelper(c, list);
//			}
//		}
        for(Position<E> c : children(p)) {
            postOrderHelper(c, list);
        }
        list.addLast(p);
    }
    
    @Override
    public Iterable<Position<E>> levelOrder()
    {
        Queue<Position<E>> que = new ArrayBasedQueue<Position<E>>();
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            levelOrderHelper(root(), que, list);
        }
        return list;
    }
    
    private void levelOrderHelper(Position<E> p, Queue<Position<E>> queue, List<Position<E>> list) {
    	queue.enqueue(p);
		while(!queue.isEmpty()) {
			Position<E>  tempNode = queue.front();
			if(isInternal(tempNode)) {
		        for(Position<E> c : children(tempNode)) {
		            queue.enqueue(c);
		        }
			}
			list.addLast(queue.dequeue());
		}
    }
    /**
     * Abstract Node of the tree
     * @author Viet Dinh
     *
     * @param <E> element
     */
    protected abstract static class AbstractNode<E> implements Position<E> {

        private E element;
        
        /**
         * AbstractNode constructor with the given element
         * @param element element of the node
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Set the given element at the given node
         * @param element element
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Element Iterator for the map
     * @author Viet Dinh
     *
     */
    protected class ElementIterator implements Iterator<E> {
        private Iterator<Position<E>> it;
        
        /**
         * Element iterator constructor with the given iterator
         * @param iterator iterator to use
         */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
}
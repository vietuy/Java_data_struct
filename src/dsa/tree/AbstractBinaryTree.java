package dsa.tree;
import java.util.Iterator;

import dsa.Position;
import dsa.list.List;
import dsa.list.SinglyLinkedList;

/**
 * AbstractBinaryTree with its functionality
 * @author Viet Dinh
 *
 * @param <E> generic element 
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();

        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }

        return traversal;
    }
    
    /**
     * the code for this method is based on the remove method on page 342 in the course textbook
     * "Data Structures & Algorithms" by Goodrich, Tamassia, Goldwasser.
     */
    private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
        if(left(node) != null) {
        	inOrderHelper(left(node), traversal);
        }
        traversal.addLast(node);
        if(right(node) != null) inOrderHelper(right(node), traversal);
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
//    	if(isInternal(left(p)) && isInternal(right(p))) {
//    		return null;
    	
//    	if(numChildren(parent(p)) > 1 && parent(right(p)) == parent(p)) {
//		return right(p);
//	} else if(numChildren(parent(p)) > 1 && parent(left(p)) == parent(p)) {
//		return left(p);
//	}
    	if(isRoot(p)) {
    		return null;
    	}
    	Position<E> parent = parent(p);
    	if(numChildren(parent) == 2) {
    		if(right(parent) != p) return right(parent);
    		if(left(parent) != p) return left(parent);
    	}
        return null;
    }
    
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    @Override
    public int numChildren(Position<E> p) {
    	int count = 0;
        for(@SuppressWarnings("unused") Position<E> c : children(p)) {
           count += 1;
        }
        return count;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            ret.addLast(left(node));
        }
        if(right(node) != null) {
            ret.addLast(right(node));
        }
        return ret;
    }
}


package dsa.tree;

import java.util.Iterator;

import dsa.Position;
import dsa.list.List;
import dsa.list.SinglyLinkedList;
import dsa.list.positional.PositionalLinkedList;
import dsa.list.positional.PositionalList;

/**
 * GeneralTree Implementation
 * @author Viet Dinh
 *
 * @param <E> generic element
 */
public class GeneralTree<E> extends AbstractTree<E> implements GeneralTreeCollection<E> {

    private Node<E> root;
    private int size;
    
    /**
     * General tree constructor
     */
    public GeneralTree() {
        root = null;
        size = 0;
    }
    
    @Override
    public Position<E> root() {
        return root;
    }

    private Node<E> validate(Position<E> p) {
        if(!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a legal general tree node");
        }
        return (Node<E>)p;
    }
    
    @Override
    public Position<E> parent(Position<E> p) {
        return validate(p).getParent();
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        for(Position<Node<E>> n : node.getChildren().positions())
        {
            ret.addLast(n.getElement());
        }
        return ret;
    }

    @Override
    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren().size();
    }
    
    @Override
    public Position<E> addRoot(E value) {
        if(root != null) {
            throw new IllegalArgumentException("Tree already has a root");
        }
        this.root = createNode(value);
        size = 1;
        return root;
    }   

    @Override
    public Position<E> addChild(Position<E> p, E value) {
        Node<E> node = validate(p);
        Node<E> newNode = createNode(value);
        node.getChildren().addLast(newNode);
        newNode.setParent(node);
        size++;
        return newNode;
    }

    @Override
    public E set(Position<E> p, E value) {
        Node<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    /**
     * Create a general tree node with the given element
     * @param element element
     * @return created node
     */
    public Node<E> createNode(E element) {
        return new Node<E>(element);
    }
	
	@Override
	public int size() {
		return size;
	}

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(preOrder().iterator());
    }

	@Override
	public E remove(Position<E> p) {
	    // We will only support removal of a node that only has 1 child
	    if(numChildren(p) > 1) {
	        throw new IllegalArgumentException("The node has more than 1 child.");
	    }
	    // Handle special case if the node being removed is the root
	    if(isRoot(p)) {
	        E original = p.getElement();
	        if(numChildren(p) == 0) {
	            this.root = null;
	        } else {
	            Node<E> replacement = validate(p).getChildren().first().getElement();
	            replacement.setParent(null);
	            this.root = replacement;
	        }
	        size--;
	        return original;
	    }
	    // Handle the case where the node being removed is NOT the root
	    Node<E> node = validate(p);
	    Node<E> parent = validate(parent(p));
	    // Create an iterator over the parent node's children positions
	    Iterator<Position<Node<E>>> it = parent.getChildren().positions().iterator();
	    while(it.hasNext()) {
	        // Find the position of the node to be removed
	        Position<Node<E>> current = it.next();
	        if(current.getElement() == node) {
	            if(numChildren(p) == 1) {
	                // If the node being removed has 1 child, replace it
	                // in the parent's list of children
	                Node<E> replacement = node.getChildren().first().getElement();
	                replacement.setParent(parent);
	                parent.getChildren().set(current, replacement);
	            } else {
	                // If the node being removed has 0 children, remove it
	                parent.getChildren().remove(current);
	            }
	        }
	    }
	    size--;
	    return node.getElement();
	}

	/**
	 * General Tree node that extends abstract node
	 * @author Viet Dinh
	 *
	 * @param <E> generic element
	 */
	private static class Node<E> extends AbstractNode<E> {

        private Node<E> parent;
        
        // A general tree node needs to maintain a list of children
        private PositionalList<Node<E>> children;
        
        public Node(E element) {
            this(element, null);
        }
        
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
            children = new PositionalLinkedList<Node<E>>();
        }
        
        public void setParent(Node<E> p) {
            parent = p;
        }
        
        public Node<E> getParent() {
            return parent;
        }
        
        public PositionalList<Node<E>> getChildren() {
            return children;
        }
    }

}


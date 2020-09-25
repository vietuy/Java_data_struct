package dsa.tree;

import dsa.Position;

/**
 * LinkedBinaryTree implementation
 * @author Viet Dinh
 *
 * @param <E> generic element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    private Node<E> root;
    private int size;

    /**
     * LinkedBinaryTree constructor, set root to null and size to 0
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * Checked if the position is a node and return it
     * @param p position 
     * @return node of the given position
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    /**
     * Node of the binary tree
     * @author Viet Dinh
     *
     * @param <E> generic element
     */
    public static class Node<E> extends AbstractNode<E> {
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * Node constructor with given element
         * @param element element
         */
        public Node(E element) {
            this(element, null);
        }

        /**
         * Node constructor with given element and parent
         * @param element element 
         * @param parent parent
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Return the left's node
         * @return left's node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Return the right's node
         * @return right's node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Set the given node to be left
         * @param left left
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * Set the given node to be right
         * @param right right node
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * Return the node's parent
         * @return node's parent 
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Set the node parent
         * @param parent parent
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> parent = validate(p);
        if (left(parent) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        Node<E> child = createNode(value, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> parent = validate(p);
        if (right(parent) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        Node<E> child = createNode(value, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }
    /**
     * the code for this method is based on the remove method on page 329 in the course textbook
     * "Data Structures  Algorithms" by Goodrich, Tamassia, Goldwasser.
     */
    @Override
    public E remove(Position<E> p) {
    	Node<E> node = validate(p);
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        
        Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
        if(child != null) child.setParent(node.getParent());
        if(node == root) {
        	root = child;
        } else {
        	Node<E> parent = node.getParent();
        	if(node == parent.getLeft()) {
        		parent.setLeft(child);
        	} else { 
        		parent.setRight(child);
        	}
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Create a node with given element
     * @param e element
     * @param parent parent's node
     * @param left left's node
     * @param right right's node
     * @return the new created node
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    /**
     * Set the root to the given position
     * @param p position 
     * @return position of root
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}


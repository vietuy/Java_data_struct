package dsa.map.search_tree;

import java.util.Comparator;

import dsa.Position;
import dsa.list.ArrayBasedList;
import dsa.list.List;
import dsa.map.AbstractSortedMap;
import dsa.map.Map.Entry;
import dsa.tree.BinaryTree;
import dsa.tree.LinkedBinaryTree;

/**
 * Binary search tree map implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
        implements BinaryTree<Entry<K, V>> {

    // The BalanceableBinaryTree class is an inner class below
    private BalanceableBinaryTree<K, V> tree;

    /**
     * BinarySearchTreeMap constructor with null comparator
     */
    public BinarySearchTreeMap() {
        this(null);
    }

    /**
     * BinarySearchTreeMap constructor with given comparator
     * @param compare comparator
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }

    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2      
        return (tree.size() - 1) / 2;
    }

    // This method is used to add dummy/sentinel left and right children as leaves
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry      
        tree.set(p, entry);
        // Then add new dummy/sentinel children     
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    // This helper method traces a path down the tree to locate the position
    // that contains an entry with the given key.
    // Think of "lookUp" as returning the last node visited when tracing
    // a path down the tree to find the given key
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key         
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use        
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        // Create the new map entry     
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If the last node visited is a dummy/sentinel node        
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return original;
        }
    }

    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If p is a dummy/sentinel node        
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)         
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor               
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move p to the replacement node in the right subtree              
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a child)...         
            Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
            // ... then get its sibling (will be another sentinel or an actual entry node)          
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your binary tree remove method)            
            remove(leaf);
            // Remove the NODE (this is your binary tree remove method)
            // which will "promote" the sib node to replace p           
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnDelete(sib);
            return original;
        }
    }

    // Returns the inorder successor (the minimum from the right subtree)
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
        Position<Entry<K, V>> current = node;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
        for (Position<Entry<K, V>> n : tree.inOrder()) {
        	if(n.getElement() == null) continue;
            set.addLast(n.getElement());
        }
        return set;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    // This is a "hook" method that will be overridden in 
    // your AVL, Splay, and Red-Black tree implementations
    /**
     * Performed the action on insert
     * @param node node to do the action on
     */
    protected void actionOnAccess(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    // This is a "hook" method that will be overridden in 
    // your AVL, Splay, and Red-Black tree implementations
    /**
     * Performed the action on insert
     * @param node node to do the action on
     */
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    // This is a "hook" method that will be overridden in 
    // your AVL, Splay, and Red-Black tree implementations
    /**
     * Performed the action on delete
     * @param node node to do the action on
     */
    protected void actionOnDelete(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * This class balance the binary tree
     * @author Viet Dinh
     *
     * @param <K> key
     * @param <V> value
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        // Relink is a helper method for trinode restructuring and rotations
        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
	       child.setParent( parent );
	      if (makeLeftChild) {
	         parent.setLeft( child );
	      } else {
	         parent.setRight( child );
      }
        }

        /* Citing Help from the Textbooks
         * The code for this method is based on the rotate algorithm on page 278 in the course textbook
         * "Data Structures & Algorithms" by Goodrich, Tamassia, Goldwasser.
         */
        /**
         * Helper method for handling rotations and relinking nodes
         * @param p node;
         */
        public void rotate(Position<Entry<K, V>> p) {
        	Node<Entry<K, V>> node = validate(p); 
        	Node<Entry<K, V>> parent = node.getParent(); 
        	Node<Entry<K, V>> grandparent = parent.getParent(); 
        	if (grandparent == null) {
        		setRoot(node);
        		node.setParent(null); 
        	} else {
        		if(parent == left(grandparent)) {
        			relink(grandparent, node, true);
        		} else {
        			relink(grandparent, node, false);
        		}
        	}
        	if (node == left(parent))  {
        	   relink(parent, node.getRight(), true);
        	   relink(node, parent, false);
        	} else {
        	   relink(parent, node.getLeft(), false);
        	   relink(node, parent, true);
        	}
        }

        
        /** 
         * helper method to perform a trinode restructuring
         * and trigger the appropriate rotations
         * @param x node
         * @return return parent or x after rotate
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        	Node<Entry<K, V>> node = validate(x); 
        	Node<Entry<K, V>> parent = node.getParent(); 
        	Node<Entry<K, V>> grandparent = parent.getParent(); 
        	if((node.equals(right(parent))) == parent.equals(right(grandparent))) {
        		rotate(parent);
        		return parent;
        	} else {
        		rotate(x);
        		rotate(x);
        		return x;
        	}
        }

        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
                Node<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        // A binary search tree node needs to keep track of some property.
        // For example, for AVL trees the "property" is the height of the node.
        // For Red-Black trees, the "property" is the color of the node.
        /**
         * binary search tree node
         * @author Viet Dinh
         *
         * @param <E> generics element
         */
        protected static class BSTNode<E> extends Node<E> {

            private int property;

            /**
             * BSTNode with given element
             * @param element element
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }

            /**
             * Set the height
             * @param height height
             */
            public void setProperty(int height) {
                this.property = height;
            }

            /**
             * Reuturn the height of node
             * @return height
             */
            public int getProperty() {
                return property;
            }
        }

        /**
         * Return height property
         * @param p position
         * @return height property
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }

        /**
         * Set property height
         * @param p position
         * @param value value
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which extends 
    // your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
        return tree.children(p);
    }

    @Override
    public int numChildren(Position<Entry<K, V>> p) {
        return tree.numChildren(p);
    }

    @Override
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    /**
     * Set the position with the entry value
     * @param p position
     * @param entry entry
     * @return the entry that is setted
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }

    @Override
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }

    @Override
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> preOrder() {
        return tree.preOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> postOrder() {
        return tree.postOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> levelOrder() {
        return tree.levelOrder();
    }

    @Override
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    /**
     * Remove the given position
     * @param p position 
     * @return the removed entry
     */
    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    @Override
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> inOrder() {
        return tree.inOrder();
    }

    /**
     * Rotate the given position
     * @param p position
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * Restructure the given position and return it
     * @param x position
     * @return position after restructure
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * Return height of the given position
     * @param p position
     * @return height
     */
    public int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }

    /**
     * Set Height
     * @param p position
     * @param value value
     */
    public void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }
}
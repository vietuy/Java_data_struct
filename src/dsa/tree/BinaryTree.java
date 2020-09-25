package dsa.tree;

import dsa.Position;

/**
 * Binary Tree
 * @author Viet Dinh
 *
 * @param <E> generic
 */
public interface BinaryTree<E> extends Tree<E> {
    /**
     * Returns the left child of the node p
     * @param p position
     * @return left child position of the given position
     */
    Position<E> left(Position<E> p);
    /**
     * Returns the right child of the node p
     * @param p position
     * @return right child of the node p
     */
    Position<E> right(Position<E> p);
    /**
     * Returns the sibling of the node p
     * @param p position
     * @return sibling of the node p
     */
    Position<E> sibling(Position<E> p);
    
    /**
     * Return the in order iterable of the tree
     * @return iterable tree of the tree
     */
    Iterable<Position<E>> inOrder();
}
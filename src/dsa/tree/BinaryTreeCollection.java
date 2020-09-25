package dsa.tree;

import dsa.Position;

/**
 * BinaryTreeCollection with add/remove/set functionality
 * @author Viet Dinh
 *
 * @param <E> generic element
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
    /**
     * Adds an element as the root of the tree, then returns a reference to the new root
     * @param value value to be added
     * @return reference to the new root
     */
    Position<E> addRoot(E value);
    /**
     * Adds an element as the left child of position P, then returns a reference to the new child
     * @param p position 
     * @param value value
     * @return reference to the new child
     */
    Position<E> addLeft(Position<E> p, E value);
    /**
     * Adds an element as the right child of position P, then returns a reference to the new child
     * @param p position
     * @param value value
     * @return reference to the new child
     */
    Position<E> addRight(Position<E> p, E value);
    /**
     * Removes the position P from the tree, and returns the value that was stored in the removed position
     * @param p position
     * @return value that was stored in the removed position
     */
    E remove(Position<E> p);
    /**
     * Updates the element stored at position P, and returns the original element that was overridden
     * @param p position
     * @param value value
     * @return original element that was overridden
     */
    E set(Position<E> p, E value);
}


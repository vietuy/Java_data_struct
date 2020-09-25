package dsa.tree;

import dsa.Position;

/**
 * GeneralTreeCollection with add/remove/manipulate behaviors
 * @author Viet Dinh
 *
 * @param <E> element
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
    /**
     * Adds an element as the root of the tree, then returns a reference to the new root
     * @param value element to be added
     * @return reference to the new root
     */
    Position<E> addRoot(E value);
    /**
     * Adds an element as the child of position P, then returns a reference to the new child
     * @param p position
     * @param value element to added
     * @return reference to the new child
     */
    Position<E> addChild(Position<E> p, E value);
    /**
     * Removes the position P from the tree, and returns the value that was stored in the removed position
     * @param p position to be remove
     * @return the original value
     */
    E remove(Position<E> p);
    /**
     * Updates the element stored at position P, and returns the original element that was overridden
     * @param p position to be set
     * @param value value to be set
     * @return original element that was overridden
     */
    E set(Position<E> p, E value);
}
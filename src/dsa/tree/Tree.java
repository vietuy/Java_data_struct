package dsa.tree;
import dsa.Position;

/**
 * Tree interface
 * @author Viet Dinh
 *
 * @param <E> element
 */
public interface Tree<E> {

    /**
     * Returns the root of the tree
     * @return the root of the tree
     */
    Position<E> root();
    /**
     * Returns the parent of the given node p
     * @param p position to be looked up
     * @return position of the parent of the given node
     */
    Position<E> parent(Position<E> p);
    /**
     * Returns a list of children of the given node p
     * @param p position
     * @return list of children
     */
    Iterable<Position<E>> children(Position<E> p);
    /**
     * Returns the number of children of the given node p
     * @param p position
     * @return number of children
     */
    int numChildren(Position<E> p);
    /**
     * Returns true if the node has one or more children
     * @param p position
     * @return true if the node has one or more children
     */
    boolean isInternal(Position<E> p);
    /**
     * Checked if the given node is a leaf
     * @param p position
     * @return true if given p is leaf, false otherwise
     */
    boolean isLeaf(Position<E> p);
    /**
     * Checked if the given position is a root
     * @param p position
     * @return true if given p is a root
     */
    boolean isRoot(Position<E> p);
    /**
     * Return the size of the map
     * @return size of the map
     */
    int size();
    /**
     * Check if the map is empty
     * @return true if the map is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Return iterable of the map in pre order
     * @return iterable of the map in pre order
     */
    Iterable<Position<E>> preOrder();
    /**
     * Return iterable of the map in post order
     * @return iterable of the map in post order
     */
    Iterable<Position<E>> postOrder();
    /**
     * Return iterable of the map in level order
     * @return iterable of the map in level order
     */
    Iterable<Position<E>> levelOrder();
}

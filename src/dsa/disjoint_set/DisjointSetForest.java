package dsa.disjoint_set;

import dsa.Position;

/**
 * DisjointSetForest inteface
 * @author Viet Dinh
 *
 * @param <E> generic elements
 */
public interface DisjointSetForest<E> {

    /**
     * Creates a disjoint set that contains the element e, then returns the position of the set
     * @param value value to make set
     * @return position of the set
     */
    Position<E> makeSet(E value);
    /**
     * Returns the position of the identifier for the disjoint set that contains the element
     * @param value value to find 
     * @return Returns the position of the identifier
     */
    Position<E> find(E value);
    /**
     * Merges the disjoint sets that contain positions s and t
     * @param s first set
     * @param t second set
     */
    void union(Position<E> s, Position<E> t);

}


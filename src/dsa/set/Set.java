package dsa.set;

/**
 * Set interface
 * @author Viet Dinh
 *
 * @param <E> generic element
 */
public interface Set<E> extends Iterable<E> {
    /**
     * Adds the element e to the set (if e is not already present in the set)
     * @param value to be added
     */
    void add(E value);
    /**
     * Returns true if the set contains the element e; otherwise, returns false
     * @param value value to checked
     * @return true if set contains the element, false otherwise
     */
    boolean contains(E value);
    /**
     * Removes and returns the element e from the set (if e is present in the set)
     * @param value value to remove
     * @return removed value
     */
    E remove(E value);
    /**
     * Returns true if set is empty, false otherwise
     * @return true if set is empty, false otherwise
     */
    boolean isEmpty();
    /**
     * Return size of the set
     * @return size of the set
     */
    int size();
    
    /**
     * Updates the current set to also include all elements contained in the set T (also called “union”)
     * @param other set to do union
     */
    void addAll(Set<E> other);
    /**
     * Updates the current set to keep only those elements that are also elements in T (also called “intersection”)
     * @param other set to do intersection
     */
    void retainAll(Set<E> other);
    /**
     * Updates the current set to remove any of the elements that are contained in T (also called “subtraction”)
     * @param other set to do subtraction
     */
    void removeAll(Set<E> other);
}


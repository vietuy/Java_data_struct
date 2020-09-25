/**
 * 
 */
package dsa.sorter;

import java.util.Comparator;

import dsa.sorter.Sorter;

/**
 * This class provide the common sorter functionality
 * @author Viet Dinh
 *  @param <E> generics
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

    private Comparator<E> comparator;
    
    /**
     * Constructor with comparator 
     * @param comparator comparator to use
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
           comparator = new NaturalOrder();
        }
        this.comparator = comparator;
    }     
    
    /**
     * Inner class with the compare method use to compare two elements
     * @author Viet Dinh
     *
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * This method use the comparator to compare and return it
     * @param data1 data 1
     * @param data2 data 2
     * @return the result of the comparation
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}

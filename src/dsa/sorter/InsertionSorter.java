package dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @param <E> generics */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
    /**
     * The constructor for Insertion Sorter with null comparator
     */
    public InsertionSorter() {
        this(null);
    }
    
    /**
     * This set the comparator to use in insertion sort
     * @param comparator comparator to use
     */
    public InsertionSorter(Comparator<E> comparator) {
    	super(comparator);
  
    }
    
	/**
	 *Sort by Insertion
	 *
	 */
	@Override
	public void sort(E[] list) {
		int j = 0;
		E x;
		for(int i = 1; i < list.length; i++) {
			x = list[i];
			j = i - 1;
			while (j >= 0 && compare(list[j], x) > 0) {
				list[j + 1] = list[j];
				j = j - 1;
			}
			list[j + 1] = x;
		}
	}
}

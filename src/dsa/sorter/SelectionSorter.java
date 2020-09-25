package dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
    /**
     * The constructor for Selection Sorter with null comparator
     */
    public SelectionSorter() {
        this(null);
    }  
    
    /**
     * This set the comparator to use in selection sort
     * @param comparator comparator to use
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
       

    /**
     *Sort by selection
     *@param data list of data
     */
    public void sort(E[] data) {
//    	   for i <-- 0 up to n-1 do
//    		      min <-- i
//    		      for j <-- i+1 up to n-1 do
//    		         if A[j] < A[min] then
//    		            min <-- j 
//    		   if NOT i = min then
//    		      x <-- A[i]
//    		      A[i] <-- A[min]
//    		      A[min] <-- x 
    	int min = 0;
    	E x;
        for(int i = 0; i < data.length; i++) {
        	min = i;
        	for(int j = i + 1; j < data.length; j++ ) {
        		if(data[j].compareTo(data[min]) < 0) {
        			min = j;
        		}
        	}
        	if( i != min) {
        		x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    	
    }
}

package dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter implementation with sort 
 * @author Viet Dinh
 *
 * @param <E> generic
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	 private PivotSelector selector;
    /**
     * Pivot selection strategies that return the first element
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    /**
     * Pivot selection strategies that return the last element
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    /**
     * Pivot selection strategies that return the middle element
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    /**
     * Pivot selection strategies that return the random element
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /**
     * Nested Class that return the 1st element to be pivot point
     * @author Viet Dinh
     */
    public static class FirstElementSelector implements PivotSelector {

    	/**
    	 * FirstElementSelector constructor
    	 */
    	public FirstElementSelector() {
    		// do nothing
    	}
   	
		@Override
		public int selectPivot(int low, int high) {
			return low;
		}
    }
    /**
     * Nested Class that return the last element to be pivot point
     * @author Viet Dinh
     */
    public static class LastElementSelector implements PivotSelector {
      	/**
    	 * LastElementSelector constructor
    	 */
    	public LastElementSelector() {
    		// do nothing
    	}
    	
		@Override
		public int selectPivot(int low, int high) {
			return high;
		}
    }
    /**
     * Nested Class that return the middle element to be pivot point
     * @author Viet Dinh
     */
    public static class MiddleElementSelector implements PivotSelector {
      	/**
    	 * MiddleElementSelector constructor
    	 */
    	public MiddleElementSelector() {
    		// do nothing
    	}
		@Override
		public int selectPivot(int low, int high) {
			return (high - low) / 2 ;
		}
    }
    /**
     * Nested Class that return the random element to be pivot point
     * @author Viet Dinh
     */
    public static class RandomElementSelector implements PivotSelector {
      	/**
    	 * RandomElementSelector constructor
    	 */
    	public RandomElementSelector() {
    		// do nothing
    	}

		@Override
		public int selectPivot(int low, int high) {
			Random rand = new Random();
			return rand.nextInt(high - low) + low;
		}
    }
	/**
	 * QuickSorter constructor with the given comparator and pivot selector
	 * @param comparator quicksorter comparator
	 * @param selector pivot selector
	 */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
	/**
	 * QuickSorter constructor given comparator, and without the pivot point
	 * @param comparator QuickSorter comparator
	 */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}    
    
    /**
     * QuickSorter constructor with given pivot selector, but without comparator
     * @param selector Pivot selector
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }
	
    /**
     * Constructor without the parameter
     */
    public QuickSorter() {
        this(null, null);
    }
    
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            selector = new RandomElementSelector();
        }
        this.selector = selector;
    }
 
	@Override
	public void sort(E[] list) {
		quickSort(list, 0, list.length - 1);
	}
	
	private void quickSort(E[] list, int low, int high) {
//		if low < high then
//		   pivotLocation <-- partition(T, low, high)
//		   quickSort(T, low, pivotLocation - 1)
//		   quickSort(T, pivotLocation + 1, high)
		if(low < high ) {
			int pivotLocation = partition(list, low, high);
			quickSort(list, low, pivotLocation - 1);
			quickSort(list, pivotLocation + 1 , high);
		}
	}
	
    private int partition(E[] list, int low, int high) {
    	// Select a Pivot element
//    	pivotIndex <-- selectPivot(low, high)
//    	// Swap the pivot to be the last element in the array
//    	swap( T, pivotIndex, high )
//    	return partitionHelper(T, low, high)
    	int pivotIndex = selector.selectPivot(low, high);
    	swap(list, pivotIndex, high);
		return partitionHelper(list, low, high);
	}
	private int partitionHelper(E[] list, int low, int high) {
//		pivot <-- T[high];
//		index <-- low; // index of smaller element
//		for j <-- low to high - 1 do
//		   if T[j] <= pivot then
//		      swap( T, index, j )
//		      index <-- index + 1
//		// swap the index with the pivot (T[high] is the pivot)
//		swap( T, index, high )
//
//		// Return the index of the pivot element
//		return index;
		E pivot = list[high];
		int index = low;
		for(int j = low; j < high; j++) {
			if(compare(list[j], pivot) <= 0) {
				swap(list, index, j);
				index = index + 1;
			}
		}
		swap(list, index, high);
		return index;
	}
	private void swap(E[] list, int index1, int index2) {
    	E temp = list[index2];
    	list[index2] = list[index1];
    	list[index1] = temp;
	}
	/**
     * Interface for choosing pivot point
     * @author Viet Dinh
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
        
    }
}
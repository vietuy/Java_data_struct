/**
 * 
 */
package dsa.sorter;

import java.util.Comparator;

/**
 *  BubbleSorter uses the bubble sort algorithm to sort data
 * @author Viet Dinh
 * @param <E> generics
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E>  {
	/**
	 * Constructor for BubbleSorter with null
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * Constructor with comparator to use 
	 * @param comparator comparator to use
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	@Override
	public void sort(E[] list) {
//		   for i <-- 0 up to n-1 do
//			      min <-- i
//			      for j <-- i+1 up to n-1 do
//			         if A[j] < A[min] then
//			            min <-- j 
//			   if NOT i = min then
//			      x <-- A[i]
//			      A[i] <-- A[min]
//			      A[min] <-- x 
		int min = 0;
		E x;
		for(int i = 0; i < list.length; i++) {
			min = i;
			for(int j = i + 1; j < list.length; j++) {
				if(compare(list[j], list[min]) < 0) {
					min = j;
				}
			}
			if(i != min) {
				x = list[i];
				list[i] = list[min];
				list[min] = x;
			}
		}
	}

}

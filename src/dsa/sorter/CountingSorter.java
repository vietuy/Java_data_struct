package dsa.sorter;

import dsa.data.Identifiable;
import dsa.sorter.Sorter;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
//	   // Find the min and the max elements in the input data
//	   min <-- A[0]
//	   max <-- A[0]
//	   for i <-- 0 to n-1 do
//	      min <-- min(A[i], min)
//	      max <-- max(A[i], max)
//	   // Calculate the range of the elements
//	   k <-- (max-min+1)
//
//	   B <-- new array with length k
//	   for i <-- 0 to n-1 do
//	      B[ A[i]-min ] <-- B[ A[i]-min ] + 1
//	   
//	   for i <-- 1 to k-1 do
//	      B[i] <-- B[i-1] + B[i]
//
//	   F <-- new array with length n
//	   for i <-- 0 up to n-1 do
//	      F[ B[ A[i]-min ] - 1 ] <-- A[i]
//	      B[ A[i]-min ] <-- B[ A[i]-min ] - 1
//	   
//	   A <-- F
	@Override
	public void sort(E[] list) {
		int min = list[0].getId();
		int max = list[0].getId();
		for(int i = 0; i < list.length; i++) {
			min = Math.min(list[i].getId(), min) ;
			max = Math.max(list[i].getId(), max);
		}
		int k = max - min + 1;
		int[] data = new int[k];
		
		for(int i = 0; i < list.length; i++) {
			data[list[i].getId() - min] = data[list[i].getId() - min] + 1;
		}
		
		for(int i = 1; i < k; i++) {
			data[i] = data[i - 1] + data[i];
		}
		
		
		@SuppressWarnings("unchecked")
		E[] newa = (E[])(new Identifiable[list.length]);
		for(int i = 0; i < list.length; i++) {
		    newa[ data[list[i].getId() - min ] - 1 ] = list[i];
		    data[ list[i].getId() - min ] = data[list[i].getId() - min ] - 1;
		}
		for(int i = 0; i < list.length; i++) {
			list[i] = newa[i];
		}
	}
}

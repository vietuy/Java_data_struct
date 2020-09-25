package dsa.sorter;

import dsa.data.Identifiable;
import dsa.sorter.Sorter;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	@Override
	public void sort(E[] list) {
		int k = 0;
		for(int i = 0; i < list.length; i++) {
			k = Math.max(k, list[i].getId());
		}
		double x = Math.ceil(Math.log(k + 1 ) / Math.log(10));
		
		int p = 1;
		for(int j = 1; j <= x; j++) {
			int[] data = new int[10];
			for(int i = 0; i < list.length; i++) {
		         data[ (list[i].getId() / p) % 10 ] = data[ (list[i].getId() / p) % 10 ] + 1;
			}
			
			for(int i = 1; i <= 9; i++) {
				data[i] = data[i - 1] + data[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] newa = (E[])(new Identifiable[list.length]);
			for(int i = 0; i < list.length; i++) {
				 newa[ data[ (list[i].getId() / p) % 10 ] - 1 ] = list[i];
				 data[ (list[i].getId() / p) % 10 ] = data[ (list[i].getId() / p) % 10 ] - 1;
			}
			
			for(int i = 0; i < list.length; i++) {
				list[i] = newa[i];
			}
			
			p = p * 10;
		}
	}
	
	
}

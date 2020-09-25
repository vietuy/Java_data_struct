package dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter implementation
 * @author Viet Dinh
 *
 * @param <E> generic
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * MergerSorter constructor with given comparator
	 * @param comparator merge short comparator
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor without the given parameter
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] list) {
		if(list.length < 2) {
		    return ;
		}
		int mid = list.length / 2;
		E[] left =  Arrays.copyOfRange(list, 0, mid );
		E[] right = Arrays.copyOfRange(list, mid, list.length  );
		
		sort(left);
		sort(right);
		
		merge(left, right, list);
	}
	private void merge(E[] left, E[] right, E[] list) {
		int leftIndex = 0;
		int rightIndex = 0;
		while((leftIndex + rightIndex) < list.length) {
			if(rightIndex == right.length || ( leftIndex < left.length && compare(right[rightIndex], left[leftIndex]) > 0)) {
				list[leftIndex + rightIndex] = left[leftIndex];
				leftIndex = leftIndex + 1;
			} else {
			      list[leftIndex + rightIndex] = right[rightIndex];
			      rightIndex = rightIndex + 1;
			}
		}
	}

}
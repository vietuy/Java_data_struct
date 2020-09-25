package dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @param <E> generics
 */
public interface Sorter<E> {
	
	/**
	 * interface method for sorter
	 * @param list of integer
	 */
	void sort(E[] list) ;
}

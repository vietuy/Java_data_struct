package dsa.set;

import java.util.Iterator;

import dsa.map.Map;
import dsa.map.hashing.LinearProbingHashMap;

// Since our hash map uses linear probing, the entries are not ordered.
// As a result, we do not restrict our hash set to use Comparable elements.
// This also gives you an option if you need a set to manage elements
//     that are *NOT* Comparable (versus a TreeSet)
/**
 * HashSet implementation
 * @author Viet Dinh
 *
 * @param <E> generic elements
 */
public class HashSet<E> extends AbstractSet<E> {

    private Map<E, E> map;
    
    // This constructor will use our "production version" of our hash map
    // meaning random values for alpha and beta will be used
    /**
     * HashSet constructor
     */
    public HashSet() {
    	this(false);
        map = new LinearProbingHashMap<E, E>();
        
    }
    
    // If isTesting is true, this constructor will use our "development version" of our hash map
    // meaning alpha=1, beta=1, and prime=7 
    /**
     * HashSet constructor for testing
     * @param isTesting for testing
     */
    public HashSet(boolean isTesting) {
        map = new LinearProbingHashMap<E, E>(isTesting);
    }   
    
    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public void add(E value) {
        map.put(value, value);
    }

    @Override
    public boolean contains(E value) {
    	E temp = map.get(value);
    	if(temp == null) return false;
       return true;
    }

    @Override
    public E remove(E value) {
    	return map.remove(value);
    }
    
    @Override
    public int size() {
        return map.size();
    }
}

package dsa.tree;

import java.util.Iterator;

import dsa.map.Map;
import dsa.map.search_tree.RedBlackTreeMap;
import dsa.set.AbstractSet;

// Remember that search trees are ordered, so our elements must be Comparable
/**
 * TreeSet implementation
 * @author Viet Dinh
 *
 * @param <E> generic elements
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

    private Map<E, E> tree;
    
    /**
     * TreeSet constructor
     */
    public TreeSet() {
        tree = new RedBlackTreeMap<E, E>();
    }
    
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
       tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
    	E temp = tree.get(value);
    	if(temp == null) return false;
       return true;
    }

    @Override
    public E remove(E value) {
        return tree.remove(value);
    }

    @Override
    public int size() {
        return tree.size();
    }
}

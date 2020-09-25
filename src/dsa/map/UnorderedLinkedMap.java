package dsa.map;

import java.util.Iterator;

import dsa.Position;
import dsa.list.positional.PositionalLinkedList;
import dsa.list.positional.PositionalList;


/**
 * UnorderedLinkedMap that extends AbstractMap
 * @author Viet Dinh
 *
 * @param <K> key 
 * @param <V> value
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

	private PositionalList<Entry<K, V>> list;
	
	/**
	 * Initialize the PositionalList
	 */
	public UnorderedLinkedMap() {
		this.list = new PositionalLinkedList<Entry<K, V>>();
	}
	
	/**
	 * Find the position of the given key and return it
	 * @param key key to looked up
	 * @return the position of the given key
	 */
	private Position<Entry<K, V>> lookUp(K key)
	{
		Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
		while(it.hasNext()) {
			Position<Entry<K, V>> current = it.next();
			if(current.getElement().getKey() == key) {
				return current;
			}
		}
		return null;
	}

	@Override
	public V get(K key) {
		Position<Entry<K, V>> p = lookUp(key);
		if(p == null) {
			return null;
		}
		V temp = p.getElement().getValue();
		moveToFront(p);
		return temp ;
	}
	
	private void moveToFront(Position<Entry<K, V>> position) {
		list.addBefore(list.first(), position.getElement());
		list.remove(position);
	}

	@Override
	public V put(K key, V value) {
		Position<Entry<K, V>> p = lookUp(key);
	      if(p == null) {
	    	  @SuppressWarnings({ "rawtypes", "unchecked" })
			MapEntry<K, V> m = new MapEntry(key, value); 
	    	  list.addFirst(m);
	    	  return null;
	       }
		V temp = p.getElement().getValue();
		p.getElement().setValue(value);
		moveToFront(p);
		return temp;
	}
	
	@Override
	public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if(p == null) {
    	   return null;
       }
       V temp = p.getElement().getValue();
       list.remove(p);
       return temp;
	}
	
	@Override
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		PositionalList<Entry<K, V>> set = new PositionalLinkedList<Entry<K, V>>();
		for(Entry<K, V> m : list) {
			set.addLast(m);
		}
		return set;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		Iterator<Entry<K, V>> it = list.iterator();
		while(it.hasNext()) {
			sb.append(it.next().getKey());
			if(it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
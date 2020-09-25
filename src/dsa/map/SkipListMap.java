package dsa.map;

import java.util.Comparator;
import java.util.Random;

import dsa.list.ArrayBasedList;
import dsa.list.List;

/**
 * SkipListMap implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V> {
	
	private Random coinToss;
	private SkipListEntry<K, V> start;
	private int size;
	private int height;

	/**
	 * SkipListMap constructor without null comparator
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * SkipListMap constructor with given comparator
	 * @param compare comparator
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListEntry<K, V>(null, null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower		
		start.setNext(new SkipListEntry<K, V>(null, null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

	/**
	 * SkipListEntry that represents the quad-nodes in skip list
	 * @author Viet Dinh
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	private static class SkipListEntry<K, V> extends MapEntry<K, V> {

        private SkipListEntry<K, V> above;
        private SkipListEntry<K, V> below;
        private SkipListEntry<K, V> prev;
        private SkipListEntry<K, V> next;

        public SkipListEntry(K key, V value) {
            super(key, value);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        public SkipListEntry<K, V> getBelow() {
            return below;
        }
        
        public SkipListEntry<K, V> getNext() {
            return next;
        }
        
        public SkipListEntry<K, V> getPrevious() {
            return prev;
        }
        
        public SkipListEntry<K, V> getAbove() {
            return above;
        }
        
        public void setBelow(SkipListEntry<K, V> down) {
            this.below = down;
        }
        
        public void setNext(SkipListEntry<K, V> next) {
            this.next = next;
        }
        
        public void setPrevious(SkipListEntry<K, V> prev) {
            this.prev = prev;
        }
        public void setAbove(SkipListEntry<K, V> up) {
            this.above = up;
        }
    }
	
    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
	private boolean isSentinel(SkipListEntry<K, V> entry) {
		return entry.getKey() == null;
	}	

	private SkipListEntry<K, V> lookUp(K key) {
       SkipListEntry<K, V> current = start;
        while (current.getBelow() != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
	}

	@Override
	public V get(K key) {
		SkipListEntry<K, V> temp = lookUp(key);
//		if(isSentinel(temp)) return null;
		if(!isSentinel(temp) && compare(temp.getKey(), key) != 0) {
			return null; 
		}
		while(temp.above != null) {
			temp = temp.above;
		}
		return temp.getValue();
	}

	private SkipListEntry<K, V> insertAfterAbove(SkipListEntry<K, V> prev, SkipListEntry<K, V> down, K key, V value) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		SkipListEntry<K, V> newEntry = new SkipListEntry(key, value);
		newEntry.setBelow(down);
		newEntry.setPrevious(prev);
		if(prev != null ) {
			newEntry.setNext(prev.next);
			newEntry.prev.setNext(newEntry);
		}
		if(newEntry.next != null) newEntry.next.setPrevious(newEntry);
		if(down != null) down.setAbove(newEntry);
		return newEntry;
	}

	@Override
	public V put(K key, V value) {
		SkipListEntry<K, V> p = lookUp(key);
		if(!isSentinel(p) && compare(p.getKey(), key) == 0) {
			V originalValue = p.getValue();
			while(p != null) {
				p.setValue(value);
				p = p.above;
			}
			return originalValue;
		}
		SkipListEntry<K, V> q = null ;
		int currentLevel = 1;
		do {
			currentLevel += 1;
			if(currentLevel >= height ) {
				height += 1;
				SkipListEntry<K, V> tail = start.next;
				start = insertAfterAbove(null, start, null, null);
				insertAfterAbove(start, tail, null, null);
			}
			q = insertAfterAbove(p, q, key, value);
			while(p.above == null) {
				p = p.prev;
			}
			p = p.above;
		} while (coinToss.nextInt(100) + 1 <= 50  );
		size += 1;
		return null;
	}
	
    @Override
    public V remove(K key) {
        SkipListEntry<K, V> temp = lookUp(key);
        if(!isSentinel(temp)) {
        	SkipListEntry<K, V> pred = temp.getPrevious();
        	SkipListEntry<K, V> succ = temp.getNext();
        	pred.setNext(succ);
        	succ.setPrevious(pred);
        	temp.setPrevious(null);
        	temp.setNext(null);
        	size--;
        	while(temp.getAbove() != null) {
        		temp = temp.above;
            	pred = temp.getPrevious();
            	succ = temp.getNext();
            	pred.setNext(succ);
            	succ.setPrevious(pred);
            	temp.setPrevious(null);
            	temp.setNext(null);
            	
        	}
        	return temp.getValue();
        }
        return null;
    }	

	@Override
	public int size() {
		return size;
	}
	
   @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>();
        SkipListEntry<K, V> current = start;
        while(current.below != null){
            current = current.below;
        }
        current = current.next;
        while(!isSentinel(current)) {
            set.addLast(current);
            current = current.next;
        }
        return set;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[");
		SkipListEntry<K, V> cursor = start;
		while( cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while(cursor != null && cursor.getKey() != null) {
			sb.append(cursor.getKey());
			if(!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");
		
		return sb.toString();
	}

//	/**
//	 * This method return the full string of the skip list
//	 * @return full string of the skip list
//	 */
//	public String toFullString() {
//		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
//		SkipListEntry<K, V> cursor = start;
//		SkipListEntry<K, V> firstInList = start;
//		while( cursor != null) {
//			firstInList = cursor;
//			sb.append("-INF -> ");
//			cursor = cursor.next;
//			while(cursor != null && !isSentinel(cursor)) {
//				sb.append(cursor.getKey() + " -> ");
//				cursor = cursor.next;
//			}
//			sb.append("+INF\n");
//			cursor = firstInList.below;
//		}
//		sb.append("]");
//		return sb.toString();
//	}

}
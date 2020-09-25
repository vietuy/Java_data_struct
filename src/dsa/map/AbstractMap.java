package dsa.map;

import java.util.Iterator;

/**
 * Abstract Map 
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * Map entry
	 * @author Viet Dinh
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;

		/**
		 * Created a map entry
		 * @param key key
		 * @param value value
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 *Set the given key
		 *@param key key to be set
		 *@return the setted key
		 */
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}
	   /**
	    * Iterator of key
	 * @author Viet
	 *
	 */
	protected class KeyIterator implements Iterator<K> {

	        private Iterator<Entry<K, V>> it;
	        
	        /**
	         * Set the map iterator
	         * @param iterator map iterator
	         */
	        public KeyIterator(Iterator<Entry<K, V>> iterator) {
	            it = iterator;
	        }
	        
	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }

	        @Override
	        public K next() {
	        	Entry<K, V> temp = it.next();
	        	if(temp == null) return null;
	            return temp.getKey();
	        }
	        
	        @Override
	        public void remove() {
	            throw new UnsupportedOperationException("The remove operation is not supported yet.");
	        }
	   }
	
	/**
	 * Iterator for Value
	 * @author Viet Dinh
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> it;
        
        /**
         * ValueIterator constructor set with map iterator
         * @param iterator map iterator
         */
        public ValueIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public V next() {
            return it.next().getValue();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}
	
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}
	
	/**
	 * Value Iterable that return the value iterator 
	 * @author Viet Dinh
	 *
	 */
	private class ValueIterable implements Iterable<V> {
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator(entrySet().iterator());
		}	
	}
	
}
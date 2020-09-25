package dsa.map.hashing;

import dsa.list.ArrayBasedList;

/**
 * LinearProbingHashMap implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    // This time, our array is an array of TableEntry objects
    private TableEntry<K, V>[] table;
    private int size;

    /**
     * LinearProbingHashMap constructor
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * LinearProbingHashMap constructor for testing
     * @param isTesting for testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }
    
    /**
     * LinearProbingHashMap constructo with given capacity
     * @param capacity capacity
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }
    
    /**
     * LinearProbingHashMap constructor for with given capacity and isTesting boolean value
     * @param capacity capacity
     * @param isTesting for testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	ArrayBasedList<Entry<K, V>> i = new ArrayBasedList<Entry<K, V>>();
    	for(int j = 0; j < capacity(); j++) {
    		if(!isAvailable(j)) i.addLast(table[j]);
    	}
    	return i;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    // Helper method to determine whether a bucket has an entry or not  
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    /**
     * the code for this method is based on the remove method on page 427 in the course textbook
     * "Data Structures  Algorithms" by Goodrich, Tamassia, Goldwasser.
     */
    // Helper method to find the bucket for an entry;
    // If the entry *is* in the map, returns the index of the bucket
    // If the entry is *not* in the map, returns -(a + 1) to indicate 
    //     that the entry should be added at index a
    private int findBucket(int index, K key) {
    	int avail = -1;
    	int j = index;
    	do {
    		if(isAvailable(j)) {
    			if(avail == -1) avail = j;
    			if(table[j] == null) break;
    		} else if(table[j].getKey().equals(key))
    			return j;
    		j = (j + 1) % capacity();
    	} while(j != index);
    	return -(avail + 1);
    }
    
    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) return null;
        return table[index].getValue();
        
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if(index >= 0)
        	return table[index].setValue(value);
        table[-(index + 1)] = new TableEntry<>(key, value);
        size++;
        return null;
    }   

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if(index < 0) return null;
        V answer = table[index].getValue();
        table[index].setDeleted(true);
//        table[index] = null;
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
    /**
     * Table Entry of LinearProbingHashMap implementation
     * @author Viet Dinh
     *
     * @param <K> key
     * @param <V> valye
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

        private boolean isDeleted;

        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}

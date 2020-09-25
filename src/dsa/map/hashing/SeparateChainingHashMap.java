package dsa.map.hashing;

import dsa.list.List;
import dsa.list.SinglyLinkedList;
import dsa.map.Map;
import dsa.map.SkipListMap;

/**
 * SeparateChainingHashMap implementation
 * @author Viet Dinh
 *
 * @param <K> key
 * @param <V> value
 */
public class SeparateChainingHashMap<K extends Comparable<K>, V> extends AbstractHashMap<K, V> {

    /** map's table   */
    private Map<K, V>[] table;
    private int size;
    
    /**
     * SeparateChainingHashMap constructor
     */
    public SeparateChainingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }
    
    /**
     * SeparateChainingHashMap constructor for testing
     * @param isTesting used for testing
     */
    public SeparateChainingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }  	
	
    /**
     * SeparateChainingHashMap constructor with given capacity
     * @param capacity capacity of map
     */
    public SeparateChainingHashMap(int capacity) {
        this(capacity, false);
    }    
    
    /**
     * used for testing constructor with given capacity and isTesting
     * @param capacity capacity
     * @param isTesting used for testing
     */
    public SeparateChainingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> list = new SinglyLinkedList<Entry<K, V>>();
        for(int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                for(Entry<K, V> entry : table[i].entrySet()) {
                    list.addLast(entry);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        // Example -- change this to whatever map you'd like        
        table = new SkipListMap[capacity];
        size = 0;
    }

    @Override
    public V bucketGet(int hash, K key) {
        // Get the bucket at the specified index in the hash table      
        Map<K, V> bucket = table[hash];
        // If there is no map in the bucket, then the entry does not exist      
        if(bucket == null) {
            return null;
        }
        // Otherwise, delegate to the existing map's get method to return the value     
        return bucket.get(key);
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        Map<K, V> bucket = table[hash]; 
        if(bucket == null) {
        	table[hash] = new SkipListMap<K, V>();
        	bucket = table[hash];
        }
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        size += bucket.size() - oldSize;
        return answer;
    }

    @Override
    public V bucketRemove(int hash, K key) {
        Map<K, V> bucket = table[hash];
       if(bucket == null) return null;
       int oldSize = bucket.size();
       V answer = bucket.remove(key);
       size -= oldSize - bucket.size();
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
}

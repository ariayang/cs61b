import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V> {

    /** Buckets size: size */
    /** Each bucket has an ArrayList */
    private int size;
    private double loadF = 0.75;
    private int numItems;
    private LinkedList<V>[] buckets;

    //(List<T>[]) new ArrayList[size]

    public MyHashMap() {
        size = 16;
        loadF = 0.75;
        numItems = 0;
        buckets = (LinkedList<V>[]) new LinkedList[size];
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        loadF = 0.75;
        numItems = 0;
        buckets = (LinkedList<V>[]) new LinkedList[size];
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        loadF = loadFactor;
        numItems = 0;
        buckets = (LinkedList<V>[]) new LinkedList[size];
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear();

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public containsKey(K key);

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key);

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return numItems;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value);

    @Override
    public remove() throws new UnsupportedOperationException;

    @Override
    public keySet() {}

    /** Returns an iterator iterates over stored keys, any order */
    @Override
    public iterator() {}


}

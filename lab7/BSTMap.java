import com.sun.source.tree.Tree;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** tree root */
    private TreeNode root;

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root.size = 0;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) { throw new IllegalArgumentException(); }
        return (get(key) != null);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(TreeNode x, K key) {
        if (key == null) { throw new IllegalArgumentException(); }
        if (x == null) { return null; }
        int cmp = key.compareTo((K)x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return (V)x.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(TreeNode x) {
        if (x == null) { return 0;
        } else { return x.size; }
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            //delete key; return;
        }
        root = put(key, val, root);
    }

    /** Create a new node with key, val. Insert comparable to n/this */
    private TreeNode put(K key, V val, TreeNode n) {
        if (n == null) { return new TreeNode(key, val, 1); }
        int cmp = key.compareTo((K)n.key);
        if (cmp < 0) {
            n.left = put(key, val, n.left);
        } else if (cmp > 0) {
            n.right = put(key, val, n.right);
        } else { n.value = val; }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() throws UnsupportedOperationException {
        return null;
    }

    @Override
    public V remove(K key) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public V remove(K key, V value) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Iterator<K> iterator() throws UnsupportedOperationException {
        return null;
    }

    /***/
    public void printInOrder() {

    }

    private class TreeNode<K,V> {
        private K key;
        private V value;
        private TreeNode parent, left, right;
        private int size; //number of nodes in subtree;

        public TreeNode(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }
    }

}

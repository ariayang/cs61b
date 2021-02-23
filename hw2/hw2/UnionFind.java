package hw2;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        // for every node (v1, array index), its value: its parent's index
        // or - size if the root. -1 if an isolated set.
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid vertex. */
    private void validate(int v1) {
        // Written
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException("invalid vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -1 * parent[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // Written
        validate(v1);
        validate(v2);
        return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int v1Size = sizeOf(v1);   //size of the set v1 belons
        int v1Root = find(v1);  //return the root index
        int v2Root = find(v2);
        int v2Size = sizeOf(v2);
        int finalsize = v1Size + v2Size;
        if (v1 == v2 || v1Root == v2Root) {
            return;
        } else if (v1Size <= v2Size) {
            parent[v1Root] = v2Root;
            parent[v2Root] = finalsize * (-1);
        } else {
            parent[v2Root] = v1Root;
            parent[v1Root] = finalsize * (-1);
        }
    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    // return itself if it's isolated
    public int find(int v1) {
        validate(v1);
        if (parent[v1] == -1) {
            return v1;
        } else {
            // TO OPTIMIZE
            while (parent[v1] > 0) {
                v1 = parent[v1];
            }
            return v1;
        }
    }

}

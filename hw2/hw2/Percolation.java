package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class Percolation {
    /** create N-by-N grid, with all sites initially blocked */
    private int gridN;  // length or width of the grid

    /** Status of the sites: Open 1, blocked 0 */
    private int[] gridContents;

    /** Record number of open sites. */
    private int numOpenSites;

    /** Implementation using Weighted Quick Union. */
    private WeightedQuickUnionUF wquf;

    /** Status of the bottom row: Percolated 1, Not yet 0 */
    //int perc;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Grid size cannot be negative");
        }
        gridN = N;
        wquf = new WeightedQuickUnionUF(N * N + 2);
        gridContents = new int[N * N];
        numOpenSites = 0;
        //perc = 0;
        //wquf [N * N]: Top virtual site
        //wquf [N * N +1]: Bottom virtual site
        for (int i = 0; i < gridN; i++) {
            wquf.union(i, N * N);
            wquf.union(N * N + 1, N * N - (i + 1));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = gridConvert(row, col);
        if (isOpen(row, col)) { return;
        } else {
            gridContents[index] = 1;
            numOpenSites++;
        }

        // Check if 4 neighboring sites can be connected if open
        // No need to implement fill. in isFull instead.

        if (row >= 1) {
            if (isOpen(row - 1, col)) {
                int index2 = gridConvert(row - 1, col);
                wquf.union(index, index2);
            }
        }

        if (col >= 1) {
            if (isOpen(row, col - 1)) {
                int index2 = gridConvert(row, col - 1);
                wquf.union(index, index2);
            }
        }

        if (col < gridN - 1) {
            if (isOpen(row, col + 1)) {
                int index2 = gridConvert(row, col + 1);
                wquf.union(index, index2);
            }
        }

        if (row < gridN - 1) {
            if (isOpen(row + 1, col)) {
                int index2 = gridConvert(row + 1, col);
                wquf.union(index, index2);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = gridConvert(row, col);
        return (gridContents[index] != 0);
    }

    // is the site (row, col) full?
    /** To check if it's connected to the top row */
    public boolean isFull(int row, int col) {
        int index = gridConvert(row, col);
        return (wquf.connected(index, gridN * gridN) && isOpen(row, col));
    }


    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // if any bottom cell is connected with top row cell
        return (wquf.connected(gridN * gridN - 1, gridN * gridN));
    }

    // use for unit testing (not required, but keep this here for the autograder)
    @Test
    public static void main(String[] args) {
        Percolation myPerc = new Percolation(5);
        assertEquals(true, myPerc.isNeighbor(0, 4, 1, 0));
        //assertEquals(false, myPerc.isNeighbor(1, 0));
    }

    /** Helper: if two locations are neighbors */
    private boolean isNeighbor(int row1, int col1, int row2, int col2) {
        //Not the side locations
        isValid(row1, col1);
        isValid(row2, col2);
        int index1 = gridConvert(row1, col1);
        int index2 = gridConvert(row2, col2);
        if (Math.abs(index1 - index2) == 1) {
            //check if it's side case
            if (index1 % gridN == 0 && index2 < index1) {
                return false;
            } else {
                return !(index2 % gridN == 0 && index1 < index2);
            }
        } else {
            return false;
        }
    }

    /** Helper: convert row and col into uf (union find parent[]) index */
    private int gridConvert(int row, int col) {
        isValid(row, col);
        int index = row * gridN + col;
        return index; }

    /** Helper: check if row, col is valid */
    private boolean isValid(int row, int col) {
        if (row >= 0 && row < gridN) {
            if (col >= 0 && col < gridN) {
                return true;
            }
        } else {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return false;
    }

}

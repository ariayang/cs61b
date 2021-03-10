package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board implements WorldState {

    private int[][] tiles;
    private int BLANK = 0;

    /** Constructs a board from an N-by-N array of tiles
     * where tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        this.tiles = tiles.clone();
    }


    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (tiles[i][j] == BLANK) { return 0; }
        return tiles[i][j];
    }


    /** Returns the board size N */
    public int size() {
        return tiles.length;
    }


    /**
     * Returns neighbors of this board.
     * Source: Josh Hug, CS61B course
     * */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int sizeN = size();
        int emptyRow = -1;
        int emptyCol = -1;

        //Locate the empty tile
        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                if (tileAt(i, j) == BLANK) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }

        // Make a new copy of the original tiles/board
        int[][] temp = new int[sizeN][sizeN];
        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                temp[i][j] = tileAt(i, j);
            }
        }


        for (int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                if (Math.abs(-emptyRow + i) + Math.abs(j - emptyCol) - 1 == 0) {
                    temp[emptyRow][emptyCol] = temp[i][j];
                    temp[i][j] = BLANK;
                    Board neighbor = new Board(temp);
                    neighbors.enqueue(neighbor);
                    temp[i][j] = temp[emptyRow][emptyCol];
                    temp[emptyRow][emptyCol] = BLANK;
                }
            }
        }

        return neighbors;
    }



    /** Return the number of tiles in the wrong position */
    public int hamming() {
        int wrong = 0;
        int sizeN = size();
        for(int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                if (tileAt(i, j) == BLANK) { continue; }
                if (tileAt(i, j) != (i * sizeN + j + 1)) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    /** The sum of the Manhattan distances
     * Sum of the vertical and horizontal distance
     * from the tiles to their goal positions */
    public int manhattan() {
        int sizeN = size();
        int wrong = 0;
        // i * sizeN + j + 1: should be the right number.
        for(int i = 0; i < sizeN; i++) {
            for (int j = 0; j < sizeN; j++) {
                if (tileAt(i, j) == BLANK) { continue; }
                if (tileAt(i, j) != (i * sizeN + j + 1)) {
                    int correctJ = (tileAt(i, j) - 1) % sizeN;
                    int correctI = (tileAt(i, j) - correctJ - 1) / sizeN;
                    wrong = wrong + Math.abs(i - correctI) + Math.abs(j - correctJ);
                }
            }
        }
        return wrong;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

   @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

   /** Returns true if this board's tile values are the same position as y's */
    public boolean equals(Object y) {
        if (y instanceof Board) {
            Board otherBoard = (Board) y;

            if (otherBoard.size() != this.size()) {
                return false;
            }

            int sizeN = otherBoard.size();
            for (int i = 0; i < sizeN; i++) {
                for (int j = 0; j < sizeN; j++) {
                    if (otherBoard.tileAt(i, j) != this.tileAt(i, j)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /** Provided by the Skeleton */
    /** Returns the string representation of the board. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

//    public static void main(String[] args) {
//        int[][] grid = { { 5, 8, 7}, {1, 4, 6}, {3, 0, 2} };
//        Board board = new Board(grid);
//        board.manhattan();
//    }
}

package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private ArrayDeque fringe;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe = new ArrayDeque();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        fringe.add(v);

        while (fringe.peek() != null) {
            int z = (int)fringe.poll();
            if (z == t) {
                targetFound = true;
                marked[z] = true;
                announce();
                break; }
            if (!marked[z]) {
                marked[z] = true;
                announce();

                for (int w : maze.adj(z)) {
                    if(!marked[w]) {
                        fringe.add(w);
                        edgeTo[w] = z;
                        distTo[w] = distTo[z] + 1;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}


package lab11.graphs;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze m;
    private boolean cycleFound;
    private int[] edgeToNotMarked;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        // Where to start the dfs? int 0
        int start = 0;
        distTo[start] = 0;
        edgeToNotMarked = new int[maze.V()];
        ArrayDeque fringe = new ArrayDeque();
        fringe.add(start);

        while (fringe.peek() != null) {
            int z = (int)fringe.poll();
            /*if (z == t) {
                cycleFound = true;
                marked[z] = true;
                announce();
                break; }*/
            if (!marked[z]) {
                marked[z] = true;
                announce();

                for (int w : maze.adj(z)) {
                    if(!marked[w]) {
                        fringe.add(w);
                        edgeToNotMarked[w] = z;
                        distTo[w] = distTo[z] + 1;
                    } else {
                        if (edgeToNotMarked[z] != w) {
                            cycleFound = true;
                            edgeTo[z] = w;
                            announce();
                            drawCycle(w, z);
                            break;
                        }
                    }
                    if (cycleFound == true) { break; }
                }
            }
        }

    }

    // Helper methods go here
    // Not working yet. NOT SURE how to draw a cycle. How to decide the parent?
    private void drawCycle(int w, int z) {
        while(w != z) {
            edgeTo[w] = edgeToNotMarked[w];
            edgeTo[z] = edgeToNotMarked[z];
             w = edgeTo[w];
            announce();
             z = edgeTo[z];
            announce();
        }
    }
}


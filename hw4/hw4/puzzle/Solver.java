package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private int moves;
    private SearchNode goal;

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists.
     */

    public Solver(WorldState initial) {

        SearchNode ini = new SearchNode();
        ini.state = initial;
        ini.moves = 0;
        ini.prev = null;
        HashSet<WorldState> NodesHash = new HashSet();
        NodesHash.add(initial);

        SearchNode goal = new SearchNode();

        //PriorityQ's key is: NodesHash.get(SearchNode ini);
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNode.SearchNodeComparator());
        pq.insert(ini);

        while(!pq.isEmpty()) {
            SearchNode x = pq.delMin();
            NodesHash.add(x.state);
            if (x.state.isGoal()) {
                this.goal = x;
                this.moves = x.moves;
                break;
            }
            for (WorldState y: x.state.neighbors()) {
                if (!NodesHash.contains(y)) {
                    SearchNode yNode = new SearchNode();
                    yNode.state = y;
                    yNode.moves = x.moves + 1;
                    yNode.prev = x;
                    pq.insert(yNode);
                }
            }
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.
     */
    public int moves() {
        return this.moves;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
    to the solution.
     */
    public Iterable<WorldState> solution() {
        LinkedList<WorldState> sol = new LinkedList();
        while(goal != null) {
            sol.addFirst(goal.state);
            goal = goal.prev;
        }
        return sol;
    }


}

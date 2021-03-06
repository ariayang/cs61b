package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private double timeSpent;
    private ArrayHeapMinPQ pq;
    //private DoubleMapPQ pq;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    int deq;
    double longest = Double.POSITIVE_INFINITY;
    //double shortDist;

    /** Constructor which finds the solution, computing everything necessary
     * for all other methods to return their results in constant time.
     * Note that the timeout passed in is in seconds. */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        pq = new ArrayHeapMinPQ();
        //pq = new DoubleMapPQ();
        double p;  // priority of vertex v, sum of v's distance from the start + estimate v to goal
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        solution = new LinkedList<>();
        solutionWeight = 0;

        //PQ for all start's neighbor, from start, depth = 1
        List<WeightedEdge<Vertex>> vNeighbors = input.neighbors(start);  //All neighbors of start
        for (WeightedEdge edge: vNeighbors) {
            Vertex v = (Vertex) edge.to();
            p = edge.weight();
            distTo.put(v, p);
            double estToGoal = input.estimatedDistanceToGoal(v, end);
            p = p + estToGoal;
            pq.add(v, p);
        }


        //Insert source vertex
        pq.add(start, longest);
        distTo.put(start, longest);
        //solution.add(start);
        edgeTo.put(start, start);

        // Repeat until PQ is empty
        //Remove best vertex v from PQ, relax all edges pointing from v
        while(pq.size() != 0) {
            Vertex closest = (Vertex) pq.removeSmallest();
            //if(!solution.contains(closest)) {solution.add(closest); } //how to remove cycled route?
            deq++;
            if (closest.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distTo.get(end);
                break;
            }

            List<WeightedEdge<Vertex>> sNeighbors = input.neighbors(closest);
            for(WeightedEdge e: sNeighbors) {
                relax(input, e, start, end);  // update pq

                timeSpent = sw.elapsedTime();
                if (timeSpent > timeout) {
                    outcome = SolverOutcome.TIMEOUT;
                    solution = new LinkedList<>();
                    break;
                }
            }

        }

        if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            solution = new LinkedList<>();
        }

        /** Build solution */
        Vertex pointer = end;
        while((pointer!=null) && !pointer.equals(start)) {
            solution.addFirst(pointer);
            pointer = edgeTo.get(pointer);
        }
        solution.addFirst(start);

        return;
    }


    private void relax(AStarGraph<Vertex> input, WeightedEdge e, Vertex start, Vertex end) {
        Vertex p = (Vertex) e.from();
        Vertex q = (Vertex) e.to();
        double w = e.weight();
        if (!distTo.containsKey(q)) { distTo.put(q, longest);}

        if ((distTo.get(p) + w) < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            if (pq.contains(q)) {
                pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            } else {
                pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }
    /** Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.*/
    @Override
    public SolverOutcome outcome() { return outcome; }


    /** A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public List<Vertex> solution() { return solution; }


    /** The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public double solutionWeight() {return solutionWeight;}


    /** The total number of priority queue dequeue operations.*/
    @Override
    public int numStatesExplored() {return deq;}

    /**  The total time spent in seconds by the constructor. */
    @Override
    public double explorationTime() {return timeSpent;}

}

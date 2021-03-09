package hw4.puzzle;

import java.util.Comparator;

public class SearchNode {

    WorldState state;
    int moves;
    SearchNode prev;

    public static class SearchNodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Priority = o1.state.estimatedDistanceToGoal() + o1.moves;
            int o2Priority = o2.state.estimatedDistanceToGoal() + o2.moves;

            if (o1Priority < o2Priority) {
                return -1;
            } else if (o1Priority == o2Priority) {
                return 0;
            } else {
                return 1;
            }
        }
    }

}


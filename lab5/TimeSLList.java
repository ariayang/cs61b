import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        SLList<Integer> items = new SLList();

        //Instantiate SLList object with N items.
        int M = 10000;

        //Ns stores testCases number of test cases
        ArrayList Ns = new ArrayList();
        Ns.add(1000);
        int testCases = 8;

        for (int i = 1; i < testCases; i++) {
            Ns.add((int)(Ns.get(i - 1)) * 2);
        }

        ArrayList<Double> times = new ArrayList(testCases);
        ArrayList <Integer> opCounts = new ArrayList(testCases);

        for (int j = 0; j < testCases; j++) {
            opCounts.add(0);
            for (int n = 0; n < (int)Ns.get(j); n++) {
                items.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < M; i++) {
                items.getLast();
                opCounts.set(j, opCounts.get(j)+1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }

}

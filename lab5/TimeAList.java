import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList list1 = new AList();

        //Ns stores the 8 test cases
        ArrayList Ns = new ArrayList();
        Ns.add(1000);
        int testCases = 16;
        for (int i = 1; i < testCases; i++) {
            Ns.add((int)(Ns.get(i - 1)) * 2);
        }

        ArrayList<Double> times = new ArrayList(testCases);
        ArrayList <Integer> opCounts = new ArrayList(testCases);

        for (int j = 0; j < testCases; j++) {
            opCounts.add(0);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < (int)Ns.get(j); i++) {
                list1.addLast(1);
                opCounts.set(j, opCounts.get(j)+1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }
}

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class TestUnionFind {

    int SIZE = 10;
    public UnionFind uf = new UnionFind(SIZE);

    @Test
    public void TestUnionFind() {
        //System.out.print(uf.parent.toString());
        assertEquals(false, uf.isConnected(1, 2));

        assertEquals(-1, uf.parent(1));
        assertEquals(1, uf.find(1));
        uf.connect(1,2);
        assertEquals(true, uf.isConnected(1, 2));
        uf.connect(2,3);
        assertEquals(true, uf.isConnected(1, 3));
        uf.connect(3,7);
        assertEquals(true, uf.isConnected(1, 7));
        uf.connect(2,8);
        assertEquals(true, uf.isConnected(7, 8));
        assertEquals(5, uf.sizeOf(7));
        assertEquals(2, uf.parent(7));
        assertEquals(5, uf.sizeOf(2));
    }

    @Test
    public void TimingTestUnionFind() {
        //Size of the unionFind: mysize
        int mysize = 100;
        UnionFind list1 = new UnionFind(mysize);

        //Ns stores the 8 test cases
        ArrayList Ns = new ArrayList();
        Ns.add(1000);
        int testCases = 7;
        for (int i = 1; i < testCases; i++) {
            Ns.add((int)(Ns.get(i - 1)) * 2);
        }

        ArrayList<Double> times = new ArrayList(testCases);
        ArrayList <Integer> opCounts = new ArrayList(testCases);

        for (int j = 0; j < testCases; j++) {
            opCounts.add(0);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < (int)Ns.get(j); i++) {
                double double_random = StdRandom.uniform(0, mysize - 1);
                int int_random = (int) double_random;
                double double_random2 = StdRandom.uniform(0, mysize - 1);
                int int_random2 = (int) double_random2;
                list1.connect(int_random, int_random2);
                //TO TEST: FIND timing
                opCounts.set(j, opCounts.get(j)+1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
    }


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
}

package hw2;

import edu.princeton.cs.introcs.StdRandom;
//import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private Percolation mypf;
    private int gridN;
    private int testTimes;
    private double[] percOpenSites;

    /** Perform T independent experiments on an N by N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Grid size cannot be negative");
        }
        gridN = N;
        testTimes = T;
        percOpenSites = new double[T];

        // TO perform T times
        for (int i = 0; i < T; i++) {
            mypf = pf.make(N);
            while (!mypf.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                mypf.open(row, col);
            }
            percOpenSites[i] = (double) mypf.numberOfOpenSites() / (double) (N * N);
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        double mysum = 0.0;
        for (int i = 0; i < testTimes; i++) {
            mysum = percOpenSites[i] + mysum;
        }
        return (double) mysum / testTimes;
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        double mysum = 0.0;
        double mymean = mean();
        for (int i = 0; i < testTimes; i++) {
            mysum = (percOpenSites[i] - mymean) * (percOpenSites[i] - mymean);
        }
        return (double) mysum / (testTimes - 1);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        double mystddev = stddev();
        double mymean = mean();
        return mymean - 1.96 * mystddev / Math.sqrt(testTimes);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        double mystddev = stddev();
        double mymean = mean();
        return mymean + 1.96 * mystddev / Math.sqrt(testTimes);
    }
}

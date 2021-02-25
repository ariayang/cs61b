package bearmaps;

import edu.princeton.cs.introcs.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.introcs.StdRandom;


public class KDTreeTest {

    @Test
    public void KdTreeTestGenerate() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kd = new KDTree(List.of(p1, p2, p3));
        Point ret = kd.nearest(3.0, 4.0);
        //System.out.println(ret.toString());

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point retnn = nn.nearest(3.0, 4.0);

        assertEquals(ret, retnn);

        /** Starting a random generated numbers list , a few thousand for list */
        int listItems = 2000;
        int numberRange = 1000;
        ArrayList testPoints = new ArrayList();
        for (int i = 0; i < listItems; i++) {
            double double_randomX = StdRandom.uniform(-numberRange, numberRange);
            double double_randomY = StdRandom.uniform(-numberRange, numberRange);
            Point p = new Point(double_randomX, double_randomY);
            testPoints.add(p);
        }

        KDTree kd2 = new KDTree(testPoints);
        NaivePointSet nn2 = new NaivePointSet(testPoints);

        /** Starting a random generated numbers list , a few hundred for nearest */
        int testTimes = 200;
        for (int i = 0; i < testTimes; i++) {
            double double_randomX = StdRandom.uniform(-numberRange, numberRange);
            double double_randomY = StdRandom.uniform(-numberRange, numberRange);
            Point kdNear = kd2.nearest(double_randomX, double_randomY);
            Point nnNear = nn2.nearest(double_randomX, double_randomY);
            assertEquals(kdNear, nnNear);
        }
    }

    @Test
    public void KdTreeTestTiming() {
        int listItems = 2000;
        int numberRange = 1000;
        ArrayList testPoints = new ArrayList();
        for (int i = 0; i < listItems; i++) {
            double double_randomX = StdRandom.uniform(-numberRange, numberRange);
            double double_randomY = StdRandom.uniform(-numberRange, numberRange);
            Point p = new Point(double_randomX, double_randomY);
            testPoints.add(p);
        }

        KDTree kd2 = new KDTree(testPoints);
        NaivePointSet nn2 = new NaivePointSet(testPoints);

        int testTimes = 1000000;
        Stopwatch kdsw = new Stopwatch();
        for (int i = 0; i < testTimes; i++) {
            double double_randomX = StdRandom.uniform(-numberRange, numberRange);
            double double_randomY = StdRandom.uniform(-numberRange, numberRange);
            Point kdNear = kd2.nearest(double_randomX, double_randomY);
        }
        double timeInSeconds = kdsw.elapsedTime();
        System.out.println(("KD Tree's running time is " + timeInSeconds));

        Stopwatch nnsw = new Stopwatch();
        for (int i = 0; i < testTimes; i++) {
            double double_randomX = StdRandom.uniform(-numberRange, numberRange);
            double double_randomY = StdRandom.uniform(-numberRange, numberRange);
            Point nnNear = nn2.nearest(double_randomX, double_randomY);
        }
        double timeInSeconds2 = nnsw.elapsedTime();
        System.out.println(("Naive Tree's running time is " + timeInSeconds2));
    }

}

package bearmaps;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    public static void main(String[] args) {
        ArrayHeapMinPQ testHeap = new ArrayHeapMinPQ();
        testHeap.add(5.01, 5.5);
        testHeap.add(7.01, 7.7);
        testHeap.add(1.01, 1.1);
        testHeap.add(4.01, 4.4);
        testHeap.add(6.01, 6.6);
        testHeap.add(3.01, 3.3);
        testHeap.add(8.01, 8.8);
        testHeap.add(9.01, 9.9);
        testHeap.add(2.01, 2.2);
        //testHeap.add(2, 10); //Expect error, but failed.


        /* build iterator to print */
/*
        ArrayList printArr = new ArrayList();
        Iterator itemIter = testHeap.items.iterator();
        while (itemIter.hasNext()) {
            printArr.add(((ArrayHeapMinPQ.itemNode)itemIter.next()).getItem());
        }
        //in oder for print to work, I temp made itemNode public.
        //TODO: need to change it back to private in the end.
        //PrintHeapDemo.printSimpleHeapDrawing(printArr.toArray());
        PrintHeapDemo.printFancyHeapDrawing(printArr.toArray());

        assertEquals(1.01, testHeap.getSmallest());
        assertEquals(9, testHeap.size());
        assertEquals(true, testHeap.contains(9.01));
        assertEquals(false, testHeap.contains(10));
        assertEquals(1.01, testHeap.removeSmallest());
        assertEquals(8, testHeap.size());


        assertEquals(2.01, testHeap.getSmallest());
        testHeap.changePriority(2.01, 11);


        //assertEquals(11, testHeap.items.get(getPriority);
        /* Print block
        ArrayList printArr2 = new ArrayList();
        Iterator itemIter2 = testHeap.items.iterator();
        while (itemIter2.hasNext()) {
            printArr2.add(((ArrayHeapMinPQ.itemNode)itemIter2.next()).getItem());
        }
        //PrintHeapDemo.printSimpleHeapDrawing(printArr2.toArray());
        PrintHeapDemo.printFancyHeapDrawing(printArr2.toArray());
        //

        assertEquals(3, testHeap.getSmallest());
        */

    }

    @Test
    public void testNaiveMinPQ() {
        NaiveMinPQ naive = new NaiveMinPQ();
        naive.add(10, 1);
        naive.add(9, 2);
        naive.add( 8, 3);
        //naive.add(10, 2); Will not throw exception, but will not add
        naive.add(7, 4);
        naive.add(6, 5);
        naive.add(5, 0.5);
        System.out.println("NaivePQ's smallest is " + naive.getSmallest());

        //ArrayList printArr2 = new ArrayList();
        //Iterator itemIter2 = naive.items.iterator();
        //while (itemIter2.hasNext()) {
         //   printArr2.add(((NaiveMinPQ.PriorityNode)itemIter2.next()).getItem());
       // }
        //PrintHeapDemo.printSimpleHeapDrawing(printArr2.toArray());
    }

    @Test
    public void randomTest() {
        int listItems = 5000;
        double numberRange = 1000;
        ArrayHeapMinPQ mPQ = new ArrayHeapMinPQ();
        NaiveMinPQ nPQ = new NaiveMinPQ();


        for (int i = 0; i < listItems; i++) {

            double double_randomItem = StdRandom.uniform(-numberRange, numberRange);
            double double_randomPriority = StdRandom.uniform(-numberRange, numberRange);
            if (!mPQ.contains(double_randomItem)) {
                mPQ.add(double_randomItem, double_randomPriority);
                nPQ.add(double_randomItem, double_randomPriority);
                assertEquals(mPQ.getSmallest(), nPQ.getSmallest());  //Add is always working
            }
        }


        //assertEquals(mPQ.size(), nPQ.size()); //is right

        //Removal n Times, testing
        for (int i = 0; i < mPQ.size(); i++) {
           assertEquals(mPQ.removeSmallest(), nPQ.removeSmallest());
        }

        //Timing test
        /*
        listItems = 100000;
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < listItems; i++) {
            double double_randomItem = StdRandom.uniform(-numberRange, numberRange);
            double double_randomPriority = StdRandom.uniform(-numberRange, numberRange);
            if (!mPQ.contains(double_randomItem)) {
                mPQ.add(double_randomItem, double_randomPriority);
                //nPQ.add(double_randomItem, double_randomPriority);
            }
        }

        System.out.println("ArrayHeapPQ's adding time is " + sw.elapsedTime());

        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < listItems; i++) {

            double double_randomItem = StdRandom.uniform(-numberRange, numberRange);
            double double_randomPriority = StdRandom.uniform(-numberRange, numberRange);
            if (!nPQ.contains(double_randomItem)) {
                nPQ.add(double_randomItem, double_randomPriority);
            }
        }
        System.out.println("NaivePQ's adding time is " + sw2.elapsedTime());

        Stopwatch sw3 = new Stopwatch();
        for (int i = 0; i < mPQ.size(); i++) {
           mPQ.removeSmallest();
        }
        System.out.println("ArrayHeapPQ's removal time is " + sw3.elapsedTime());
        System.out.println("ArrayHeapPQ's size is " + mPQ.size());

        Stopwatch sw4 = new Stopwatch();
        for (int i = 0; i < nPQ.size(); i++) {
            nPQ.removeSmallest();
        }
        System.out.println("NaivePQ's removal time is " + sw4.elapsedTime());
        System.out.println("NaivePQ's size is " + nPQ.size());
        */


    }



}


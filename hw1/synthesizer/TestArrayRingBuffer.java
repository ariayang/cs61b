package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertEquals(4, arb.capacity());
        assertEquals(0, arb.fillCount());
        try {
            arb.enqueue(33.1);
            assertEquals(1, arb.fillCount()); // 33.1 null null null ...
            arb.enqueue(44.8);
            assertEquals(2, arb.fillCount()); //33.1 44.8 null null ...
            arb.enqueue(62.3);
            //assertArrayEquals([33.1, 44.8, 62.3, null], );
            arb.enqueue(-3.4);
            arb.enqueue(0.6);
        } catch (Exception e) {
            System.out.println(e);
        }
        /* assertArrayEquals([33.1, 44.8, 62.3, -3.4], arb.enqueue(-3.4);
        //Insert another one, throw exception expected */
        assertEquals(33.1, arb.dequeue());
        assertEquals(44.8, arb.peek());
        Iterator ami = arb.iterator();
        while (ami.hasNext()) {
            System.out.println(ami.next());
        }

        try {
            arb.dequeue();
            arb.dequeue();
            arb.dequeue();
            arb.dequeue();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            arb.peek();
        } catch (Exception e) {
            System.out.println(e);
        }

        arb.enqueue(3);
        ArrayRingBuffer arb2 = new ArrayRingBuffer(4);
        arb2.enqueue(3);
        assertEquals(true, arb.equals(arb2));
        arb2.enqueue(4);
        assertEquals(false, arb.equals(arb2));
    }
}

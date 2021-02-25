package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class TestNaivePointSet {
    @Test
    public void TestNaivePointSet() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0);
        //ret.getX();
        //ret.getY();
        System.out.println(ret.toString());
        assertEquals(p2, ret);
    }
}

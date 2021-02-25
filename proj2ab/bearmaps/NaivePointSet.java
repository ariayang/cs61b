package bearmaps;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;


public class NaivePointSet implements PointSet {
    int size;
    HashSet<Point> mySet;

    public NaivePointSet(List<Point> points) {
        size = points.size();
        mySet = new HashSet<>();
        for (Point point: points) {
            mySet.add(point);
        }
    }

    public Point nearest(double x, double y) {


        Point target = new Point(x, y);
        Iterator pIter = mySet.iterator();
        Point nearP = (Point) pIter.next();
        double nearestD = Point.distance(nearP, target);
        //for (Point pIter: mySet) {
        while (pIter.hasNext()) {
            Point p = (Point) pIter.next();
            // Point p = pIter;
            double dis = Point.distance(p, target);
            if (dis < nearestD) {
                nearestD = dis;
                nearP = p;
            }
        }
        return nearP;
    }

}

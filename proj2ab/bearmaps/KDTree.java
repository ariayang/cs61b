package bearmaps;
import javax.swing.tree.TreeNode;
import java.util.List;

public class KDTree {
    KdNode root;


    /** Can assume points has at least size 1 */
    public KDTree(List<Point> points) {
        root = new KdNode(points.get(0)); // Root is the first node
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            insert(p);
        }
    }

    private void insert(Point p) {
        root = insertHelper(p, root, root.depth);
    }

    /** Helper for insert() */
    private KdNode insertHelper(Point p, KdNode root, int dep) {
        dep = dep;
        if (root == null) { return new KdNode(p, dep); }
        // Even depth layer (including 0/root). compare x
        if (root.depth % 2 == 0) {
            if(p.getX() < root.value.getX()) {
                dep++;
                root.left = insertHelper(p, root.left, dep);
            } else {
                dep++;
                root.right = insertHelper(p, root.right, dep);
            }
        } else {
            if(p.getY() < root.value.getY()) {
                dep++;
                root.left = insertHelper(p, root.left, dep);
            } else {
                dep++;
                root.right = insertHelper(p, root.right, dep);
            }
        }
        root.size = 1+ size(root.left) + size(root.right);
        return root;
    }

    private int size(KdNode n) {
        if (n == null) {
            return 0;
        } else {
            return n.size;
        }
    }


    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        KdNode found = nearest(root, goal, root);
        return found.value;
    }

    private KdNode nearest(KdNode n, Point goal, KdNode best) {
        if (n == null) { return best; }
        if (Point.distance(n.value, goal) < Point.distance(best.value, goal)) {
            best = n;
        }
        best = nearest(n.left, goal, best);
        best = nearest(n.right, goal, best);
        return best;
    }

    private class KdNode {
        Point value;
        int size;
        KdNode left;
        KdNode right;
        int depth;

        private KdNode(Point p) {
            size = 1;
            value = p;
            left = null;
            right = null;
            depth = 0;
        }

        private KdNode(Point p, int d) {
            size = 1;
            value = p;
            left = null;
            right = null;
            depth = d;
        }


        private Point getValue(KdNode n) {
            return n.value;
        }

        private int size(KdNode n) {
            return size;
        }

        private int depth(KdNode n) {
            return depth;
        }

    }

}

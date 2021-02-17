import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        // TODO: YOUR CODE HERE
        AListFloorSet alfs = new AListFloorSet();
        RedBlackFloorSet rbfs = new RedBlackFloorSet();
        int M = 1000000;
        for (int i = 0; i < M; i++) {
            double double_random = StdRandom.uniform(-5000, 5000);
            alfs.add(double_random);
            rbfs.add(double_random);
        }
        int N = 100000;
        for (int i = 0; i < N; i++) {
            double double_random = StdRandom.uniform(-5000, 5000);
            assertEquals(alfs.floor(double_random), rbfs.floor(double_random), 0.000001);
        }
    }
}

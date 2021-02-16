import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        //OffByOne obo = new OffByOne();
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(true, offByOne.equalChars('b', 'a'));
        assertEquals(true, offByOne.equalChars('&', '%'));
        assertEquals(false, offByOne.equalChars('a', 'a'));
        assertEquals(false, offByOne.equalChars('a', 'e'));
        assertEquals(false, offByOne.equalChars('z', 'a'));
    }

    // Your tests go here.
}
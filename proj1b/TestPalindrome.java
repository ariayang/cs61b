import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(true, palindrome.isPalindrome("racecar"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(false, palindrome.isPalindrome("horse"));
        assertEquals(false, palindrome.isPalindrome("null"));
    }

    @Test
    public void testisPalindromeOBY() {
        CharacterComparator cc = new OffByOne();
        assertEquals(true, palindrome.isPalindrome("a", cc));
        assertEquals(true, palindrome.isPalindrome("flake", cc));
        assertEquals(true, palindrome.isPalindrome("", cc));
        assertEquals(false, palindrome.isPalindrome("horse", cc));
        assertEquals(false, palindrome.isPalindrome("null", cc));
    }

    @Test
    public void testisPalindromeOBN() {
        CharacterComparator cc = new OffByN(1);
        assertEquals(true, palindrome.isPalindrome("a", cc));
        assertEquals(true, palindrome.isPalindrome("flake", cc));
        assertEquals(true, palindrome.isPalindrome("", cc));
        assertEquals(false, palindrome.isPalindrome("horse", cc));
        assertEquals(false, palindrome.isPalindrome("null", cc));
        CharacterComparator cc5 = new OffByN(5);
        assertEquals(true, palindrome.isPalindrome("a", cc5));
        assertEquals(true, palindrome.isPalindrome("af", cc5));
        assertEquals(true, palindrome.isPalindrome("fa", cc5));
        assertEquals(false, palindrome.isPalindrome("fh", cc5));
    }
}

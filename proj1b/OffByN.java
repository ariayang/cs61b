public class OffByN implements CharacterComparator {

    private int n;

    /** constructor that takes an N */
    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (Math.abs(x - y) == n);
    }
}

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {

    private static int maxNumChars;
    private static LinkedList[] buckets;
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int len = asciis.length;
        String[] result = new String[len];
        String[] temp = new String[len];
        buckets = new LinkedList[256];
        // Find max number of chars in a string
        maxNumChars = Integer.MIN_VALUE;  //max digits of string
        int i = 0;
        for (String str: asciis) {
            maxNumChars = maxNumChars > str.length() ? maxNumChars : str.length();
            result[i] = str;
            i++;
        }

        for (int d = maxNumChars - 1; d >= 0; d--) {
            sortHelperLSD(result, d);
            int count = 0;
            for(i = 0; i < buckets.length; i++) {
                if (buckets[i] == null) { continue; }
                while(buckets[i].size() > 0) {
                    temp[count] = (String) buckets[i].removeFirst();
                    count++;
                }
            }
            result = temp;
        }
        return result;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        // ASCII radix q = 256; int i = (int) 'char'; char a = (char) int;
        // if ascii length < max, pad '_' in the end. buckets start with 0.
        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() <= index) {
                if (buckets[(int)'_'] == null) { buckets[(int)'_'] = new LinkedList(); }
                buckets[(int)'_'].addLast(asciis[i]);
            } else {
                Character cha = asciis[i].charAt(index);
                if (buckets[(int)cha] == null) { buckets[(int)cha] = new LinkedList(); }
                buckets[(int)cha].addLast(asciis[i]);
            }
        }
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}

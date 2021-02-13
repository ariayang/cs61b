/** Performs some basic Array deque tests. */
public class ArrayResizeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove/resize test.");

		//System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addFirst(11);
		lld1.addFirst(9);
		lld1.addLast(12);
		lld1.addFirst(8);
		lld1.addFirst(7);
		lld1.addFirst(6);
		System.out.println("Printing deque after 7 items");
		lld1.printDeque();
		lld1.addLast(13);
		System.out.println("Printing deque after 8 items");
		lld1.printDeque();


		lld1.addLast(14);

		System.out.println("Printing deque after 8 items");
		lld1.printDeque();
		System.out.println("Printing the size of items");
		System.out.println(lld1.size());
		System.out.println("Printing the length of the array");
		//System.out.println(lld1.length);
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addRemoveTest();
	}
} 
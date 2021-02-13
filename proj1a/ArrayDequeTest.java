/** Performs some basic Array deque tests. */
public class ArrayDequeTest {
	
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

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		//System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		System.out.println("Printing out deque: should be front");
		//System.out.println(lld1.items[0]);
		lld1.printDeque();

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		//Tested addFirst for 3 consecutive elements. Passed. //Feb 12, 4:30pm
		//Loopindex passed. Print deque passed.
		//Now changed following two add to addLast to test.
		//Feb 12, 4:36. addLast 2 consecutive elements passed.
		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;
		System.out.println("Printing out deque: should be front, middle");
		lld1.printDeque();


		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: should be front, middle, back");
		lld1.printDeque();

		printTestStatus(passed);



	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */


	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		//System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		System.out.println("Printing out deque after adding 10 at first: should be 10");
		lld1.printDeque();
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty
		System.out.println("Printing out deque after removing first: should be empty");
		lld1.printDeque();
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);


		lld1.addFirst(11);
		System.out.println("Printing out deque after adding 11 to empty at first: ");
		lld1.printDeque();

		lld1.addFirst(10);
		System.out.println("Printing out deque adding 10 at first: should be 10, 11");
		lld1.printDeque();

		lld1.addLast(12);
		System.out.println("Printing out deque after adding 12 at last: should be 10, 11, 12");
		lld1.printDeque();

		System.out.println("Printing removed item 10 at first: should be 10");
		System.out.println(lld1.removeFirst());
		// should not be empty
		System.out.println("Printing out deque after removing 12 at last: should be 11, 12");
		lld1.printDeque();
		//Above removeFirst() passed. 4:51pm

		System.out.println("Printing removed item 10 at first: should be 12");
		System.out.println(lld1.removeLast());
		// should not be empty
		System.out.println("Printing out deque after removing 12 at last: should be 11");
		lld1.printDeque();
		//Passed above. 4:56pm.


		lld1.removeFirst();
		// should be empty
		//System.out.println("Printing out deque after removing 10 at first: should be 11");
		//lld1.printDeque();

		// should not be empty
		System.out.println("Printing out index 0, should be null: ");
		System.out.println(lld1.get(0));

		lld1.addFirst(9);
		System.out.println("Printing out index 0, should be 9: ");
		System.out.println(lld1.get(0));

		lld1.addFirst(8);
		System.out.println("Printing out last item, should be 9: ");
		System.out.println(lld1.getLast());
		//Above passed on 5:06pm

		/*System.out.println("Testing recursive, get index 0, should be 8");
		System.out.println(lld1.getRecursive(0));
		*/
	}



	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
	}
} 
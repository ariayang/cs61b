public class Palindrome {
    /** Converts a string into ArrayDeque implementation */
    public Deque<Character> wordToDeque(String word) {
        Deque myArray = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            myArray.addLast(word.charAt(i));
        }
        return myArray;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        } else if (word == null) {
            return false;
        } else {
            Deque myDeque = wordToDeque(word);
            while (myDeque.size() > 1) {
                if (myDeque.removeFirst() != myDeque.removeLast()) {
                    return false;
                }
            }
            return true;
        }
    }

    /** return true if the word is palindrome. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        } else if (word == null) {
            return false;
        } else {
            Deque d = wordToDeque(word);
            while (d.size() > 1) {
                if (!cc.equalChars((char) d.removeFirst(), (char) d.removeLast())) {
                    return false;
                }
            }
            return true;
        }
    }

}

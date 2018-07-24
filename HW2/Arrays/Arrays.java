/* NOTE: The file ArrayUtil.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author Youmna Rabie
 */
class Arrays {
    /**
     * Returns a new array consisting of the elements of A followed by the
     * the elements of B.
     */
    static int[] catenate(int[] A, int[] B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        int lengthA = A.length;
        int lengthB = B.length;
        int lengthC = lengthA + lengthB;
        int[] result = new int[lengthC];
        System.arraycopy(A, 0, result, 0, lengthA);
        System.arraycopy(B, 0, result, lengthA, lengthB);
        return result;

    }

    /**
     * Returns the array formed by removing LEN items from A,
     * beginning with item #START.
     */
    static int[] remove(int[] A, int start, int len) {
        if (len == 0) {
            return A;
        }
        int[] newA = new int[A.length - len];
        System.arraycopy(A, 0, newA, 0, start);
        System.arraycopy(A, start + len, newA, start, A.length - start - len);
        return newA;
    }

    /* E. */

    /**
     * Returns the array of arrays formed by breaking up A into
     * maximal ascending lists, without reordering.
     * For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     * returns the three-element array
     * {{1, 3, 7}, {5}, {4, 6, 9, 10}}.
     */
    static int[][] naturalRuns(int[] A) {
        if (A == null) {
            return null;
        }
        int counter = 0;
        int currIndex = 0;
        int nextIndex = 1;
        while (nextIndex != A.length) ;
        while (A[currIndex] < A[nextIndex]) {
            currIndex += 1;
            nextIndex += 1;
            counter += 1;
        }
        return new int[2][3];
            
    }

}

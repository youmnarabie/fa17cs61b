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
    static int numSubs(int[] A) {
        int num = 0;
        int curr = 0;
        int next = 1;
        while (next < A.length) {
            while (next < A.length && A[curr] < A[next]) {
                curr += 1;
                next += 1;
            }
            curr += 1;
            next += 1;
            num += 1;
        }
        return num;
    }
    static int[][] naturalRuns(int[] A) {
        int len = numSubs(A);
        int[][] ret = new int[len][];
        int first = 0;
        int next = 1;
        int holdLength = 0;
        int retPos = 0;
        int pointer = 0;
        while (retPos < len) {
            while (next < A.length && A[first] < A[next]) {
                holdLength += 1;
                first += 1;
                next += 1;
            }
            first += 1;
            next += 1;
            holdLength += 1;
            int[] holder = new int[holdLength];
            int holdPointer = 0;
            while (holdPointer < holdLength) {
                holder[holdPointer] = A[pointer];
                pointer += 1;
                holdPointer += 1;
            }
            ret[retPos] = holder;
            retPos += 1;
            holdLength = 0;
        }
        return ret;
    }

}

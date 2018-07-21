/* NOTE: The file Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #1. */

/** List problem.
 *  @author Youmna Rabie
 */
class Lists {
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal strictly ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10, 10, 11),
     *  then result is the four-item list
     *            ((1, 3, 7), (5), (4, 6, 9, 10), (10, 11)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntList2 naturalRuns(IntList L) {
        if (L == null) {
            return null;
        }
        IntList pointer = L;
        IntList2 result = new IntList2();
        while (pointer.tail != null && pointer.head < pointer.tail.head) {
            pointer = pointer.tail;
        }
        IntList holder = pointer.tail;
        pointer.tail = null;
        result.head = L;
        result.tail = naturalRuns(holder);
        return result;

    }
}




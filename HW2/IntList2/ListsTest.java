import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author Youmna Rabie
 */

public class ListsTest {
    /** Sample tests that verify if Lists.java functions work correctly.
     */
    @Test
    public void testNaturalRuns() {
        IntList to_be_broken = IntList.list(1, 3, 7, 5, 4, 6, 9, 10, 10, 11);
        IntList first_part = IntList.list(1, 3, 7);
        IntList second_part = IntList.list(5);
        IntList third_part = IntList.list(4, 6, 9, 10);
        IntList fourth_part = IntList.list(10, 11);
        IntList2 correct_answer = IntList2.list(first_part, second_part, third_part, fourth_part);
        IntList2 actual_answer = Lists.naturalRuns(to_be_broken);
        assertEquals(correct_answer, actual_answer);


        IntList second_to_break = IntList.list(1, 2, 3, 1, 2, 3, 1, 2, 3);
        IntList partition = IntList.list(1, 2, 3);
        IntList2 expected = IntList2.list(partition, partition, partition);
        IntList2 actual = Lists.naturalRuns(second_to_break);
        assertEquals(expected, actual);
    }


    // It might initially seem daunting to try to set up
    // Intlist2 expected.
    //
    // There is an easy way to get the IntList2 that you want in just
    // few lines of code! Make note of the IntList2.list method that
    // takes as input a 2D array.

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}

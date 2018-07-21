import org.junit.Test;
import static org.junit.Assert.*;

/** Series of tests to check that the methods in ArraysTest work as expected.
 *  @author Youmna Rabie
 */

public class ArraysTest {

    /**Tests the functionality of the Catenate method in the Arrays class */
    @Test
    public void testCatenate() {
        int[] A = {1, 2, 3, 4};
        int[] B = {5, 6, 7};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        int[] actual = Arrays.catenate(A, B);
        assertArrayEquals(expected, actual);

        int[] empty = {};
        int[] something = {1, 3, 7};
        int[] correct = {1, 3, 7};
        int[] output = Arrays.catenate(empty, something);
        assertArrayEquals(correct, output);

        int[] nothing = {};
        int[] alsoNothing = {};
        int[] response = Arrays.catenate(nothing, alsoNothing);
        assertArrayEquals(nothing, response);
    }


    /* Tests whether or not remove in Arrays.java works as expected. */
    @Test
    public void testRemove() {
        int[] A = {1, 2, 3, 4, 5, 6, 7};
        int[] answer = {1, 2, 6, 7};
        int[] actual = Arrays.remove(A, 2, 3);
        assertArrayEquals(answer, actual);


        int[] full = {1, 2, 3, 4};
        int[] fullUnchanged = {1, 2, 3, 4};
        int[] output = Arrays.remove(full, 1, 0);
        assertArrayEquals(fullUnchanged, output);

        int[] many = {100, 3, 4, 21, 506, 30, 401, 2, 0};
        int[] fewer = {100, 0};
        int[] produced = Arrays.remove(many, 1, 7);
        assertArrayEquals(fewer, produced);
    }

    @Test
    public void testNaturalRuns(){
        int[] to_be_broken = {1, 3, 7, 5, 4, 6, 9, 10, 10, 11};
        int[] first_part = {1, 3, 7};
        int[] second_part = {5};
        int[] third_part = {4, 6, 9, 10};
        int[] fourth_part = {10, 11};
        int[][] expected = new int[4][1];
        expected[0] = first_part;
        expected[1] = second_part;
        expected[2] = third_part;
        expected[3] = fourth_part;
        int[][] actual_answer = Arrays.naturalRuns(to_be_broken);
        assertArrayEquals(actual_answer, expected);

        int[] second_to_break = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        int[] partition = {1, 2, 3};
        int[][] desired = new int[3][1];
        desired[0] = partition;
        desired[1] = partition;
        desired[2] = partition;
        int[][] output = Arrays.naturalRuns(second_to_break);
        assertArrayEquals(desired, output);
        }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}

import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Sample test that verifies correctness of the IntList.list static
     *  method. The main point of this is to convince you that
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.
     *
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with
     *  IntList empty = IntList.list().
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A.
     */

    @Test
    public void testDcatenate() {
        IntList empty = IntList.list();
        IntList generic = IntList.list(1, 2, 3, 4, 5);
        IntList varying = IntList.list(100, 40, 53, 304, 20103, 4032, 4, 31, 1, 0);
        IntList one_alone = IntList.list(1);


        IntList combining_generic_and_varying = IntList.list(1, 2, 3, 4, 5, 100, 40, 53, 304, 20103, 4032, 4, 31, 1, 0);
        IntList result_of_gen_var = IntList.dcatenate(generic, varying);

        assertEquals(combining_generic_and_varying, result_of_gen_var);

        IntList combining_empty_and_alone = IntList.list(1);
        IntList result_of_empty_alone = IntList.dcatenate(empty, one_alone);

        assertEquals(combining_empty_and_alone, result_of_empty_alone);

    }

    /** Tests that subtail works properly. Again, don't use new.
     *
     *  Make sure to test that subtail does not modify the list.
     */

    @Test
    public void testSubtail() {
        IntList sample = IntList.list(1, 2, 3, 4, 5);
        IntList answer = IntList.list(3, 4, 5);
        IntList actual_answer = IntList.subTail(sample, 2);
        assertEquals(answer, actual_answer);

        IntList nulling = IntList.list();
        IntList answer_2 = IntList.list();
        IntList actual_answer_2 = IntList.subTail(nulling, 3);
        assertEquals(answer_2, actual_answer_2);
    }

    /** Tests that sublist works properly. Again, don't use new.
     *
     *  Make sure to test that sublist does not modify the list.
     */

    @Test
    public void testSublist() {
        IntList sample = IntList.list(1, 2, 3, 4);
        IntList correct_answer = IntList.list(2, 3);
        IntList actual_answer = IntList.sublist(sample, 1, 2);
        assertEquals(correct_answer, actual_answer);

        IntList nullify = IntList.list();
        IntList answer_true = IntList.list();
        IntList actual_answer_2 = IntList.sublist(nullify, 2, 3);
        assertEquals(answer_true, actual_answer_2);

        IntList longer = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntList answering = IntList.list(3, 4, 5, 6, 7);
        IntList real_answer = IntList.sublist(longer, 2, 5);
        assertEquals(answering, real_answer);

    }

    /** Tests that dSublist works properly. Again, don't use new.
     *
     *  As with testDcatenate, it is not safe to assume that list passed
     *  to dSublist is the same after any call to dSublist
     */

    @Test
    public void testDsublist(){
        IntList sample = IntList.list(1, 2, 3, 4);
        IntList correct_answer = IntList.list(2, 3);
        IntList actual_answer = IntList.sublist(sample, 1, 2);
        assertEquals(correct_answer, actual_answer);

        IntList nullify = IntList.list();
        IntList answer_true = IntList.list();
        IntList actual_answer_2 = IntList.sublist(nullify, 2, 3);
        assertEquals(answer_true, actual_answer_2);

        IntList longer = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntList answering = IntList.list(3, 4, 5, 6, 7);
        IntList real_answer = IntList.sublist(longer, 2, 5);
        assertEquals(answering, real_answer);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(IntListTest.class));
    }
}

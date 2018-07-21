import org.junit.Test;
import static org.junit.Assert.*;

/** Series of tests to make sure the MatrixUtils class works as expected.
 *  @author Youmna Rabie
 */

public class MatrixUtilsTest {
    /** Tests to be run on MatrixUtils class
     */

    @Test
    public void testAccumulateVertical(){
        /* tests the AccumulateVertical function in MatrixUtils.java on a 3x3 square matrix */
        double[][] to_accumulate = new double[3][3];
        to_accumulate[0][0] = 100;
        to_accumulate[0][1] = 100;
        to_accumulate[0][2] = 100;
        to_accumulate[1][0] = 200;
        to_accumulate[1][1] = 200;
        to_accumulate[1][2] = 50;
        to_accumulate[2][0] = 250;
        to_accumulate[2][1] = 30;
        to_accumulate[2][2] = 70;
        double[][] expected = new double[3][3];
        expected[0][0] = 100;
        expected[0][1] = 100;
        expected[0][2] = 100;
        expected[1][0] = 300;
        expected[1][1] = 300;
        expected[1][2] = 150;
        expected[2][0] = 550;
        expected[2][1] = 180;
        expected[2][2] = 220;
        double[][] actual = MatrixUtils.accumulateVertical(to_accumulate);
        assertArrayEquals(actual, expected);
    }

    @Test
    public void testAccumulate(){
        double[][] to_accumulate = new double[3][3];
        to_accumulate[0][0] = 100;
        to_accumulate[0][1] = 100;
        to_accumulate[0][2] = 100;
        to_accumulate[1][0] = 200;
        to_accumulate[1][1] = 200;
        to_accumulate[1][2] = 50;
        to_accumulate[2][0] = 250;
        to_accumulate[2][1] = 30;
        to_accumulate[2][2] = 70;
        double[][] expected = new double[3][3];
        expected[0][0] = 100;
        expected[1][0] = 200;
        expected[2][0] = 300;
        expected[0][1] = 200;
        expected[1][1] = 300;
        expected[2][1] = 250;
        expected[0][2] = 250;
        expected[1][2] = 230;
        expected[2][2] = 300;
        double[][] actual = MatrixUtils.accumulate(to_accumulate, MatrixUtils.Orientation.HORIZONTAL);
        assertArrayEquals(actual, expected);
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}

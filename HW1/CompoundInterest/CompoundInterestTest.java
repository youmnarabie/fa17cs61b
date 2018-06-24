import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.
        assertEquals(0, 0); */
        assertEquals(1, CompoundInterest.numYears(2016));
        assertEquals(4, CompoundInterest.numYears(2019));
    }


    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        assertEquals(12.544, CompoundInterest.futureValue(10, 12, 2017), tolerance = 0.01);
        assertEquals(204.073, CompoundInterest.futureValue(150, 8, 2019), tolerance = 0.01);

    }

    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        assertEquals(11.8026496, CompoundInterest.futureValueReal(10, 12, 2017, 3), tolerance = 0.01);
        assertEquals(27516.32, CompoundInterest.futureValueReal(1000, 12, 2055, 3), tolerance = 0.01);
    }


    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000, 2017, 10), tolerance = 0.01);
        assertEquals(1194832, CompoundInterest.totalSavings(250000, 2018, 12), tolerance = 0.01);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        assertEquals(1090490.91, CompoundInterest.totalSavingsReal(250000, 2018, 12, 3), tolerance = 0.01);
        assertEquals(15571.895, CompoundInterest.totalSavingsReal(5000, 2017, 10, 3), tolerance = 0.01);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}

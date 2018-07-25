package db61b;

/**
 * @author Youmna Rabie
 */
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class TestTable {

    @Test
    public void testAdd() {
        String[] titlesA = {"First", "Second", "Third", "Fourth", "Fifth"};
        String[] rowOne = {"A", "B", "C", "D", "E"};
        String[] additions = {"G", "H", "I", "J", "K"};

        Table A = new Table(titlesA);
        assertEquals(A.add(rowOne), true);
        assertEquals(A.add(rowOne), false);
        assertEquals(A.add(additions), true);
        assertEquals(A.add(additions), false);


        String[] titlesB = {"1", "2", "3", "4", "5"};
        Table B = new Table(titlesB);
        B.add(rowOne);
        B.add(additions);
        B.add(titlesA);

        List<Column> colly = new ArrayList<>();
    }


    @Test
    public void testGetTitle() {
        String[] titlesA = {"First", "Second", "Third", "Fourth", "Fifth"};

        Table A = new Table(titlesA);
        assertEquals(A.getTitle(3), "Fourth");
        assertEquals(A.getTitle(4), "Fifth");

        try {
            A.getTitle(7);
        } catch (IndexOutOfBoundsException e) {
            /* Ignore error */
        }
    }

    @Test
    public void testFindColumn() {
        String[] titlesA = {"First", "Second", "Third", "Fourth", "Fifth"};
        String[] rowOne = {"A", "B", "C", "D", "E"};
        String[] additions = {"G", "H", "I", "J", "K"};

        Table A = new Table(titlesA);
        assertEquals(A.findColumn("Second"), 1);
        assertEquals(A.findColumn("Third"), 2);
        assertEquals(A.findColumn("not a column"), -1);
        assertEquals(A.findColumn(" "), -1);
    }

    @Test
    public void testGetAndFindAndSize() {
        String[] titlesA = {"First", "Second", "Third", "Fourth", "Fifth"};
        String[] rowOne = {"A", "B", "C", "D", "E"};
        String[] additions = {"G", "H", "I", "J", "K"};
        String[] thirdRow = {"L", "M", "N", "O", "P"};

        Table A = new Table(titlesA);
        A.add(rowOne);
        A.add(additions);
        A.add(thirdRow);

        String result = A.get(2, 3);
        assertEquals(result, "O");

        int first = A.findColumn("First");
        assertEquals(first, 0);

        int third = A.findColumn("Third");
        assertEquals(third, 2);

        int size = A.size();
        assertEquals(size, 3);


    }

    @Test
    public void testAddandReadandWrite() {
        String[] names = {"Sandwiches", "Price", "Rating"};
        Table A = new Table(names);
        A.add(new String[]{"balogna", "2", "5"});
        A.add(new String[]{"vegan", "6", "2.4"});
        A.add(new String[]{"grilled cheese", "4", "10"});

        A.writeTable("sandwich");
        Table testReader = A.readTable("sandwich");
        testReader.print();
        ArrayList<String> selectCol = new ArrayList<>();
        selectCol.add("Sandwiches");
        Table selected = A.select(selectCol, null);
        selected.print();
    }

    @Test
    public void testDatabase() {
        Table t = new Table(new String[] {"A", "B", "C", "D", "E", "F"});
        String[] first = new String[] {"1", "2", "3", "4", "5", "6"};
        String[] second = new String[] {"2", "3", "4", "5", "6", "7"};
        String[] third = new String[] {"3", "4", "5", "6", "7", "8"};
        t.add(first);
        t.add(second);
        t.add(third);
        Database d = new Database();
        assertEquals(null, d.get("scsdc"));
        d.put("students", t);
        assertEquals(t, d.get("students"));
    }


    @Test
    public void testTableSelect() {
        Table students = Table.readTable("enrolled");
        List<String> listStr = new ArrayList<String>();
        listStr.add("SID");
        listStr.add("CCN");
        List<Condition> conditions = new ArrayList<Condition>();
        Table table = students.select(listStr, conditions);
        table.writeTable("numbers");
        List<String> anotherListStr = new ArrayList<String>();
        anotherListStr.add("Extras");
        try {
            Table table2 = students.select(anotherListStr, conditions);
            table2.writeTable("bunches");
        } catch (DBException exep) {
            exep.getCause();
        }
    }
}

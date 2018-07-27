package db61b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import static db61b.Utils.*;

/** A single table in a database.
 *  @author P. N. Hilfinger
 */
class Table {
    /**
     * A new Table whose columns are given by COLUMNTITLES, which may
     * not contain duplicate names.
     */
    Table(String[] columnTitles) {
        //to do
    }

    /**
     * A new Table whose columns are give by COLUMNTITLES.
     */
    Table(List<String> columnTitles) {
        //to do
    }

    /**
     * Return the number of columns in this table.
     */
    public int columns() {
        //to do
        return 0;
    }

    /**
     * Return the title of the Kth column.  Requires 0 <= K < columns().
     */
    public String getTitle(int k) {
        //to do
        return null;
    }

    /**
     * Return the number of the column whose title is TITLE, or -1 if
     * there isn't one.
     */
    public int findColumn(String title) {
        //to do
        return 0;
    }


    /**
     * Return the number of rows in this table.
     */
    public int size() {
        //to do
        return 0;
    }

    /**
     * Return the value of column number COL (0 <= COL < columns())
     * of record number ROW (0 <= ROW < size()).
     */
    public String get(int row, int col) {
        //to do
        return null;
    }

    /**
     * Add a new row whose column values are VALUES to me if no equal
     * row already exists.  Return true if anything was added,
     * false otherwise.
     */

    public boolean add(String[] values) {
        //to do
        return false;
    }


    /**
     * Add a new row whose column values are extracted by COLUMNS from
     * the rows indexed by ROWS, if no equal row already exists.
     * Return true if anything was added, false otherwise. See
     * Column.getFrom(Integer...) for a description of how Columns
     * extract values.
     */
    public boolean add(List<Column> columns, Integer... rows) {
        //to do
        return false;
    }

    /**
     * Read the contents of the file NAME.db, and return as a Table.
     * Format errors in the .db file cause a DBException.
     */
    static Table readTable(String name) {
        BufferedReader input;
        Table table;
        input = null;
        try {
            String fileName = "/Documents/fa17cs61b/proj1/testing/";
            input = new BufferedReader(new FileReader(name + ".db"));
            String header = input.readLine();
            if (header == null) {
                throw error("missing header in DB file");
            }
            String[] columns = header.split(",");
            String headerNew = input.readLine();
            table = new Table(columns);
            while (headerNew != null) {
                String[] currRow = headerNew.split(",");
                table.add(currRow);
                headerNew = input.readLine();
            }
        } catch (FileNotFoundException e) {
            throw error("could not find %s.db", name);
        } catch (IOException e) {
            throw error("problem reading from %s.db", name);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
        return table;
    }

    /**
     * Write the contents of TABLE into the file NAME.db. Any I/O errors
     * cause a DBException.
     */
    void writeTable(String name) {
        PrintStream output;
        output = null;
        try {
            String sep;
            sep = "";
            output = new PrintStream(name + ".db");
            int length = _titles.length;

            for (int k = 0; k < length; k += 1) {
                if (k != length - 1) {
                    output.print(getTitle(k) + ",");
                } else {
                    output.print(getTitle(k));
                }
            }
            output.println();
            for (String[] row : _rows) {
                for (int i = 0; i < row.length; i += 1) {
                    if (i != row.length - 1) {
                        output.print(row[i] + ",");
                    } else {
                        output.print(row[i]);
                    }
                }
                output.println();
            }
        } catch (IOException e) {
            throw error("trouble writing to %s.db", name);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Print my contents on the standard output, separated by spaces
     * and indented by two spaces.
     */
    void print() {
        //to do
    }


    /**
     * Return a new Table whose columns are COLUMNNAMES, selected from
     * rows of this table that satisfy CONDITIONS.
     */
    Table select(List<String> columnNames, List<Condition> conditions) {
        //to do
        return null;
    }

    /**
     * Return a new Table whose columns are COLUMNNAMES, selected
     * from pairs of rows from this table and from TABLE2 that match
     * on all columns with identical names and satisfy CONDITIONS.
     */
    Table select(Table table2, List<String> columnNames,
                 List<Condition> conditions) {
        //to do
        return null;
    }


    /** Return <0, 0, or >0 depending on whether the row formed from
     *  the elements _columns[0].get(K0), _columns[1].get(K0), ...
     *  is less than, equal to, or greater than that formed from elememts
     *  _columns[0].get(K1), _columns[1].get(K1), ....  This method ignores
     *  the _index. */
    private int compareRows(int k0, int k1) {
        //to do
        return 0;
    }

    /** Return true if the columns COM1 from ROW1 and COM2 from
     *  ROW2 all have identical values.  Assumes that COM1 and
     *  COM2 have the same number of elements and the same names,
     *  that the columns in COM1 apply to this table, those in
     *  COM2 to another, and that ROW1 and ROW2 are indices, respectively,
     *  into those tables. */
    private static boolean equijoin(List<Column> com1, List<Column> com2,
                                    int row1, int row2) {
        //to do
        return false;

    }

    /** My column titles. */
    //CHANGE THESE
    private final String[] _titles = new String[0];
    /** My columns. Row i consists of _columns[k].get(i) for all k. */
    private final ValueList[] _columns = new ValueList[1];
    /** My rows in a hashset.*/
    private HashSet<String[]> _rows = new HashSet<>();


    /** A class that is essentially ArrayList<String>.  For technical reasons,
     *  we need to encapsulate ArrayList<String> like this because the
     *  underlying design of Java does not properly distinguish between
     *  different kinds of ArrayList at runtime (e.g., if you have a
     *  variable of type Object that was created from an ArrayList, there is
     *  no way to determine in general whether it is an ArrayList<String>,
     *  ArrayList<Integer>, or ArrayList<Object>).  This leads to annoying
     *  compiler warnings.  The trick of defining a new type avoids this
     *  issue. */
    private static class ValueList extends ArrayList<String> {
    }

    /** Rows in the database are supposed to be sorted. To do so, we
     *  have a list whose kth element is the index in each column
     *  of the value of that column for the kth row in lexicographic order.
     *  That is, the first row (smallest in lexicographic order)
     *  is at position _index.get(0) in _columns[0], _columns[1], ...
     *  and the kth row in lexicographic order in at position _index.get(k).
     *  When a new row is inserted, insert its index at the appropriate
     *  place in this list.
     *  (Alternatively, we could simply keep each column in the proper order
     *  so that we would not need _index.  But that would mean that inserting
     *  a new row would require rearranging _rowSize lists (each list in
     *  _columns) rather than just one. */
    private final ArrayList<Integer> _index = new ArrayList<>();

    /** An arraylist of all rows (added for .get() functionality). */
    private ArrayList<String[]> _rowsrows = new ArrayList<>();

}

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
        if (columnTitles.length == 0) {
            throw error("table must have at least one column");
        }
        _size = 0;
        _rowSize = columnTitles.length;

        for (int i = columnTitles.length - 1; i >= 1; i -= 1) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (columnTitles[i].equals(columnTitles[j])) {
                    throw error("duplicate column name: %s",
                            columnTitles[i]);
                }
            }
        }
        _titles = columnTitles;
        _columns = new ValueList[_rowSize];
        for (int k = 0; k < _rowSize; k += 1) {
            _columns[k] = new ValueList();
        }
    }

    /**
     * A new Table whose columns are give by COLUMNTITLES.
     */
    Table(List<String> columnTitles) {
        this(columnTitles.toArray(new String[columnTitles.size()]));
    }

    /**
     * Return the number of columns in this table.
     */
    public int columns() {
        return _titles.length;
    }

    /**
     * Return the title of the Kth column.  Requires 0 <= K < columns().
     */
    public String getTitle(int k) {
        if (k > columns() || k < 0) {
            throw new
                    IndexOutOfBoundsException("invalid k");
        } else {
            return _titles[k];
        }
    }

    /**
     * Return the number of the column whose title is TITLE, or -1 if
     * there isn't one.
     */
    public int findColumn(String title) {
        for (int i = 0; i < _titles.length; i += 1) {
            if (_titles[i].equals(title)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Return the number of rows in this table.
     */
    public int size() {
        return _size;
    }

    /**
     * Return the value of column number COL (0 <= COL < columns())
     * of record number ROW (0 <= ROW < size()).
     */
    public String get(int row, int col) {
        try {
            return _columns[col].get(row);
        } catch (IndexOutOfBoundsException excp) {
            throw error("invalid row or column");
        }
    }

    /**
     * Add a new row whose column values are VALUES to me if no equal
     * row already exists.  Return true if anything was added,
     * false otherwise.
     */

    public boolean add(String[] values) {
        for (int i = 0; i < size(); i += 1) {
            for (int j = 0; j < columns(); j += 1) {
                if (!values[j].equals(get(i, j))) {
                    break;
                } else if (j == columns() - 1) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        _size += 1;
        _rows.add(values);
        _rowsrows.add(values);
        for (int curr = 0; curr < _columns.length; curr += 1) {
            _columns[curr].add(values[curr]);
        }
        int originalSize = _index.size();
        int numIndex = _size - 1;
        for (int counter = 0; counter < _size; counter += 1) {
            if (_size == 1) {
                _index.add(numIndex);
                break;
            } else if (counter == _size - 1) {
                _index.add(numIndex);
                break;
            } else if (compareRows(counter, numIndex) < 0) {
                continue;
            } else {
                //_index.add(counter, numIndex);
                //break;
                int posInIndex = 0;
                for (int i = 0; i < _index.size(); i += 1) {
                    if (_index.get(i) == counter) {
                        posInIndex = i;
                        break;
                    }
                }
                if (posInIndex == 0) {
                    _index.add(counter, numIndex);
                    break;
                } else {
                    int holder = counter;
                    while (compareRows(holder, numIndex) > 0) {
                        holder += 1;
                        if (compareRows(holder, numIndex) < 0) {
                            int posInIndex2 = 0;
                            for (int i = 0; i < _index.size(); i += 1) {
                                if (_index.get(i) == holder) {
                                    posInIndex2 = i;
                                    break;
                                }
                            }
                            _index.add(posInIndex2 + 1, numIndex);
                            break;
                        } else if (holder == numIndex) {
                            _index.add(numIndex);
                        }
                    }
                    if (_index.size() != originalSize) {
                        break;
                    }
                }
            }
        }
        return true;
    }


    /**
     * Add a new row whose column values are extracted by COLUMNS from
     * the rows indexed by ROWS, if no equal row already exists.
     * Return true if anything was added, false otherwise. See
     * Column.getFrom(Integer...) for a description of how Columns
     * extract values.
     */
    public boolean add(List<Column> columns, Integer... rows) {
        String[] newRow = new String[columns.size()];
        for (int i = 0; i < columns.size(); i += 1) {
            newRow[i] = columns.get(i).getFrom(rows[i]);
        }
        if (!_rows.contains(newRow)) {
            add(newRow);
        }
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
            String fileName = "/Users/youmnarabie/repo/proj1/testing/";
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
        for (int i : _index) {
            for (int j = 0; j < _rowSize; j += 1) {
                System.out.print(" " + _rowsrows.get(i)[j]);
            }
            System.out.println();
        }
    }


    /**
     * Return a new Table whose columns are COLUMNNAMES, selected from
     * rows of this table that satisfy CONDITIONS.
     */
    Table select(List<String> columnNames, List<Condition> conditions) {
        Table result = new Table(columnNames);
        for (int i = 0; i < _size; i += 1) {
            if (Condition.test(this, conditions, i)) {
                String[] newRow = new String[columnNames.size()];
                for (int j = 0; j < columnNames.size(); j += 1) {
                    int originalIndex = findColumn(columnNames.get(j));
                    newRow[j] = get(i, originalIndex);
                }
                result.add(newRow);
            }
        }
        return result;
    }

    /**
     * Return a new Table whose columns are COLUMNNAMES, selected
     * from pairs of rows from this table and from TABLE2 that match
     * on all columns with identical names and satisfy CONDITIONS.
     */
    Table select(Table table2, List<String> columnNames,
                 List<Condition> conditions) {
        ArrayList<Column> common1 = new ArrayList<>();
        ArrayList<Column> common2 = new ArrayList<>();
        ArrayList<String> allColNames = new ArrayList<>();
        for (int i = 0; i < columns(); i += 1) {
            allColNames.add(getTitle(i));
        }
        for (int i = 0; i < table2.columns(); i += 1) {
            if (!allColNames.contains(table2.getTitle(i))) {
                allColNames.add(table2.getTitle(i));
            }
        }
        Table naturalJoin = new Table(allColNames);
        for (int i = 0; i < columns(); i += 1) {
            String colTitle = getTitle(i);
            int indexIn2 = table2.findColumn(colTitle);
            if (indexIn2 >= 0) {
                Column col1 = new Column(colTitle, this);
                Column col2 = new Column(colTitle, table2);
                common1.add(col1);
                common2.add(col2);
            }
        }
        for (int j = 0; j < table2.size(); j += 1) {
            for (int k = 0; k < size(); k += 1) {
                String[] newRow = new String[allColNames.size()];
                boolean equi = equijoin(common1, common2, k, j);
                if (equi) {
                    for (int m = 0; m < allColNames.size(); m += 1) {
                        if (m < columns()) {
                            newRow[m] = get(k, m);
                        } else {
                            String thisColName = allColNames.get(m);
                            int posIn2 = table2.findColumn(thisColName);
                            newRow[m] = table2.get(j, posIn2);
                        }
                    }
                    naturalJoin.add(newRow);
                }
            }
        }
        return naturalJoin.select(columnNames, conditions);
    }


    /** Return <0, 0, or >0 depending on whether the row formed from
     *  the elements _columns[0].get(K0), _columns[1].get(K0), ...
     *  is less than, equal to, or greater than that formed from elememts
     *  _columns[0].get(K1), _columns[1].get(K1), ....  This method ignores
     *  the _index. */
    private int compareRows(int k0, int k1) {
        for (int i = 0; i < _columns.length; i += 1) {
            int c = _columns[i].get(k0).compareTo(_columns[i].get(k1));
            if (c != 0) {
                return c;
            }
        }
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
        for (int i = 0; i < com1.size(); i += 1) {
            String one = com1.get(i).getFrom(row1);
            String two = com2.get(i).getFrom(row2);
            boolean relation = one.equals(two);
            if (!relation) {
                return false;
            }
        }
        return true;

    }

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

    /** My column titles. */
    private final String[] _titles;
    /** My columns. Row i consists of _columns[k].get(i) for all k. */
    private final ValueList[] _columns;
    /** My rows in a hashset.*/
    private HashSet<String[]> _rows = new HashSet<>();

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

    /** My number of rows (redundant, but convenient). */
    private int _size;
    /** My number of columns (redundant, but convenient). */
    private final int _rowSize;

}

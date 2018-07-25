package db61b;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/** Represents a single 'where' condition in a 'select' command.
 *  @author Youmna Rabie */
class Condition {

    /** A Condition representing COL1 RELATION COL2, where COL1 and COL2
     *  are column designators. and RELATION is one of the
     *  strings "<", ">", "<=", ">=", "=", or "!=". */
    Condition(Column col1, String relation, Column col2) throws DBException {
        _col1 = col1;
        _col2 = col2;
        _relation = relation;
        if (!_poss.contains(relation)) {
            throw new DBException("invalid relation");
        }
    }

    /** A Condition representing COL1 RELATION 'VAL2', where COL1 is
     *  a column designator, VAL2 is a literal value (without the
     *  quotes), and RELATION is one of the strings "<", ">", "<=",
     *  ">=", "=", or "!=".
     */
    Condition(Column col1, String relation, String val2) {
        this(col1, relation, (Column) null);
        _val2 = val2;
    }

    /** Assuming that ROWS are row indices in the respective tables
     *  from which my columns are selected, returns the result of
     *  performing the test I denote.
     *  SOURCE is the table from which we get our data. */
    boolean test(Table source, Integer... rows) {
        _col1name = _col1.getName();
        String getFromTable = source.get(rows[0], source.findColumn(_col1name));
        String value;
        int comparison;
        if (_col2 != null) {
            _col2name = _col2.getName();
            value = source.get(rows[0], source.findColumn(_col2name));
            comparison = getFromTable.compareTo(value);
        } else {
            value = _val2;
            comparison = getFromTable.compareTo(_val2);
        }
        switch (_relation) {
        case "=":
            return comparison == 0;
        case "!=":
            return comparison != 0;
        case ">":
            return comparison > 0;
        case "<":
            return comparison < 0;
        case ">=":
            return comparison >= 0;
        case "<=":
            return comparison <= 0;
        default:
            return false;
        }
    }


    /** Return true iff ROWS satisfies all CONDITIONS.
     *  SOURCE is the table we operate on
     *  CONDS is the conditions.
     * */
    static boolean test(Table source, List<Condition> conds, Integer... rows) {
        if (conds == null) {
            return true;
        }
        for (Condition cond : conds) {
            if (!cond.test(source, rows)) {
                return false;
            }
        }
        return true;
    }

    /** Accesses comparators.
     * @return the string of comparators.
      */
    static String[] getComparators() {
        return comp;
    }

    /** The operands of this condition.  _col2 is null if the second operand
     *  is a literal. */
    private Column _col1, _col2;

    /** The names of my operands. */
    private String _col1name, _col2name;

    /** Second operand, if literal (otherwise null). */
    private String _val2;

    /** Relation used.*/
    private String _relation;
    /** Possible comparators in string form. */
    private static String[] comp = {"<", ">", "<=", ">=", "=", "!="};
    /** Set of all relations. */
    private static Set<String> _poss = new HashSet<String>(Arrays.asList(comp));
}

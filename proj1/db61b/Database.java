package db61b;

import java.util.HashMap;

/** A collection of Tables, indexed by name.
 *  @author Youmna Rabie*/

class Database {
    /** An empty database. */

    public Database() {
        _names = new HashMap<String, Table>();
    }

    /** Return the Table whose name is NAME stored in this database, or null
     *  if there is no such table. */
    public Table get(String name) {
        // to do
        return null;
    }

    /** Set or replace the table named NAME in THIS to TABLE.  TABLE and
     *  NAME must not be null, and NAME must be a valid name for a table. */
    public void put(String name, Table table) {
        //to do
    }

    /** A hashmap with table names as the keys and tables as the values.*/
    private HashMap<String, Table> _names;
}

package game2048;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Youmna Rabie
 */
class Model extends Observable {

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to _board[c][r].  Be careful! This is not the usual 2D matrix
     * numbering, where rows are numbered from the top, and the row
     * number is the *first* index. Rather it works like (x, y) coordinates.
     */

    /**
     * Largest piece value.
     */
    private static final int MAX_PIECE = 2048;

    /**
     * A new 2048 game on a board of size SIZE with no pieces
     * and score 0.
     */
    Model(int size) {
        _board = new Tile[size][size];
        _score = _maxScore = 0;
        _gameOver = false;
    }

    /**
     * Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     * 0 <= COL < size(). Returns null if there is no tile there.
     */
    Tile tile(int col, int row) {
        return _board[col][row];
    }

    /**
     * Return the number of squares on one side of the board.
     */
    int size() {
        return _board.length;
    }

    /**
     * Return true iff the game is over (there are no moves, or
     * there is a tile with value 2048 on the board).
     */
    boolean gameOver() {
        return _gameOver;
    }

    /**
     * Return the current score.
     */
    int score() {
        return _score;
    }

    /**
     * Return the current maximum game score (updated at end of game).
     */
    int maxScore() {
        return _maxScore;
    }

    /**
     * Clear the board to empty and reset the score.
     */
    void clear() {
        _score = 0;
        _gameOver = false;
        for (Tile[] column : _board) {
            Arrays.fill(column, null);
        }
        setChanged();
    }

    /**
     * Add TILE to the board.  There must be no Tile currently at the
     * same position.
     */
    void addTile(Tile tile) {
        assert _board[tile.col()][tile.row()] == null;
        _board[tile.col()][tile.row()] = tile;
        checkGameOver();
        setChanged();
    }

    /**
     * Tilt the board toward SIDE. Return true iff this changes the board.
     */
    boolean tilt(Side side) {
        boolean changed;
        changed = false;
        for (int col = 0; col < size(); col += 1) {
            for (int row = size() - 1; row >= 0; row -= 1) {
                int dummyRow = row - 1;
                Tile curr = vTile(col, row, side);
                while (curr == null && dummyRow >= 0) {
                    curr = vTile(col, dummyRow, side);
                    dummyRow -= 1;
                }
                if (curr != null) {
                    if (dummyRow != row - 1) {
                        setVTile(col, row, side, curr);
                        changed = true;
                    }
                }
            }
        }
        for (int colMerge = 0; colMerge < size(); colMerge += 1) {
            for (int rowMerge = size() - 1; rowMerge >= 0; rowMerge -= 1) {
                Tile currMerge = vTile(colMerge, rowMerge, side);
                if (rowMerge - 1 >= 0) {
                    Tile below = vTile(colMerge, rowMerge - 1, side);
                    if (below != null) {
                        if (currMerge != null) {
                            if (currMerge.value() == below.value()) {
                                setVTile(colMerge, rowMerge, side, below);
                                _score += currMerge.value() * 2;
                                changed = true;
                            }
                        }
                    }
                }
            }
        }
        for (int col2 = 0; col2 < size(); col2 += 1) {
            for (int row2 = size() - 1; row2 >= 0; row2 -= 1) {
                int dummyrow2 = row2 - 1;
                Tile current2 = vTile(col2, row2, side);
                while (current2 == null && dummyrow2 >= 0) {
                    current2 = vTile(col2, dummyrow2, side);
                    dummyrow2 -= 1;
                }
                if (current2 != null) {
                    setVTile(col2, row2, side, current2);
                }
            }
        }
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /**
     * Return the current Tile at (COL, ROW), when sitting with the board
     * oriented so that SIDE is at the top (farthest) from you.
     */
    private Tile vTile(int col, int row, Side side) {
        return _board[side.col(col, row, size())][side.row(col, row, size())];
    }

    /**
     * Move TILE to (COL, ROW), merging with any tile already there,
     * where (COL, ROW) is as seen when sitting with the board oriented
     * so that SIDE is at the top (farthest) from you.
     */
    private void setVTile(int col, int row, Side side, Tile tile) {
        int pCol = side.col(col, row, size()),
                prow = side.row(col, row, size());
        if (tile.col() == pCol && tile.row() == prow) {
            return;
        }
        Tile tile1 = vTile(col, row, side);
        _board[tile.col()][tile.row()] = null;

        if (tile1 == null) {
            _board[pCol][prow] = tile.move(pCol, prow);
        } else {
            _board[pCol][prow] = tile.merge(pCol, prow, tile1);
        }
    }

    /**
     * Determine whether game is over and update _gameOver and _maxScore
     * accordingly.
     */
    private void checkGameOver() {
        for (int col = 0; col < size(); col += 1) {
            for (int row = 0; row < size(); row += 1) {
                if (_board[col][row] != null) {
                    if (_board[col][row].value() == MAX_PIECE) {
                        _gameOver = true;
                        if (_score > _maxScore) {
                            _maxScore = _score;
                        }
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        for (int col = 0; col < size(); col += 1) {
            for (int row = 0; row < size() - 1; row += 1) {
                Tile north = vTile(col, row, Side.NORTH);
                Tile south = vTile(col, row, Side.SOUTH);
                Tile east = vTile(col, row, Side.EAST);
                Tile west = vTile(col, row, Side.WEST);
                Tile aboveEast = vTile(col, row + 1, Side.EAST);
                Tile aboveNorth = vTile(col, row + 1, Side.NORTH);
                Tile aboveWest = vTile(col, row + 1, Side.WEST);
                Tile aboveSouth = vTile(col, row + 1, Side.SOUTH);
                if (north.value() == aboveNorth.value()) {
                    return;
                }
                if (south.value() == aboveSouth.value()) {
                    return;
                }
                if (east.value() == aboveEast.value()) {
                    return;
                }
                if (west.value() == aboveWest.value()) {
                    return;
                }
            }
        }
        _gameOver = true;
        if (_score > _maxScore) {
            _maxScore = _score;
        }
    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        out.format("] %d (max: %d)", score(), maxScore());
        return out.toString();
    }
    /** Current contents of the board. */
    private Tile[][] _board;
    /** Current score. */
    private int _score;
    /** Maximum score so far.  Updated when game ends. */
    private int _maxScore;
    /** True iff game is ended. */
    private boolean _gameOver;
}

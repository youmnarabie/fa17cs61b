
public class IntDList {

    protected DNode _front, _back;

    /** Initialize an empty DList with front and back initialized as NULL. */
    public IntDList() {
        _front = _back = null;
    }

    /** DList constructor with an array of VALUES. */
    public IntDList(Integer... values) {
        _front = _back = null;
        for (int val : values) {
            insertBack(val);
        }
    }

    /** Returns the first item in the IntDList. */
    public int getFront() {
        return _front._val;
    }

    /** Returns the last item in the IntDList. */
    public int getBack() {
        return _back._val;
    }

    /** Return value #I in this list, where item 0 is the first, 1 is the
     *  second, ...., -1 is the last, -2 the second to last.... */
    public int get(int i) {
        DNode curr = null;
        if (i == 0) {
            return this._front._val;
        } else if (i == -1) {
            return this._back._val;
        } else if (i > 0) {
            curr = this._front;
            while (i > 0) {
                curr = curr._next;
                i -= 1;
            }
        } else if (i < 0) {
            curr = this._back;
            while (i < -1) {
                curr = curr._prev;
                i += 1;
            }
        }
        return curr._val;
    }

    /** The length of this list. */
    public int size() {
        if (this._front == null && this._back == null) {
            return 0;
        } else if (this._front == null) {
            _front = _back;
        }
        int counter = 0;
        DNode curr = this._front;
        while (curr != null) {
            counter += 1;
            curr = curr._next;
        }
        return counter;
    }

    /** Adds D to the front of the IntDList. */
    public void insertFront(int d) {
        DNode newFront = new DNode(d);
        if (this._front == null) {
            this._front = newFront;
        }
        _front._prev = newFront;
        newFront._next = _front;
        _front = newFront;
    }

    /** Adds D to the back of the IntDList. */
    public void insertBack(int d){
        DNode newBack = new DNode(d);
        if (this._front == null) {
            _front = _back;
        }
        if (this._back == null) {
            this._back = newBack;
        }
        _back._next = newBack;
        newBack._prev = _back;
        _back = newBack;
    }

    /** Removes the last item in the IntDList and returns it.
     * This is an extra challenge problem. */
    public int deleteBack() {
        if (this._back == null) {
            int hold = this._front._val;
            this._front = null;
            return hold;
        }
        if (this._front == null) {
            this._back = null;
        }
        int toReturn = _back._val;
        DNode curr = this._back._prev;
        curr._next = null;
        _back = curr;
        return toReturn;
    }

    /** Returns a string representation of the IntDList in the form
     *  [] (empty list) or [1, 2], etc. 
     * This is an extra challenge problem. */
    public String toString() {
        String toPrint = "[";
        DNode point = this._front;
        for (int i = 0; i < this.size(); i += 1) {
            if (i != this.size() - 1) {
                toPrint += (String.valueOf(point._val) + ", ");
                point = point._next;
            } else {
                toPrint += (String.valueOf(point._val));
                point = point._next;
            }
        }
        toPrint += "]";
        return toPrint;
    }

    /* DNode is a "static nested class", because we're only using it inside
     * IntDList, so there's no need to put it outside (and "pollute the
     * namespace" with it. This is also referred to as encapsulation.
     * Look it up for more information! */
    protected static class DNode {
        protected DNode _prev;
        protected DNode _next;
        protected int _val;

        private DNode(int val) {
            this(null, val, null);
        }

        private DNode(DNode prev, int val, DNode next) {
            _prev = prev;
            _val = val;
            _next = next;
        }
    }

}

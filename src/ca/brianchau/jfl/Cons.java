package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-06.
 */
public class Cons extends Value<Lambda> {
    private Cons c;
    Cons(Lambda l, Cons c) {
        super(l);
        this.c = c;
    }
    @Override
    public String toString() {
        return "(Cons " + t + " " + c + ")";
    }
    boolean isEmpty() {
        return false;
    }
    Cons next() {
        return c;
    }
}

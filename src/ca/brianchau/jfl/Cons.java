package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-06.
 */
public class Cons extends Value<Value> {
    private Cons c;
    Cons(Value v, Cons c) {
        super(v);
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

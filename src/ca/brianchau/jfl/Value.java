package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-06.
 */
public abstract class Value<T> implements Lambda {
    protected T t;
    protected Value(T t) {
        this.t = t;
    }
    @Override
    public Lambda eval(Lambda l) {
        throw new JFLException("Unable to operate on a value; not a function.");
    }
    public T value() {
        return t;
    }
    public String toString() { return t.toString(); }
}

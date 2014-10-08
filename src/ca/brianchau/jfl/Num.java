package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-07.
 */
public abstract class Num<T extends Number> extends Value<T> {
    protected Num(T o) {
        super(o);
    }
}

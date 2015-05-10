package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-07.
 */
public abstract class Num<T extends Number> extends Value<T> {
    boolean isInteger;
    protected Num(T o, boolean isInteger) {
        super(o);
        this.isInteger = isInteger;
    }
}

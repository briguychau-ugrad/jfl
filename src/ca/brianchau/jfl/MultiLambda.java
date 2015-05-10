package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-15.
 */
public abstract class MultiLambda<T> extends Value<T> {
    T under;

    protected MultiLambda(T t) {
        super(t);
    }

    public abstract Lambda apply(Lambda l);
}

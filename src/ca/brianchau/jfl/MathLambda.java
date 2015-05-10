package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-15.
 */
public abstract class MathLambda extends Num {
    protected MathLambda(Num num) {
        super((Number)num.t);
    }
    @Override
    public abstract Lambda apply(Lambda l);
}

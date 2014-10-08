package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-06.
 */
public class Flt extends Value<Double> implements Num {
    Flt(Double d) {
        super(d);
    }
    public String toString() {
        return t.toString();
    }
}

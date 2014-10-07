package ca.brianchau.jfl;

/**
 * Created by brian on 2014-10-06.
 */
public class Int extends Value<Long> {
    Int(Long l) {
        super(l);
    }
    Int(Integer i) {
        super(i.longValue());
    }
    public String toString() {
        return t.toString();
    }
}

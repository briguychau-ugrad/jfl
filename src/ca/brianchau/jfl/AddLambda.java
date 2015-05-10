package ca.brianchau.jfl;

import static ca.brianchau.jfl.Util.Flt;
import static ca.brianchau.jfl.Util.Int;

/**
 * Created by brian on 2014-10-15.
 */
public class AddLambda extends MathLambda {
    public AddLambda() {
        super(new Int(0));
    }

    private AddLambda(Num n) {
        super(n);
    }

    @Override
    public Lambda apply(Lambda l) {
        if (t instanceof Flt) {
            if (l instanceof Flt) {
                return new AddLambda(Flt(((Flt) t).value() + ((Flt) l).value()));
            } else if (l instanceof Int) {
                return new AddLambda(Flt(((Flt) t).value() + ((Int) l).value()));
            }
        } else if (t instanceof Int) {
            if (l instanceof Flt) {
                return new AddLambda(Flt(((Int) t).value() + ((Flt) l).value()));
            } else if (l instanceof Int) {
                return new AddLambda(Int(((Int) t).value() + ((Int) l).value()));
            }
        }
        throw new JFLException("Unable to add non-numerical values.");
    }
}

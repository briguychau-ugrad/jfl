package ca.brianchau.jfl;

import java.util.Scanner;

/**
 * Created by brian on 2014-10-06.
 */
public class Util {

    public static Scanner s = new Scanner(System.in);

    // Constructors
    public static Int Int(int i) {
        return new Int(i);
    }
    public static Int Int(long l) {
        return new Int(l);
    }
    public static Flt Flt(double d) {
        return new Flt(d);
    }
    public static Bool Bool(boolean b) {
        return new Bool(b);
    }
    public static Cons Cons(Lambda l, Cons c) {
        return new Cons(l, c);
    }

    // Math Functions
    public static Lambda add = a -> b -> {
        if (a instanceof Flt) {
            if (b instanceof Flt) {
                return Flt(((Flt) a).value() + ((Flt) b).value());
            } else if (b instanceof Int) {
                return Flt(((Flt) a).value() + ((Int) b).value());
            }
        } else if (a instanceof Int) {
            if (b instanceof Flt) {
                return Flt(((Int) a).value() + ((Flt) b).value());
            } else if (b instanceof Int) {
                return Int(((Int) a).value() + ((Int) b).value());
            }
        }
        throw new JFLException("Unable to add non-numerical values.");
    };
    public static Lambda sub = a -> b -> {
        if (a instanceof Flt) {
            if (b instanceof Flt) {
                return Flt(((Flt) a).value() - ((Flt) b).value());
            } else if (b instanceof Int) {
                return Flt(((Flt) a).value() - ((Int) b).value());
            }
        } else if (a instanceof Int) {
            if (b instanceof Flt) {
                return Flt(((Int) a).value() - ((Flt) b).value());
            } else if (b instanceof Int) {
                return Int(((Int) a).value() - ((Int) b).value());
            }
        }
        throw new JFLException("Unable to subtract non-numerical values.");
    };
    public static Lambda mul = a -> b -> {
        if (a instanceof Flt) {
            if (b instanceof Flt) {
                return Flt(((Flt) a).value() * ((Flt) b).value());
            } else if (b instanceof Int) {
                return Flt(((Flt) a).value() * ((Int) b).value());
            }
        } else if (a instanceof Int) {
            if (b instanceof Flt) {
                return Flt(((Int) a).value() * ((Flt) b).value());
            } else if (b instanceof Int) {
                return Int(((Int) a).value() * ((Int) b).value());
            }
        }
        throw new JFLException("Unable to multiply non-numerical values.");
    };
    public static Lambda add1 = add.apply(Int(1));
    public static Lambda sub1 = add.apply(Int(-1));

    // List
    public static final Cons EMPTY = new Empty();
    public static Lambda cons = l -> c -> {
        if (c instanceof Cons) {
            return Cons(l, (Cons)c);
        }
        throw new JFLException("Unable to cons to a non-Cons.");
    };
    public static Bool isEmpty(Lambda l) {
        if (l instanceof Cons) {
            return Bool(((Cons) l).isEmpty());
        }
        throw new JFLException("Unable to evaluate isEmpty on a non-list.");
    }
    public static Lambda first(Lambda l) {
        if (l instanceof Cons) {
            return ((Cons) l).value();
        }
        throw new JFLException("Unable to evaluate first on a non-list.");
    }
    public static Lambda rest(Lambda l) {
        if (l instanceof Cons) {
            return ((Cons) l).next();
        }
        throw new JFLException("Unable to evaluate rest on a non-list.");
    }

    // Conditional
    public static boolean cond(Lambda l) {
        if (l instanceof Bool) {
            return ((Bool) l).value();
        }
        throw new JFLException("Unable to evaluate cond statement on a non-bool operation.");
    }

    // Recursion
    private static Lambda selfApply = f -> f.apply(f);
    public static Lambda Fix = f -> selfApply.apply(w -> f.apply(x -> w.apply(w).apply(x)));

    public static Bool isZero(Lambda l) {
        if (l instanceof Int) {
            return Bool(((Int) l).value() == 0L);
        } else if (l instanceof Flt) {
            return Bool(((Flt) l).value() == 0.0);
        }
        return Bool(false);
    }

    // Higher order functions
    public static Lambda foldr = Fix.apply(fold -> f -> b -> l -> cond(isEmpty(l)) ? b : f.apply(first(l)).apply(fold.apply(f).apply(b).apply(rest(l))));

    // Input/Output
    public static Int getInt() {
        if (s.hasNextLong()) {
            return new Int(s.nextLong());
        }
        throw new JFLException("Unable to read Int from System.in.");
    }
    public static Flt getFlt() {
        if (s.hasNextDouble()) {
            return new Flt(s.nextDouble());
        }
        throw new JFLException("Unable to read Flt from System.in.");
    }
    public static Lambda println(Lambda l) {
        System.out.println(l.toString());
        return l;
    }
    public static Lambda print(Lambda l) {
        System.out.print(l.toString());
        return l;
    }
}

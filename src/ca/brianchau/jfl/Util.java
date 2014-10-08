package ca.brianchau.jfl;

import java.util.Scanner;

/**
 * Created by brian on 2014-10-06.
 */
public class Util {

    public static boolean USE_CONS_FOR_LIST = false;

    public static Scanner s = new Scanner(System.in);

    // Static objects
    public static final Bool TRUE = new Bool(true);
    public static final Bool FALSE = new Bool(false);
    public static final Cons EMPTY = new Empty();

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
    public static Cons List(Lambda ... ls) {
        Cons c = EMPTY;
        for (int i = ls.length - 1; i >= 0; --i) {
            c = Cons(ls[i], c);
        }
        return c;
    }

    // Boolean operators
    public static Lambda equal = a -> b -> {
        return a.equals(b) ? TRUE : FALSE;
    };

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
    public static Lambda div = a -> b -> {
        if (isZero(b).value()) {
            throw new JFLException("/ by 0 not allowed.");
        } else if (a instanceof Flt) {
            if (b instanceof Flt) {
                return Flt(((Flt) a).value() / ((Flt) b).value());
            } else if (b instanceof Int) {
                return Flt(((Flt) a).value() / ((Int) b).value());
            }
        } else if (a instanceof Int) {
            if (b instanceof Flt) {
                return Flt(((Int) a).value() / ((Flt) b).value());
            } else if (b instanceof Int) {
                return Int(((Int) a).value() / ((Int) b).value());
            }
        }
        throw new JFLException("Unable to divide non-numerical values.");
    };
    public static Lambda mod = a -> b -> {
        if (isZero(b).value()) {
            throw new JFLException("% by 0 not allowed.");
        } else if (a instanceof Flt) {
            if (b instanceof Flt) {
                return Flt(((Flt) a).value() % ((Flt) b).value());
            } else if (b instanceof Int) {
                return Flt(((Flt) a).value() % ((Int) b).value());
            }
        } else if (a instanceof Int) {
            if (b instanceof Flt) {
                return Flt(((Int) a).value() % ((Flt) b).value());
            } else if (b instanceof Int) {
                return Int(((Int) a).value() % ((Int) b).value());
            }
        }
        throw new JFLException("Unable to modulo non-numeric values.");
    };
    public static Lambda isOdd = l -> {
        if (l instanceof Int) {
            return equal.apply(Int(1)).apply(mod.apply(l).apply(Int(2)));
        }
        throw new JFLException("Unable to determine oddness of non-Int value.");
    };
    public static Lambda isEven = l -> {
        if (l instanceof Int) {
            return equal.apply(Int(0)).apply(mod.apply(l).apply(Int(2)));
        }
        throw new JFLException("Unable to determine oddness of non-Int value.");
    };
    public static Lambda add1 = add.apply(Int(1));
    public static Lambda sub1 = add.apply(Int(-1));

    // List
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
    public static Lambda foldr = Fix.apply(foldr -> f -> b -> l -> cond(isEmpty(l)) ? b : f.apply(first(l)).apply(foldr.apply(f).apply(b).apply(rest(l))));
    public static Lambda foldl = Fix.apply(foldl -> f -> b -> l -> cond(isEmpty(l)) ? b : foldl.apply(f).apply(f.apply(first(l)).apply(b)).apply(rest(l)));
    public static Lambda map = Fix.apply(map -> f -> l -> cond(isEmpty(l)) ? EMPTY : Cons(f.apply(first(l)), (Cons)map.apply(f).apply(rest(l))));
    public static Lambda filter = Fix.apply(filter -> p -> l -> cond(isEmpty(l)) ? EMPTY : cond(p.apply(first(l))) ? Cons(first(l), (Cons)filter.apply(p).apply(rest(l))) : filter.apply(p).apply(rest(l)));

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
        print(l);
        System.out.println();
        return l;
    }
    public static Lambda print(Lambda l) {
        if (!USE_CONS_FOR_LIST && l instanceof Cons) {
            System.out.print("(List");
            Cons c = (Cons)l;
            while (!isEmpty(c).value()) {
                System.out.print(" " + c.value().toString());
                c = c.next();
            }
            System.out.print(")");
        } else {
            System.out.print(l.toString());
        }
        return l;
    }
}

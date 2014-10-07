package test;

import static ca.brianchau.jfl.Util.*;
import ca.brianchau.jfl.*;

import java.util.function.UnaryOperator;

/**
 * Created by brian on 2014-10-06.
 */
public class Runner {
    public static void main(String[] args) {
        Lambda factorial = Fix.eval(f -> n -> (If(isZero(n))) ? Int(1) : mul(n, f.eval(sub(n, Int(1)))));

        Lambda sumListHelper = Fix.eval(f -> l -> a -> (If(isEmpty(l)) ? a : f.eval(rest(l)).eval(add(a, first(l)))));
        Lambda sumList = l -> sumListHelper.eval(l).eval(Int(0));
        Lambda list1 = Cons(Int(1), Cons(Int(2), Cons(Int(3), EMPTY)));

        print(sumList.eval(list1));
        print(factorial.eval(Int(10)));
        print(factorial.eval(getInt()));
    }
}

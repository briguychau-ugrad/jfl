package sample;

import static ca.brianchau.jfl.Util.*;
import ca.brianchau.jfl.*;

/**
 * Created by brian on 2014-10-06.
 */
public class Runner {
    public static void main(String[] args) {
        Lambda factorial = Fix.apply(f -> n -> cond(isZero(n)) ? Int(1) : mul.apply(n).apply(f.apply(sub1.apply(n))));

        Lambda sumListHelper = Fix.apply(f -> l -> a -> cond(isEmpty(l)) ? a : f.apply(rest(l)).apply(add.apply(a).apply(first(l))));
        Lambda sumList = l -> sumListHelper.apply(l).apply(Int(0));
        Lambda list1 = Cons(Int(1), Cons(Int(2), Cons(Int(3), Cons(Int(4), EMPTY))));

        println(foldr.apply(add).apply(Int(0)).apply(list1));
        println(foldr.apply(cons).apply(EMPTY).apply(list1));
        println(foldl.apply(cons).apply(EMPTY).apply(list1));

        Lambda mul2 = mul.apply(Int(2));

        println(map.apply(mul2).apply(list1));

        println(sumList.apply(list1));
        println(factorial.apply(Int(10)));
        println(list1);
    }
}

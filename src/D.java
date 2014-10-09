import static ca.brianchau.jfl.Util.*;

/**
 * Created by brian on 2014-10-08.
 */
public class D {
    public static void main(String[] args) {
        println(
                Lambda(sc -> sr -> ec -> er -> cond(
                        isOdd.apply(add.apply(add.apply(sc).apply(sr)).apply(add.apply(ec).apply(er)))
                ) ? Str("Odd") : Str("Even"))
                        .apply(getInt()).apply(getInt()).apply(getInt()).apply(getInt())
        );
    }
}

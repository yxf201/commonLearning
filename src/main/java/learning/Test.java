package learning;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        double a = 1   *   88   +   2   *   96   +   1   *  69   +   1   *   78   +   2   *   85   +   2   *   81   +   2   *   85   +   2   *   95   +   3   *   91;
        a += 2*94 + 1*83 + 2*73 + 2*90 + 2*86;
        System.out.println(a/25.0); // 86.44

        a = 1   *   88   +   2   *   96   +   1   *   78   +   2   *   85   +   2   *   81   +   2   *   85   +   2   *   95   +   3   *   91;
        a += 2*94 + 1*83 + 2*73 + 2*90 + 2*86;
        System.out.println(a/24.0); // 86.44

        System.out.println(Integer.MIN_VALUE);
        System.out.println(-Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE == -Integer.MIN_VALUE);

        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((long)Integer.MIN_VALUE*Integer.MIN_VALUE);
    }
}


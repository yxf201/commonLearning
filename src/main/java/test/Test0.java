package test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test0 {
    public static void main(String[] args) {
        int a = 1162261467;
        System.out.println(a % (int)Math.pow(3,18));
        int x = (int)Math.pow(3,5);
        int y = (int)Math.pow(3,4);
        System.out.println(x);
        System.out.println(y);
        System.out.println(x%y);
        System.out.println(x/y);
        /** a % b = a - (a/b)*b */
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void t1(){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Map.Entry<Integer,Integer> e = map.higherEntry(1);
        e = map.floorEntry(1);
    }

    @Test
    public void t2(){
        System.out.println(Integer.numberOfLeadingZeros(1231));
    }
}

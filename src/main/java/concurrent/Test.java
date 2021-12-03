package concurrent;

import annotations.GuardedBy;

import java.util.Arrays;

@GuardedBy("a test")
public class Test {
    public static void main(String[] args){
        Integer x = -3;
        for( int i = 0; i < 35; i++ ){
            System.out.println(x);
            System.out.println(Integer.toBinaryString(x));
            x <<= 1;
        }
        System.out.println(-1610612736L+(-1610612736));
        System.out.println(Integer.MAX_VALUE);
    }

    public int divide(int dividend, int divisor) {
        if( dividend == Integer.MIN_VALUE && divisor == -1 ){
            return Integer.MAX_VALUE;
        }

        int a = dividend < 0 ? dividend : -dividend;
        int b = divisor < 0 ? divisor : -divisor;
        int flag = dividend < 0 ? (divisor < 0 ? 1 : -1) : (divisor < 0 ? -1 : 1);

        System.out.println(a);
        System.out.println(b);
        System.out.println(flag);

        int ans = 0;
        while( a <= b ){
            int x = b;
            int res = 1;
            while( (x<<1) >= a ){
                x <<= 1;
                res <<= 1;

                if( x == a ){
                    break;
                }

                System.out.println(x);
                System.out.println(res);
            }
            ans += res;
            a -= x;
        }
        return flag > 0 ? ans : -ans;
    }

    @org.junit.jupiter.api.Test
    public void t1(){
        System.out.println(divide( -2147483648, 1));
    }

    @org.junit.jupiter.api.Test
    public void t2(){
        System.out.println(-1610612736L+(-1610612736));
        System.out.println(Integer.MIN_VALUE);
    }

    @org.junit.jupiter.api.Test
    public void t3(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        System.out.println(sb.toString());
    }

    @org.junit.jupiter.api.Test
    public void t4(){
        System.out.println(Math.pow(9,9));
        //! System.out.println(2999999999);
        for( char c : new char[]{'a'} ){
            switch (c){

            }
        }
    }

    @org.junit.jupiter.api.Test
    public void t5(){
        Object[] o1 = {1,2,3,4.5,6.7,8,9};
        Integer[] o2 = new Integer[7];

        try{
            System.arraycopy(o1, 0, o2, 0, 5);
        }catch(ArrayStoreException e){
            System.out.println("wrong");
        }
        System.out.println(Arrays.toString(o2));

        //! System.arraycopy(new Integer[]{1,2,3}, 0, new Integer[1], 0, 3);

        int[] a = {1,2};
        int[] b = {3,4};
        System.arraycopy(a, 0, b, 0, 2);
        System.out.println(Arrays.toString(b));
    }

}

package test;

import java.util.HashMap;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(2147483647);
        System.out.println(2147483647+1);
        System.out.println(2147483647-1);
        System.out.println(new Test2().integerReplacement(2147483647));
        System.out.println(new Test2().integerReplacement(2147483646));
        System.out.println(Integer.MAX_VALUE);
    }

    public long integerReplacement(long n) {
        map = new HashMap<>();
        return dfs(n);
    }

    private HashMap<Long, Long> map;

    private long dfs(long n){
        if( n < 0 ){
            return Integer.MAX_VALUE;
        }
        if( n == 1 ){
            return 0;
        }
        Long x = map.get(n);
        if( x != null ){
            return x;
        }
        long res = 0;
        if( n % 2 == 0 ){
            res = 1 + dfs(n/2);
        }else{
            res = 1 + Math.min(dfs(n+1), dfs(n-1));
        }
        map.put(n, res);
        //System.out.println(map);
        return res;
    }
}

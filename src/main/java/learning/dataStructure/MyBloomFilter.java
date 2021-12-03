package learning.dataStructure;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

public class MyBloomFilter {
    private static final int DEFAULT_SIZE = 2 << 24;

    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    private BitSet bits = new BitSet(DEFAULT_SIZE);

    private SimpleHash[] funcs = new SimpleHash[SEEDS.length];

    public MyBloomFilter(){
        for( int i = 0; i < SEEDS.length; i++ ){
            funcs[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(Object value){
        for( SimpleHash f : funcs ){
            bits.set(f.hash(value), true);
        }
    }

    public boolean contains(Object value){
        for( SimpleHash f : funcs ){
            if( !bits.get(f.hash(value)) ){
                return false;
            }
        }
        return true;
    }

    private static class SimpleHash{
        private int cap;
        private int seed;

        private SimpleHash(int cap, int seed){
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(Object value){
            int h;
            return (value == null) ? 0 : Math.abs(seed*(cap-1) & ((h = value.hashCode()) ^ (h>>>16)));
        }
    }

    public static void main(String[] args) {
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value1));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value1));

        Integer i1 = 13423;
        Integer i2 = 22131;
        System.out.println(filter.contains(i1));
        System.out.println(filter.contains(i2));
        filter.add(i1);
        filter.add(i2);
        System.out.println(filter.contains(i1));
        System.out.println(filter.contains(i2));
    }

    @Test
    public void t1(){
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500, 0.01
        );

        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }
}

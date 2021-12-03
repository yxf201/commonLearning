package learning.greed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] nodes = new int[n][2];
        for (int i = 0; i < n; i++) {
            nodes[i] = new int[]{capital[i], profits[i]};
        }
        Arrays.sort(nodes, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> -Integer.compare(a[1], b[1]));
        int index = 0;
        for( int i = 0; i < k; i++ ){
            while (index < n && w >= nodes[index][0]) {
                q.add(nodes[index++]);
            }
            if( q.isEmpty() ){
                return w;
            }
            w += q.poll()[1];
        }
        return w;
    }
}

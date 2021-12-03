package learning.leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water-ii/
 * https://leetcode-cn.com/problems/trapping-rain-water-ii/solution/gong-shui-san-xie-jing-dian-dijkstra-yun-13ik/
 * 【宫水三叶】经典 Dijkstra 运用题
 * Dijkstra 变形 + 优先队列（堆）
 * 三叶姐太强了，不愧是NOIP大佬
 * 她不仅给出了思路，还做了一个非常精妙的证明
 * 这道题出的还是不错的
 */
public class TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] vis = new boolean[m][n];

        for (int i = 0; i < n; i++) {
            q.add(new int[]{0, i, heightMap[0][i]});
            q.add(new int[]{m - 1, i, heightMap[m - 1][i]});
            vis[0][i] = true;
            vis[m - 1][i] = true;
        }
        //init 边界
        for (int i = 0; i < m; i++) {
            q.add(new int[]{i, 0, heightMap[i][0]});
            q.add(new int[]{i, n - 1, heightMap[i][n - 1]});
            vis[i][0] = true;
            vis[i][n - 1] = true;
        }

        int ans = 0;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            for (int[] d : ds) {
                int[] next = new int[]{node[0] + d[0], node[1] + d[1], -1};
                if (!check(next, m, n) || vis[next[0]][next[1]]) {
                    continue;
                }
                if (node[2] > heightMap[next[0]][next[1]]) {
                    ans += node[2] - heightMap[next[0]][next[1]];
                }
                next[2] = Math.max(heightMap[next[0]][next[1]], node[2]);
                q.add(next);
                vis[next[0]][next[1]] = true;
            }
        }
        return ans;
    }

    private boolean check(int[] next, int m, int n) {
        return next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n;
    }

    private static int[][] ds = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
}

package learning.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/perfect-rectangle/
 * 391. 完美矩形
 *
 * 题解：https://leetcode-cn.com/problems/perfect-rectangle/solution/gong-shui-san-xie-chang-gui-sao-miao-xia-p4q4/
 *
 * 扫描线
 *
 * 如[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4],[1,1,1,1]] 是有问题的，但能过给的所有测试
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[]{re[0], re[1], re[3], 1};
            rs[idx++] = new int[]{re[2], re[1], re[3], -1};
        }
        Arrays.sort(rs, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        n *= 2;
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n; ) {
            int r = l;
            l1.clear();
            l2.clear();
            // 找到横坐标相同部分
            while (r < n && rs[r][0] == rs[l][0]) {
                r++;
            }
            // 横坐标相同的部分
            for (int i = l; i < r; i++) {
                int[] cur = new int[]{rs[i][1], rs[i][2]};//y1, y2
                List<int[]> list = rs[i][3] == 1 ? l1 : l2;
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    if (cur[0] < prev[1]) return false; // 存在重叠 为啥就return false了？
                    else if (cur[0] == prev[1]) prev[1] = cur[1]; // 首尾相连
                    else list.add(cur);
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) return false;
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1]) continue;
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段
                if (l1.size() + l2.size() != 1) return false;
            }
            l = r;
        }
        return true;
    }

    public boolean myIsRectangleCover(int[][] rectangles) {
        int n = rectangles.length*2;//left and right lines number
        int[][] lines = new int[n][4];
        //(x,y1,y2,flag) 扫描线，一个矩形有left,right两条
        for( int i = 0, id = 0; i < rectangles.length; i++ ){
            lines[id++] = new int[]{rectangles[i][0],rectangles[i][1],rectangles[i][3],-1};
            lines[id++] = new int[]{rectangles[i][2],rectangles[i][1],rectangles[i][3], 1};
        }

        Arrays.sort(lines, (a,b)->{
            if( a[0] != b[0] ){
                return Integer.compare(a[0], b[0]);
            }
            else if( a[1] != b[1] ){
                return Integer.compare(a[1], b[1]);
            }
            else{// if( a[2] != b[2] )
                return Integer.compare(a[2], b[2]);
            }
        });

        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        //当前的x坐标上，将为left和为right的扫描线分开

        for( int l = 0; l < n; ){
            int r = l;
            left.clear(); right.clear();

            while( r < n && lines[r][0] == lines[l][0] ){
                r++;
            }

            //x坐标相同的扫描线
            for( int i = l; i < r; i++ ){
                List<int[]> list = lines[i][3] == 1 ? right : left;
                if( list.isEmpty() ){
                    list.add(lines[i]);
                }else{
                    int[] pre = list.get(list.size()-1);
                    if( lines[i][1] < pre[2] ){
                        return false;
                    }else if( lines[i][1] == pre[2] ){
                        pre[2] = lines[i][2];
                    }else{
                        list.add(lines[i]);
                        //return false;
                    }
                }
            }

            if( l > 0 && r < n ){
                if( left.size() != right.size() ){
                    return false;
                }
                for( int i = 0; i < left.size(); i++ ){
                    if( left.get(i)[1] != right.get(i)[1] || left.get(i)[2] != right.get(i)[2] ){
                        return false;
                    }
                }
            }else{
                if( left.size() + right.size() != 1 ){
                    return false;
                }
            }

            l = r;
        }
        return true;
    }

}

/**
 * [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4],[1,1,1,1]]
 * [[0,0,3,3],[1,1,2,3],[0,0,2,2]]
 * [[0,0,3,3],[3,3,3,5]]
 */

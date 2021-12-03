package learning.leetcode;

import java.util.Random;

/**
 * 384. 打乱数组
 * https://leetcode-cn.com/problems/shuffle-an-array/
 */
public class ShuffleAnArray {
    private int[] nums;
    private int[] original;
    private Random random;

    /**
     * O(n)
     * @param nums
     */
    public ShuffleAnArray(int[] nums) {
        this.random = new Random();
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    /**
     * O(n)
     * @return
     */
    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, nums.length);
        return this.nums;
    }

    /**
     * O(n)
     * 原地打乱
     *
     * 循环 n 次，在第 i 次循环中（0 <= i < n）：
     *  在 [0, n-i) 中随机抽取一个下标 j；
     *  将第 n-i-1 个元素与第 j 个元素交换。
     *
     *  即，在第i次循环中，数组靠后的i个元素是已被打乱的部分
     *
     * @return
     */
    public int[] shuffle() {
        int n = nums.length;
        for( int i = 0; i < n; i++ ){
            int j = random.nextInt(n-i);
            swap(nums, n-i-1, j);
        }
        return this.nums;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

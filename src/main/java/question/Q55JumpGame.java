package question;

import java.util.Arrays;

/**
 * Created by ziheng on 2020/7/29.
 */
public class Q55JumpGame {
    /**
     * @Description: 55. 跳跃游戏
     *
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     *
     * 示例 1:
     *
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     * 示例 2:
     *
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/29 上午9:29
     * @param
     * @return boolean
     */
    public boolean canJump(int[] nums) {
        // 遍历，看每次能到达的最远位置
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            // 之前的最远的距离跳不到当前节点
            if (farest < i) return false;
            // 更新最远距离
            if (i + nums[i] > farest) {
                farest = i + nums[i];
            }
        }

        return true;
    }

    public boolean canJumpOpt(int[] nums) {
        int n = nums.length;
        int rightmost = 0;

        for (int i = 0; i < n; i++) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int curMaxIdx = 0;
        int i = 0;
        do {
            if (i + nums[i] >= n - 1) {
                return true;
            }
            // 更新最远能达到的范围
            curMaxIdx = Math.max(curMaxIdx, i + nums[i]);
            i++;
        } while (i <= curMaxIdx);
        return false;
    }
}

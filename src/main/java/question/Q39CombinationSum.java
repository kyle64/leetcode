package question;

import java.util.*;

/**
 * Created by ziheng on 2020/7/24.
 */
public class Q39CombinationSum {
    /**
     * @Description: 39. 组合总和
     *
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2：
     *
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *  
     *
     * 提示：
     *
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/24 下午2:31
     * @param candidates
     * @param target
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */

    private static final List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return result;

        // 排序
        Arrays.sort(candidates);
        // 递归尝试添加一个数
        Deque<Integer> deque = new ArrayDeque<>();
        combinationSumHelper(candidates, 0, deque, target);

        return result;
    }

    private static void combinationSumHelper(int[] candidates, int index, Deque<Integer> deque, int currentTarget) {
        // 如果下一个target是0，说明当前的数字和已经是目标了
        if (currentTarget == 0) {
            result.add(new ArrayList<>(deque));
            return;
        } else if (currentTarget < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 在数组有序的前提下，剪枝
            if (currentTarget - candidate < 0) break;
            deque.add(candidate);
            combinationSumHelper(candidates, i, deque, currentTarget - candidate);
            // pop，回溯
            deque.pollLast();
        }
    }

    final List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSumDfs(int[] candidates, int target) {
        dfs(candidates, 0, target, new ArrayDeque<>());
        return res;
    }

    private void dfs(int[] candidates, int index, int target, Deque<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            // 都是正整数，不考虑负数情况，剪枝
            return;
        }

        // 只考虑当前index之后的元素, 避免重复
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            path.addLast(candidate);
            // 可重复取，不传i + 1
            dfs(candidates, i, target - candidate, path);
            path.removeLast();
        }
    }

    private void dfsSelection(int[] candidates, int index, int target, Deque<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0 || index == candidates.length) {
            // 都是正整数，不考虑负数情况
            // 已完遍历数组
            return;
        }

        // 考虑使用当前index的元素和不使用两种情况

        // 选择使用当前的candidate
        int candidate = candidates[index];
        if (target - candidate >= 0) {
            path.addLast(candidate);
            // 可重复取，不传i + 1
            dfsSelection(candidates, index, target - candidate, path);
            path.removeLast();
        }
        // 不选择使用当前的candidate，直接考虑下一个元素
        dfsSelection(candidates, index + 1, target, path);
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        combinationSum(candidates, target);
    }
}

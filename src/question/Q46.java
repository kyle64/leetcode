package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q46 {
    /**
     * @Description: 46. 全排列
     *
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 下午1:11
     * @param
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int depth = 0; // 树的深度，也是已选择的数的数量
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // dfs用栈来保存当前使用的数字

        dfs(nums, depth, result, stack);
        return result;
    }

    private void dfs(int[] nums, int depth, List<List<Integer>> result, ArrayDeque<Integer> stack) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 使用过就下一个
            if (stack.contains(nums[i])) continue;

            stack.addLast(nums[i]);
            dfs(nums, depth + 1, result, stack);
            // 回溯
            stack.removeLast();
        }
    }


    public List<List<Integer>> permuteBFS(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        bfs(nums, result);
        return result;
    }

    public void bfs(int[] nums, List<List<Integer>> result) {
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<>());

        while (!queue.isEmpty()) {
            List temp = queue.poll();
            if (temp.size() < nums.length) {
                for (int num : nums) {
                    if (!temp.contains(num)) {
                        queue.add(new ArrayList<Integer>(temp) {{
                            add(num);
                        }});
                    }
                }
            } else {
                result.add(temp);
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 2 ,3, 4};
        Q46 q46 = new Q46();
        List<List<Integer>> list = q46.permute(input);
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("\n");
        }
    }
}

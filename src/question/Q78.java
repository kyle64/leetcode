package question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q78 {
    /**
     * @Description: 78. 子集
     *
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 上午2:56
     * @param
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> list = new ArrayList<>();
            for (List<Integer> integers : result) {
                if (!integers.contains(num)) {
                    list.add(new ArrayList<Integer>(integers) {
                        {add(num);}
                    });
                }
            }

            for (List<Integer> integers : list) {
                result.add(integers);
            }
        }

        return result;
    }

    public void subsetsRecursion(int[] nums, int i, List<List<Integer>> result) {
        if (i >= nums.length) return;
        int size = result.size();
        for (int j = 0; j < size; j++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[i]);
            result.add(temp);
        }

        subsetsRecursion(nums, i + 1, result);
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    // first是取前多少个数
    // dfs回溯思想
    private void backtrack(int first, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = first; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 2 ,3};
        Q78 q78 = new Q78();
        List<List<Integer>> list = q78.subsets1(input);
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("\n");
        }

    }

}

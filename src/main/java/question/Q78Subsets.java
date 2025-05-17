package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q78Subsets {
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

    List<List<Integer>> res;
    public List<List<Integer>> subsetsDfs(int[] nums) {
        res = new ArrayList<>();
        // 只能取指定index之后的元素
        dfs(nums, 0, new ArrayDeque<>());
        return res;
    }

    private void dfs(int[] nums, int index, Deque<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(nums, i + 1, path);
            path.removeLast();
        }
    }

    public List<List<Integer>> subsetsIterator(int[] nums) {
        res = new ArrayList<>();
        // 迭代，每遍历到新元素，往res中的已有的子集的复制中添加新元素
        // init
        res.add(new ArrayList<>());

        for (int num : nums) {
            // 记录当前res大小
            int curLen = res.size();
            // 不用foreach，因为res有新元素添加
            for (int i = 0; i < curLen; i++) {
                // 复制当前子集
                List<Integer> newSubset = new ArrayList<>(res.get(i));
                // 添加当前数字
                newSubset.add(num);
                res.add(newSubset);
            }
        }
        return res;
    }

    public List<List<Integer>> subsetsEnum(int[] nums) {
        // 子集总数一定是 2^n, n为nums.length
        // 用二进制的1、0来表示选中、未选中的状态，正好对应[0, 2^n - 1]个数
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>(1 << n);

        // 枚举全集 U 的所有子集
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            // 遍历每一位的选中状态
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            res.add(subset);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {1, 2 ,3};
        Q78Subsets q78Subsets = new Q78Subsets();
        List<List<Integer>> list = q78Subsets.subsets1(input);
        for (List<Integer> integers : list) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("\n");
        }

    }

}

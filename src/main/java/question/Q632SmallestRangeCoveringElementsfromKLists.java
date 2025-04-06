package question;

import java.util.*;

/**
 * Created by ziheng on 2020/8/1.
 */
public class Q632SmallestRangeCoveringElementsfromKLists {
    /**
     * @Description: 632. 最小区间
     *
     * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
     *
     * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
     *
     * 示例 1:
     *
     * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
     * 输出: [20,24]
     * 解释:
     * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
     * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
     * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
     * 注意:
     *
     * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。
     * 1 <= k <= 3500
     * -105 <= 元素的值 <= 105
     * 对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/1 上午12:47
     * @param
     * @return int[]
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        // 优化，小顶堆维护k个指针指向的最小值
        // 每个list中元素的指针，下标是list编号
        int[] pointers = new int[nums.size()];
        int minIndex = 0;
        int[] result = new int[] {0, Integer.MAX_VALUE};
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // 保存list中的pointer的优先级队列，堆顶指向k个指针中最小的元素的下标
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                return nums.get(index1).get(pointers[index1]) - nums.get(index2).get(pointers[index2]);
            }
        });

        // 初始化
        for (int i = 0; i < pointers.length; i++) {
            pq.offer(i);
            // pointers[i]都等于0
            max = Math.max(nums.get(i).get(0), max);
        }

        while (true) {
            // 取出最小的元素
            minIndex = pq.poll();
            // 取出最小值
            min = nums.get(minIndex).get(pointers[minIndex]);
            if (max - min < result[1] - result[0]) {
                // 更新result
                result[0] = min;
                result[1] = max;
            }

            // 右移最小值的那个队列的指针
            pointers[minIndex]++;
            // 遍历完毕，结束
            if (pointers[minIndex] == nums.get(minIndex).size()) break;
            // 将新的minIndex入队列
            pq.offer(minIndex);
            // 是否需要更新max
            max = Math.max(max, nums.get(minIndex).get(pointers[minIndex]));
        }

        return result;
    }

    public static int[] smallestRange1(List<List<Integer>> nums) {
        // 每个list中元素的指针，下标是list编号
        int[] pointers = new int[nums.size()];
        int minIndex = 0;
        int[] result = new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE};

        while (pointers[minIndex] < nums.get(minIndex).size()) {
            // 重置min，max
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

            // 每次移动值最小的list的指针
            for (int i = 0; i < pointers.length; i++) {
                int current = nums.get(i).get(pointers[i]);
                // 当前区间下界
                if (current < min) {
                    min = current;
                    minIndex = i;
                }
                // 当前区间上界
                if (current > max) {
                    max = current;
                }
            }

            if (max - min < (long)result[1] - (long)result[0]) {
                // 更新result
                result[0] = min;
                result[1] = max;
            }

            pointers[minIndex]++;
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(4,10,15,24,26);
        List<Integer> list2 = Arrays.asList(0,9,12,20);
        List<Integer> list3 = Arrays.asList(5,18,22,30);
        nums.add(list1);
        nums.add(list2);
        nums.add(list3);

        int[] res = smallestRange(nums);
        for (int r : res) {
            System.out.print(r + " ");
        }
    }
}

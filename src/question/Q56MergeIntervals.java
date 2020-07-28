package question;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by ziheng on 2020/7/27.
 */
public class Q56MergeIntervals {
    /**
     * @Description: 56. 合并区间
     *
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/27 下午11:50
     * @param
     * @return int[][]
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        List<int[]> resultList = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> {
                    return o1[0] - o2[0];
                }
        );
        for (int[] interval : intervals) {
            pq.offer(interval);
        }

        int[] current = pq.poll();
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            if (current[1] >= next[0]) {
                // 取两个区间上界中大的那个，以防[1,4], [2,3]这种情况出现
                current[1] = Math.max(current[1], next[1]);
            } else {
                resultList.add(current);
                current = next;
            }
        }

        resultList.add(current);

        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        int[][] output = merge(input);
        for (int[] r : output) {
            System.out.println(r[0] + ", " + r[1]);
        }
    }
}

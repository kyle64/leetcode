package question;

import java.util.ArrayList;
import java.util.Arrays;
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

    public int[][] mergePQ(int[][] intervals) {
        List<int[]> resList = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                // 左边界越小越优先，相同左边界时数组越长越优先
                (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]
        );

        // init
        for (int[] interval : intervals) {
            pq.offer(interval);
        }
        resList.add(pq.poll());

        while (!pq.isEmpty()) {
            int[] unMerged = pq.poll();
            int[] lastMerged = resList.get(resList.size() - 1);
            if (unMerged[0] > lastMerged[1]) {
                resList.add(unMerged);
            } else if (unMerged[1] > lastMerged[1]) {
                lastMerged[1] = unMerged[1];
            }
        }

        return resList.toArray(new int[][]{});
    }

    public int[][] mergeArray(int[][] intervals) {
        List<int[]> resList = new ArrayList<>();
        Arrays.sort(intervals,
                // 左边界越小越优先，相同左边界时数组越长越优先
                (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        resList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] lastRes = resList.get(resList.size() - 1);
            if (lastRes[1] < current[0]) {
                resList.add(current);
            } else {
                lastRes[1] = Math.max(lastRes[1], current[1]);
            }
        }

        return resList.toArray(new int[resList.size()][]);
    }

    public static void main(String[] args) {
        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        int[][] output = merge(input);
        for (int[] r : output) {
            System.out.println(r[0] + ", " + r[1]);
        }
    }
}

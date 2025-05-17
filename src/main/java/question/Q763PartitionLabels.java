/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuziheng
 * @description
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 *
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 *
 * 返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 示例 2：
 *
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 *
 * @date 2025/5/14 14:20
 **/
public class Q763PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // 本质是合并区间
        // 保存每个字母最后一次出现的下标
        List<Integer> res = new ArrayList<>();
        char[] sArray = s.toCharArray();
        int[] last = new int[26];
        for (int i = 0; i < sArray.length; i++) {
            last[sArray[i] - 'a'] = i;
        }

        int start = 0;
        int end = 0;
        // 再次遍历，查看下标
        for (int i = 0; i < sArray.length; i++) {
            // 记录比较当前字母最后一次出现的下标和当前分隔的右边界
            end = Math.max(end, last[sArray[i] - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

    public List<Integer> partitionLabelsMergeIntervals(String s) {
        // 本质是合并区间
        List<Integer> res = new ArrayList<>();
        char[] sArray = s.toCharArray();
        // 记录每个字母第一次出现的下标和最后一次出现的下标
        Map<Character, int[]> intervalMap = new HashMap<>();
        for (int i = 0; i < sArray.length; i++) {
            int[] interval = intervalMap.getOrDefault(sArray[i], new int[] {i, i});
            interval[1] = i;
            intervalMap.put(sArray[i], interval);
        }
        List<int[]> intervals = new ArrayList<>(intervalMap.values());
        // 问题转变为合并intervals中的区间，然后求区间长度
        intervals.sort((o1, o2) -> o1[0] - o2[0]);
        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            int[] merged = mergedList.get(mergedList.size() - 1);
            int[] interval = intervals.get(i);
            if (interval[0] < merged[1]) {
                merged[1] = Math.max(merged[1], interval[1]);
            } else {
                mergedList.add(interval);
            }
        }
        for (int[] interval : mergedList) {
            res.add(interval[1] - interval[0] + 1);
        }
        return res;
    }
}

/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author wuziheng
 * @description
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * @date 2025/5/11 21:04
 **/
public class Q739DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Arrays.fill(res, 0);
        // 存储下标的单调栈，从栈底元素到栈顶元素单调递减，每次压栈前，保证温度更小的元素被移除
        Deque<Integer> stk = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && temperatures[i] > temperatures[stk.peek()]) {
                // 有升序
                int idx = stk.pop();
                res[idx] = i - idx;
            }
            stk.push(i);
        }

        return res;
    }
}

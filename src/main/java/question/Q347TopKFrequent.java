/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wuziheng
 * @description
 * @date 2025/5/13 21:31
 **/
public class Q347TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                (o1, o2) -> o2[1] - o1[1]
        );
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            queue.offer(new int[] {entry.getKey(), entry.getValue()});
        }

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
    }
}

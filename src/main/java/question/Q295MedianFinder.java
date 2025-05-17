/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.PriorityQueue;

/**
 * @author wuziheng
 * @description
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 *
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 *
 * MedianFinder() 初始化 MedianFinder 对象。
 *
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 *
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 *
 * 示例 1：
 *
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 *
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 提示:
 *
 * -105 <= num <= 105
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 104 次调用 addNum 和 findMedian
 *
 * @date 2025/5/13 23:14
 **/
public class Q295MedianFinder {
    public static class MedianFinder {
        // 两个堆，一个大顶堆、一个小顶堆
        // 优先往maxHeap中放, 始终维持maxHeap.size() = minHeap.size() or maxHeap.size() = minHeap.size() + 1

        // nums[mid, n]
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // nums[0, mid]
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (o1, o2) -> o2 - o1
        );


        public MedianFinder() {
            maxHeap.offer(Integer.MIN_VALUE / 2);
            minHeap.offer(Integer.MAX_VALUE / 2);
        }

        public void addNum(int num) {
            // 偶数
            if (maxHeap.size() == minHeap.size()) {
                // mid1 <= num <= mid2
                if (num >= maxHeap.peek() && num <= minHeap.peek()) {
                    maxHeap.offer(num);
                } else if (num < maxHeap.peek()) {
                    // num < mid1 <= mid2
                    maxHeap.offer(num);
                } else {
                    // mid1 <= mid2 < num
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(num);
                }
            } else {
                // 奇数
                if (num >= maxHeap.peek()) {
                    minHeap.offer(num);
                } else {
                    // num < mid1 <= mid2
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                }
            }
        }

        public void addNumSimplify(int num) {
            // 简化写法，但耗时其实增加了
            // 偶数
            if (maxHeap.size() == minHeap.size()) {
                // 无论如何要往maxHeap中放元素
                minHeap.offer(num);
                maxHeap.offer(minHeap.poll());
            } else {
                // 奇数
                // 无论如何要往maxHeap中放元素
                maxHeap.offer(num);
                minHeap.offer(maxHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0d;
            } else {
                return maxHeap.peek();
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}

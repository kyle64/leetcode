package question;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q121MaxProfit {
    /**
     * @Description: 121. 买卖股票的最佳时机
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意：你不能在买入股票前卖出股票。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/10 下午4:45
     * @param
     * @return int
     */
    public static int maxProfitSlidingWindow(int[] prices) {
        int left = 0, right = 0;
        int profit = 0;

        // sliding window
        while (right < prices.length) {
            // 如果有更小的买入位置left，更换left的位置
            if (prices[left] > prices[right]) {
                left = right;
            }
            // 每次right右移的时候都比较记录中保存的maxProfit和右移后right和left的差值
            profit = Math.max(profit, prices[right] - prices[left]);

            right++;
        }

        return profit;
    }

    public int maxProfit(int[] prices) {
        // GreedyAlgorithm贪心
        // 遍历时寻找最低的买入价，和最高的差价
        int res = 0;
        int n = prices.length;
        // 当前的最低买入价
        int curMin = Integer.MAX_VALUE >> 1;

        // 记录在每天能买入的最低价
        for (int i = 0; i < n; i++) {
            res = Math.max(res, prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = new int[]{2,1,4};
        System.out.println(new Q121MaxProfit().maxProfit(input));
    }
}

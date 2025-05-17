/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author wuziheng
 * @description 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * @date 2025/5/14 21:55
 **/
public class Q322CoinChange {

    public int coinChange(int[] coins, int amount) {
        // dp[i]表示凑成i所需的最少硬币数
        // dp[i] = min(dp[i-j]) + 1 for j in coins
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // coin硬币的值 <= amount值时寻找当前的最小值
                if (coin <= i) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChangeDP1(int[] coins, int amount) {
        // dp[i]表示凑成i所需的最少硬币数
        // dp[i] = min(dp[i-j]) + 1 for j in coins
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int minn = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    if (dp[i - coin] == -1) {
                        continue;
                    }
                    minn = Math.min(dp[i - coin], minn);
                }
            }
            dp[i] = minn == Integer.MAX_VALUE ? -1 : minn + 1;
        }

        return dp[amount];
    }

    int[] memo;
    public int coinChangeMemo(int[] coins, int amount) {
        // 回溯 + 记忆化递归
        if (amount < 1) {
            return 0;
        }
        memo = new int[amount + 1];
        backtrack(coins, amount);
        return memo[amount];
    }

    private int backtrack(int[] coins, int amount) {
        // 越界，这种组合不行
        if (amount < 0) {
            return -1;
        }
        // 找到一种解法
        if (amount == 0) {
            return 0;
        }

        if (memo[amount] != 0) {
            return memo[amount];
        }

        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = backtrack(coins, amount - coin);
            if (res >= 0 && res < count) {
                count = res + 1; // 算上当前扣除使用的coin
            }
        }
        memo[amount] = count == Integer.MAX_VALUE ? -1 : count;
        return memo[amount];
    }

    int res;
    public int coinChangeBackTrack(int[] coins, int amount) {
        // 回溯, 但超时
        if (amount < 1) {
            return 0;
        }
        res = Integer.MAX_VALUE;
        backtrack(coins, amount, new ArrayDeque<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void backtrack(int[] coins, int amount, Deque<Integer> path) {
        // 越界，这种组合不行
        if (amount < 0) {
            return;
        }
        // 找到一种解法
        if (amount == 0) {
            res = Math.min(res, path.size());
            return;
        }

        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            path.add(coin);
            backtrack(coins, amount - coin, path);
            path.pollLast();
        }
    }

    public int coinChangeBackTrack1(int[] coins, int amount) {
        // 回溯, 但超时
        if (amount < 1) {
            return 0;
        }
        res = Integer.MAX_VALUE;
        backtrack1(coins, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void backtrack1(int[] coins, int amount, int cnt) {
        // 越界，这种组合不行
        if (amount < 0) {
            return;
        }
        // 找到一种解法
        if (amount == 0) {
            res = Math.min(res, cnt);
            return;
        }

        for (int coin : coins) {
            backtrack1(coins, amount - coin, cnt + 1);
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 30;

        System.out.println(new Q322CoinChange().coinChangeMemo(coins, amount));
    }
}

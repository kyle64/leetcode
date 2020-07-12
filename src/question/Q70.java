package question;

/**
 * Created by ziheng on 2020/1/14.
 */
public class Q70 {
    /**
     * @Description: 70. 爬楼梯
     *
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/10 下午11:09
     * @param
     * @return int
     */
    public static int climbStairs(int n) {
        // 当大于等于2时，每次都有两种前置的可能: n-1 和 n-2，由于最后跳的级数不一样，因此n阶的跳法等于n-1和n-2阶跳法之和
        int[] stepsArray = new int[n + 1];

        if (n == 1) return 1;

        stepsArray[0] = 1;
        stepsArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            stepsArray[i] = stepsArray[i-2] + stepsArray[i-1];
        }

        return stepsArray[n];

    }

    // 官方的矩阵方法，处理f(x+1) = f(x) + f(x-1)
    public int climbStairsMatrix(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(9));
    }
}

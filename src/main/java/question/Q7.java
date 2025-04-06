package question;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q7 {
    /**
     * @Description: 7. 整数反转
     *
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * 注意:
     *
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 上午2:28
     * @param
     * @return int
     */
    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int remainder = x % 10;
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && remainder > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && remainder < Integer.MIN_VALUE % 10)) {
                return 0;
            }

            x /= 10;
            res = res * 10 + remainder;
        }

        return res;
    }

    public int reverse1(int x) {
        int signal = x < 0 ? -1 : 1;
        int num = x * signal;
        long res = 0;

        while (num > 0) {
            res = (num % 10) + res * 10;
            if ((signal > 0 && res > Integer.MAX_VALUE) || (signal < 0 && res > signal * (long) Integer.MIN_VALUE)) {
                return 0;
            }
            num /= 10;
        }

        return (int) (signal * res);
    }
}

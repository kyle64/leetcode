package question;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q231 {
    /**
     * @Description: 231. 2的幂
     *
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     *
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     *
     * 输入: 218
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/power-of-two
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/10 下午10:34
     * @param
     * @return boolean
     */
    public boolean isPowerOfTwo(int n) {
        // 2的幂-1之后的二进制数每一位都是1，而2的幂是10000000一共n个0的形式，AND位运算为0
        return n >0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo1(int n) {
        // 获取二进制中最右边的 1
        // x & (-x) 将保留最右边的 1。并将其他的位设置为 0。
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;
    }
}

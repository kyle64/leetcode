package question;

/**
 * Created by ziheng on 2020/7/15.
 */
public class Q43multiply {
    /**
     * @Description: 43. 字符串相乘
     *
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     *
     * 示例 1:
     *
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 示例 2:
     *
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     * 说明：
     *
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/15 下午3:56
     * @param num1
     * @param num2
     * @return java.lang.String
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        // 乘积res应该是一个长度为num1.length() + num2.length()的数组
        // num1[i] * num2[j]的结果应该写在res[i + j]和res[i+j+1]
        int[] res = new int[num1.length() + num2.length()];
        int i1,i2;

        for (int i = num1.length() - 1; i >= 0 ; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                i1 = num1.charAt(i) - '0';
                i2 = num2.charAt(j) - '0';
                // 两个个位数乘积
                int product = i1 * i2;
                // 加上原本存在res[i + j + 1]位置的数
                int sum = res[i + j + 1] + product;
                // 更新res[i + j]，可能会超过10，但是可以交给前一位去处理
                res[i + j] += sum / 10;
                // 更新余数到res[i + j]
                res[i + j + 1] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }

        return sb.toString();
    }
}

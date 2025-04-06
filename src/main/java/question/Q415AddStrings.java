package question;

/**
 * Created by ziheng on 2020/8/3.
 */
public class Q415AddStrings {
    /**
     * @Description: 415. 字符串相加
     *
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 注意：
     *
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/3 上午1:55
     * @param num1
     * @param num2
     * @return java.lang.String
     */
    public String addStrings(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 == 0) return num2;
        if (len2 == 0) return num1;

        int[] sumArray = new int[Math.max(len1, len2) + 1];
        int ptr1 = len1 - 1, ptr2 = len2 - 1;
        int curr = sumArray.length - 1;

        int sum = 0, carrier = 0;
        while (ptr1 >= 0 && ptr2 >= 0) {
            sum = (num1.charAt(ptr1) - '0') + (num2.charAt(ptr2) - '0') + carrier;
            sumArray[curr] = sum % 10;
            carrier = sum / 10;

            ptr1--;
            ptr2--;
            curr--;
        }

        while (ptr1 >= 0) {
            sum = (num1.charAt(ptr1--) - '0') + carrier;
            sumArray[curr--] = sum % 10;
            carrier = sum / 10;
        }

        while (ptr2 >= 0) {
            sum = (num2.charAt(ptr2--) - '0') + carrier;
            sumArray[curr--] = sum % 10;
            carrier = sum / 10;
        }

        if (carrier == 1) {
            sumArray[0] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sumArray.length; i++) {
            if (i == 0 && sumArray[i] == 0) continue;
            sb.append(sumArray[i]);
        }

        return sb.toString();
    }
}

package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ziheng on 2020/8/9.
 */
public class Q93RestoreIPAddresses {
    /**
     * @Description: 93. 复原IP地址
     *
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     *
     * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
     *
     *  
     *
     * 示例:
     *
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/8/10 上午9:28
     * @param
     * @return
     */
    static List<String> result = new ArrayList<>();
    // 回溯算法
    public static List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, stack, 0);
        return result;
    }

    public static void dfs(String s, int start, Deque<String> stack, int segCount) {
        if (start == s.length()) {
            if (segCount == 4) {
                result.add(String.join(".", stack));
                }
            return;
        }

        // 长度超出或长度不够，提前剪枝
        if (s.length() - start < (4 - segCount) || s.length() - start > 3 * (4 - segCount)) {
            return;
        }


        for (int i = start; i < start + 3; i++) {
            if (i >= s.length()) {
                break;
            }

            int segment = validSegment(s, start, i);
            if (segment != -1) {
                stack.addLast(String.valueOf(segment));
                dfs(s,  i + 1, stack, segCount + 1);
                stack.removeLast();
            }
        }
    }

    private static int validSegment(String s, int left, int right) {
        int length = right - left + 1;
        // 大于一位时不可以0开头
        if (length > 1 && s.charAt(left) == '0') {
            return -1;
        }

        // 转成 int 类型
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }

        if (res > 255) {
            return -1;
        }

        return res;
    }

    public static void main(String[] args) {
        String input = "25525511135";
        List<String> result = restoreIpAddresses(input);
        for (String s : result) {
            System.out.println(s);
        }
    }
}

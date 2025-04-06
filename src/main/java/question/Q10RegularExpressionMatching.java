package question;

/**
 * Created by ziheng on 2020/7/21.
 */
public class Q10RegularExpressionMatching {
    /**
     * @Description: 10. 正则表达式匹配
     *
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     *
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     *
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     *
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/21 上午10:21
     * @param s
     * @param p
     * @return boolean
     */
    public static boolean isMatch(String s, String p) {
        // 以一个例子详解动态规划转移方程：
        //S = abbbbc
        //P = ab*d*c
        //1. 当 i, j 指向的字符均为字母（或 '.' 可以看成一个特殊的字母）时，
        //   只需判断对应位置的字符即可，
        //   若相等，只需判断 i,j 之前的字符串是否匹配即可，转化为子问题 f[i-1][j-1].
        //   若不等，则当前的 i,j 肯定不能匹配，为 false.
        //
        //       f[i-1][j-1]   i
        //            |        |
        //   S [a  b  b  b  b][c]
        //
        //   P [a  b  *  d  *][c]
        //                     |
        //                     j
        //
        //
        //2. 如果当前 j 指向的字符为 '*'，则不妨把类似 'a*', 'b*' 等的当成整体看待。
        //   看下面的例子
        //
        //            i
        //            |
        //   S  a  b [b] b  b  c
        //
        //   P  a [b  *] d  *  c
        //            |
        //            j
        //
        //   注意到当 'b*' 匹配完 'b' 之后，它仍然可以继续发挥作用。
        //   因此可以只把 i 前移一位，而不丢弃 'b*', 转化为子问题 f[i-1][j]:
        //
        //         i
        //         | <--
        //   S  a [b] b  b  b  c
        //
        //   P  a [b  *] d  *  c
        //            |
        //            j
        //
        //   另外，也可以选择让 'b*' 不再进行匹配，把 'b*' 丢弃。
        //   转化为子问题 f[i][j-2]:
        //
        //            i
        //            |
        //   S  a  b [b] b  b  c
        //
        //   P [a] b  *  d  *  c
        //      |
        //      j <--
        //
        //3. 冗余的状态转移不会影响答案，
        //   因为当 j 指向 'b*' 中的 'b' 时, 这个状态对于答案是没有用的,
        //   原因参见评论区 稳中求胜 的解释, 当 j 指向 '*' 时,
        //   dp[i][j]只与dp[i][j-2]有关, 跳过了 dp[i][j-1].

        // p为空串，s不为空串，肯定不匹配
        // s为空串，但p不为空串，要想匹配，只可能是右端是星号，干掉一个字符后p变为空串。如果右端不是星号，不匹配
        // s、p都为空串，肯定匹配
        //
        //作者：hyj8
        //链接：https://leetcode-cn.com/problems/regular-expression-matching/solution/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        // dp[i][j]表示s的前i个是否被p的前j个匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        // 初始化p = ""
//        for (int i = 1; i < s.length(); i++) {
//            dp[i][0] = true;
//        }
        // 初始化s = ""
        for (int j = 2; j < p.length(); j++) {
            // 当s = "", p中任何的{x*}形式的子串都可以变成""，因此有如果p[j - 1] = *，那么dp[j] = dp[j - 2]
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                // dp[i][j]表示s的前i个是否被p的前j个匹配，因此比较的是s[i-1]和p[j-1]
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    // p[j] = s[i]或者p[j]='.', 当前位一定能匹配，看dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // p[j] = '*'的情况
                    // p的第一个数不为'*'，进入这个条件j至少为2，因此j-2不会越界

                    // 如果p[j-1,j], 即{x*}重复0次，则dp[i][j] = dp[i][j - 2]
                    // 如果p[j-1,j], 即{x*}重复1次，则dp[i][j] = dp[i][j - 1]
                    // 如果p[j-1,j], 即{x*}重复多次，s[i] = p[j - 1] = x的情况下，
                    // 我们的假设是x重复出现了多次，s[0,i]=###x, p[0,j]=###x*，如果s[0,i-1]=###匹配###x*为true，那么s[0,i]也应该匹配
                    // 换言之，就是让扔掉s[i]，用p[0,j]去匹配s[0,i-1]的结果，即dp[i][j] = dp[i - 1][j]

                    // 这里第二种情况，a*匹配一个a，其实已经包括在另外两种状态转移条件里面了
                    // 如果dp[i][j - 1]分别用i=i-1和j=j-1代入，即dp[i-1][j-2]，而这个状态已经由dp[i-1][j]检查重复0次的时候检查了
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        // s[i-1,i]="aa",p="a*" or s[i-1,i]="ba",p="a*" or p[j-2,0]="aa*",s[i]="a"
                        // 本质上是两种形式：s[0,i]="###a",p[j-1,j]="a*"和p[j-2,0]="#a*",s[i]="a"
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    } else {
                        // s[i] != p[j-1]，跳过这个匹配条件
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }
}

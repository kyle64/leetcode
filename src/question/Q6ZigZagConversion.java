package question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/7/22.
 */
public class Q6ZigZagConversion {
    /**
     * @Description: 6. Z 字形变换
     *
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/22 下午5:12
     * @param s
     * @param numRows
     * @return java.lang.String
     */


    // 官方，按行排序
    // 我们可以使用 \text{min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
    // 从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
    // 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        // 初始化结果数组
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        // 当前行
        int curRow = 0;
        // 当前填写方向
        boolean goingDown = false;

        // 遍历将字符append到结果数组中
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 如果到头了或者到底了就改变方向
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            // 移动当前行指针
            curRow += goingDown ? 1 : -1;
        }

        // 输出最终字符串
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }


    // 官方2，找到每个Z中相同的位置的数，遍历，填写
    public String convert0(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        // 一个Z的长度
        int cycleLen = 2 * numRows - 2;

        // 按最终的Z数组的行遍历
        for (int i = 0; i < numRows; i++) {
            // j是Z的圈数，即offset
            for (int j = 0; j + i < n; j += cycleLen) {
                // 第一行为例，最后只append每个Z的第一个字符
                ret.append(s.charAt(j + i));
                // 除了第一行和最后一行外，除了要拼接j+i之外，还要拼接从底部numRows - 1返回上来的那个字符j + cycleLen - i
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }


    public static String convert1(String s, int numRows) {
        if (numRows == 1) return s;

        int groupSize = 1 + (numRows - 2) * 2 + 1;
        int groupNum = s.length() / groupSize + 1;
        int groupWidth = groupNum * (numRows - 1);

        char[][] outArray = new char[numRows][groupWidth];

        // 填充数组
        for (int i = 0; i < s.length(); i++) {
            // 第几组
            int group = i / groupSize;
            // 在group中的位置
            int row = i % groupSize;
            if (row < numRows) {
                outArray[row][group * (numRows - 1)] = s.charAt(i);
            } else {
                outArray[2 * numRows - 2 - row][group * (numRows - 1) + row - (numRows - 1)] = s.charAt(i);
            }
        }

        // 遍历数组
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < groupWidth; j++) {
                if (outArray[i][j] != '\0') {
                    sb.append(outArray[i][j]);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        System.out.println(convert(s, numRows));
    }
}

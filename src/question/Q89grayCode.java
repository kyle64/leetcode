package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q89grayCode {
    /**
     * @Description: 89. 格雷编码
     *
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     *
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
     *
     * 格雷编码序列必须以 0 开头。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,3,2]
     * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     *
     * 对于给定的 n，其格雷编码序列并不唯一。
     * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     *
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * 示例 2:
     *
     * 输入: 0
     * 输出: [0]
     * 解释: 我们定义格雷编码序列必须以 0 开头。
     *      给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
     *      因此，当 n = 0 时，其格雷编码序列为 [0]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/gray-code
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 下午4:35
     * @param
     * @return java.util.List<java.lang.Integer>
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        // dp，每个数等于已添加的数加上一个2^(n-1)，然后倒序
        for (int i = 0; i < n; i++) {
            // 需要增加的数
            int num = 1 << i;
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(num + res.get(j));
            }
        }
        return res;
    }

    // 官方，镜像折叠，00 01 11 10 -> 100 101 111 110
    // 新增的部分倒序增加在原来数组的后面即可
    public List<Integer> grayCodeOfficial(int n) {
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            head <<= 1;
        }
        return res;
    }

    // 二进制转成格雷码有一个公式。
    // 所以我们遍历 0 到 2^n-1
    // 然后利用公式转换即可。即最高位保留，其它位是当前位和它的高一位进行异或操作。
    // 作者：windliang
    // 链接：https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public List<Integer> grayCodeFormula(int n) {
        List<Integer> gray = new ArrayList<Integer>();
        for(int binary = 0;binary < 1 << n; binary++){
            gray.add(binary ^ binary >> 1);
        }
        return gray;
    }


    // 超时
    public List<Integer> grayCodeDFS(int n) {
        // 相邻的数值进行异或比较后应该只有一位会是1，也就是说结果应该2的幂
        List<Integer> res = new ArrayList<>();
        // 输入数组
        List<Integer> input = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++) {
            input.add(i);
        }
        // 初始深度
        int depth = 0;
        // 初始化栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);

        // DFS深度优先搜索
        res = dfs(input, depth, res, stack);
        return res;
    }

    public List<Integer> dfs(List<Integer> input, int depth, List<Integer> res, Deque<Integer> stack) {
        if (depth == input.size() - 1) {
            res = new ArrayList<>(stack);
            return res;
        }

        for (int i = 0; i < input.size(); i++) {
            if (stack.contains(i)) continue;

            if (isPowerOfTwo(stack.peekLast() ^ i)) {
                stack.addLast(i);
                res = dfs(input, depth + 1, res, stack);
                stack.removeLast();
            }
        }

        return res;
    }

    public boolean isPowerOfTwo(int n) {
        // 2的幂-1之后的二进制数每一位都是1，而2的幂是10000000一共n个0的形式，AND位运算为0
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        Q89grayCode q89grayCode = new Q89grayCode();

        int n = 1;
        List<Integer> res = q89grayCode.grayCode(n);
        for (Integer re : res) {
            System.out.print(re + " ");
        }
    }
}

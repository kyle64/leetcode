/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author wuziheng
 * @description
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 *
 * @date 2025/5/11 19:03
 **/
public class Q394DecodeString {

    public String decodeString(String s) {
        Deque<Integer> numStk = new ArrayDeque<>();
        Deque<String> strStk = new ArrayDeque<>();

        StringBuilder res = new StringBuilder();
        char[] sArray = s.toCharArray();
        int multi = 0;

        for (char ch : sArray) {
            // 数字
            if (Character.isDigit(ch)) {
                multi = multi * 10 + (ch - '0');
            } else if (Character.isLetter(ch)) {
                // 字母
                res.append(ch);
            } else if ('[' == ch) {
                // 左括号
                numStk.push(multi);
                strStk.push(res.toString());
                // 重新初始化变量
                multi = 0;
                res = new StringBuilder();
            } else {
                // 右括号
                // ']' == ch
                StringBuilder tmp = new StringBuilder();
                int lastMulti = numStk.pop();
                while (lastMulti-- > 0) {
                    tmp.append(res);
                }
                res = new StringBuilder(strStk.pop() + tmp);
            }
        }

        return res.toString();
    }

    public String decodeString1(String s) {
        Deque<Integer> numStk = new ArrayDeque<>();
        Deque<String> strStk = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        char[] sArray = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            // 字母
            if (sArray[i] >= 'a' && sArray[i] <= 'z') {
                if (strStk.isEmpty()) {
                    sb.append(sArray[i]);
                } else {
                    strStk.push(strStk.pop() + sArray[i]);
                }
                i++;
                continue;
            }

            // 数字
            StringBuilder timeSb = new StringBuilder();
            while (sArray[i] >= '0' && sArray[i] <= '9') {
                timeSb.append(sArray[i]);
                i++;
            }
            if (timeSb.length() > 0) {
                numStk.push(Integer.valueOf(timeSb.toString()));
            }

            // 括号
            if (sArray[i] == '[') {
                strStk.push("");
                i++;
            }

            if (sArray[i] == ']') {
                int k = numStk.pop();
                String sub = strStk.pop();

                StringBuilder repeated = new StringBuilder();
                for (int j = 0; j < k; j++) {
                    repeated.append(sub);
                }

                if (strStk.isEmpty()) {
                    sb.append(repeated.toString());
                } else {
                    strStk.push(strStk.pop() + repeated.toString());
                }

                i++;
            }

        }

        return sb.toString();
    }
}

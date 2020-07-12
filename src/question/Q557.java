package question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/7/9.
 */
public class Q557 {
    /**
     * @Description: 557. 反转字符串中的单词 III
     *
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/9 下午4:15
     * @param
     * @return java.lang.String
     */
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();

        // split
        List<String> words = new ArrayList<>();
        StringBuilder w = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(w.toString());
                w = new StringBuilder();
            } else {
                w.append(s.charAt(i));
            }
        }
        words.add(w.toString());

        // reverse
        for(String word : words) {
            char[] chars = word.toCharArray();
            char temp;
            int left = 0, right = chars.length-1;
            while (left < right) {
                temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;

                left++;
                right--;
            }

            res.append(chars).append(' ');
        }

        return res.toString().trim();
    }

    public String reverseWords1(String s) {
        String[] strings = s.trim().split("\\s+");
        StringBuilder res = new StringBuilder();

        for(String word : strings) {
            StringBuilder sb = new StringBuilder(word);
            sb.reverse();
            res.append(sb).append(" ");
        }

        return res.toString().trim();
    }

    public static void main(String[] args) {
        Q557 q557 = new Q557();
        System.out.println(q557.reverseWords("Let's take LeetCode contest"));
    }
}

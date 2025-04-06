package question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ziheng on 2020/6/22.
 */
public class Q3 {

    /**
     * @param
     * @return int
     * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。(longest-substring-without-repeating-characters)
     * @date 2020/6/22 下午3:19
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int offset = -1;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) > offset) {
                offset = map.get(s.charAt(i));
                map.put(s.charAt(i), i);
            } else {
                map.put(s.charAt(i), i);
                result = Math.max(i - offset, result);
            }
        }

        return result;
    }


    public static int lengthOfLongestSubstring3(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int result = 0;

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            } else {
                while (left < right) {
                    set.remove(s.charAt(left));
                    left++;
                    if (s.charAt(left - 1) == s.charAt(right)) break;
                }
            }

            result = Math.max(right - left, result);
        }

        return result;
    }


    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int result = 0;

        while (right < s.length()) {
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }

            result = Math.max(right - left, result);

            set.remove(s.charAt(left));
            left++;
        }

        return result;
    }


    public static int lengthOfLongestSubstring1(String s) {
        int result = 0;

        // init map
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, 0);
//            map.computeIfPresent(c, (k,v) -> v + 1);
        }

        // 找到一个window，使其所有的character出现次数为1，最后取size最大的window
        int left = 0, right = 0; // two pointers

        while (right < s.length()) {
            char curRight = s.charAt(right);
            map.compute(curRight, (k, v) -> v + 1);
            right++;

            while (map.get(curRight) > 1) {
                char curLeft = s.charAt(left);
                map.compute(curLeft, (k, v) -> v - 1);

                left++;
            }

            result = (right - left) > result ? right - left : result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }
}

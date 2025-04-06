package question;

import java.util.*;

/**
 * Created by ziheng on 2020/7/29.
 */
public class Q76MinimumWindowSubstring {
    /**
     * @Description: 76. 最小覆盖子串
     *
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * 示例：
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     *
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/29 下午10:30
     * @param s
     * @param t
     * @return java.lang.String
     */
    public static String minWindowDistanceAdd(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        // 优化check
        // 用一个distance来表示window中有多少个t中的字符，超过t的频率，distance不增加
        int sLen = s.length();
        int tLen = t.length();

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // t的频率数组
        int[] tFreq = new int[128];
        // window的频率数组
        int[] winFreq = new int[128];

        for (char c : tArray) {
            tFreq[c]++;
        }
        // 用一个distance来表示window中有多少个t中的字符，超过t的频率，distance不增加
        int distance = 0;
        int left = 0, right = 0;
        int minLength = sLen + 1;
        int ansL = -1, ansR = -1;

        while (right < sLen) {
            char c = sArray[right];
            // 不在t中的字符，跳过
            if (tFreq[c] == 0) {
                right++;
                continue;
            }

            // 当window中t的字符频率小于t的频率时，增加distance
            if (tFreq[c] > 0 && winFreq[c] < tFreq[c]) {
                distance++;
            }

            // 增加window频率数组中的值
            winFreq[c]++;
            // 右移right
            right++;

            // t中的所有字符都在window中找到了
            while (distance == tLen) {
                // 区间长度小于当前最小长度，更新
                if (right - left < minLength) {
                    minLength = right - left; // 左闭右开，差即长度
                    ansL = left;
                    ansR = right;
                }

                // 如果s[left]正好是window中最后一个t的字符，即：移动left就会跳出循环，更新distance
                char lchar = sArray[left];
                if (tFreq[lchar] > 0 && winFreq[lchar] == tFreq[lchar]) {
                    distance--;
                }

                winFreq[lchar]--;
                // 移动left
                left++;
            }
        }

        return minLength > sLen ? "" : s.substring(ansL, ansR);
    }

    public static String minWindowDistanceSub(String s, String t) {
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        // 优化check
        // 用一个distance来表示window中还差多少个字符才能到达t的要求
        int sLen = s.length();
        int tLen = t.length();

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // t的频率数组
        int[] tFreq = new int[128];
        for (char c : tArray) {
            tFreq[c]++;
        }
        // distance来表示window中还差多少个t的字符
        int distance = tLen;
        int left = 0, right = 0;
        int minLength = sLen + 1;
        int ansL = -1, ansR = -1;

        while (right < sLen) {
            char c = sArray[right];
            // 当right对应的字符存在在t中时，减少distance
            if (tFreq[c] > 0) {
                distance--;
            }

            // 减少频率数组中的值（非t的字符更新为负值）
            tFreq[c]--;
            // 右移right
            right++;

            // t中的所有字符都找到了
            while (distance == 0) {
                // 区间长度小于当前最小长度，更新
                if (right - left < minLength) {
                    minLength = right - left; // 左闭右开，差即长度
                    ansL = left;
                    ansR = right;
                }

                // 如果s[left]正好是window中最后一个t的字符，即：移动left就会跳出循环，更新distance
                // 非t中的字符c'的频率tFreq[c']被更新成负数，不会命中该条件
                if (tFreq[sArray[left]] == 0) {
                    distance++;
                }

                tFreq[sArray[left]]++;
                // 移动left
                left++;
            }
        }

        return minLength > sLen ? "" : s.substring(ansL, ansR);
    }


    public static String minWindowOffical(String s, String t) {
        // 滑动数组优化
        // 用两个map，一个保存t中字符出现的频率，一个维护窗口内字符出现的频率
        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();

        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int len = s.length() + 1;
        int ansL = -1, ansR = -1;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 如果是目标字符，保存在window map
            if (targetMap.containsKey(c)) {
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            }

            while (left <= right && check(targetMap, windowMap)) {
                windowMap.put(s.charAt(left), windowMap.getOrDefault(s.charAt(left), 0) - 1);
                if (right - left + 1 < len) {
                    ansL = left;
                    ansR = right + 1;
                    len = right - left + 1;
                }
                left++;
            }

            right++;
        }

        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public static boolean check(Map<Character, Integer> targetMap, Map<Character, Integer> windowMap) {
        Iterator iter = targetMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (windowMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }


    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0) return "";

        // 初始化一个长度大于s的String
        char[] resultArray = new char[s.length() + 1];
        Arrays.fill(resultArray, '#');
        String result = Arrays.toString(resultArray);

        // 用一个map存储t的字符
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            map.computeIfPresent(c, (k,v) -> v + 1);
            map.putIfAbsent(c, 1);
        }

        // 滑动窗口寻找最短子字符串
        int left = 0, right = 0;
        while (right < s.length()) {
            Character character = s.charAt(right);
            if (map.containsKey(character)) {
                map.computeIfPresent(character, (k, v) -> v - 1);
            }

            while (left <= right && check(map.values())) {
                // 如果是目标字符，调整count
                map.computeIfPresent(s.charAt(left), (k, v) -> v + 1);
                if (right - left + 1 < result.length()) {
                    result = s.substring(left, right + 1);
                }
                left++;
            }
            right++;
        }

        return result.length() <= s.length() ? result : "";
    }

    // 判断window中是否还含有t中的所有字符
    private static boolean check(Collection<Integer> values) {
        for (Integer value : values) {
            if (value > 0) return false;
        }

        return true;
    }



    Map<Character, Integer> tMap = new HashMap<>();
    Map<Character, Integer> sMap = new HashMap<>();
    public String minWindow1(String s, String t) {
        String res = "";
        int left = 0, right = 0;

        // init tMap
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        while (right < s.length()) {
            sMap.put(s.charAt(right), sMap.getOrDefault(s.charAt(right), 0) + 1);
            while (left <= right && check(s.substring(left, right + 1))) {
                if (res.length() == 0 || right - left + 1 < res.length()) {
                    res = s.substring(left, right + 1);
                }

                sMap.put(s.charAt(left), sMap.getOrDefault(s.charAt(left), 0) - 1);
                left++;
            }

            // 当前位置right，不满足s(left, right)包含t字符串中的所有字符
            right++;
        }

        return res;
    }

    private boolean check(String ss) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            if (sMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        System.out.println(new Q76MinimumWindowSubstring().minWindow1(S, T));
    }
}

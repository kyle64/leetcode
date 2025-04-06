package question;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/7/22.
 */
public class Q13RomantoInteger {
    Map<Character, Integer> mapping = new HashMap<Character, Integer>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        // 判断条件其实可以简化，只要小数在大数前面就是减去，小数在大数右边就是加法
        if (s.length() == 0) return 0;

        int length = s.length();
        int sum = 0;
        // 用一个指针记录上一个数
        int prev = getValue(s.charAt(0));
        sum += prev;

        for (int i = 1; i < length; i++) {
            int current = getValue(s.charAt(i));
            sum += current;
            // 需要减掉prev
            if (current > prev) {
                sum -= 2 * prev;
            }
            prev = current;
        }

        return sum;
    }

    private int getValue(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


    public int romanToInt1(String s) {
        if (s.length() == 0) return 0;



        int length = s.length();
        int sum = 0;
        sum += mapping.get(s.charAt(0));

        for (int i = 1; i < length; i++) {
            sum += mapping.get(s.charAt(i));
            if ( ((s.charAt(i) == 'V' || s.charAt(i) == 'X') && (s.charAt(i - 1) == 'I'))
                    || ((s.charAt(i) == 'L' || s.charAt(i) == 'C') && (s.charAt(i - 1) == 'X'))
                    || ((s.charAt(i) == 'D' || s.charAt(i) == 'M') && (s.charAt(i - 1) == 'C'))
                    ) {
                sum -= 2 * mapping.get(s.charAt(i - 1));
            }
        }

        return sum;
    }
}

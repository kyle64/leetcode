package question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019/12/4.
 */
public class Q902 {

    public int atMostNGivenDigitSet(String[] D, int N) {
        String NString = String.valueOf(N);
        int DSize = D.length;
        int[] keys = new int[DSize];
        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < DSize; i++) {
            keys[i] = D[i].charAt(0) - '0';
        }

        for (int i = 0; i < NString.length(); i++) {
           nums.add( NString.charAt(i) - '0');
        }

        List<Integer> powerList = new ArrayList<>();

        // 0 ~ n-1 digits list
        for (int i = 1; i < NString.length(); i++) {
            powerList.add(new Double(Math.pow(DSize, i)).intValue());
        }

        // n digits
        for (int i = 0; i < nums.size(); i--) {
            int keyCount = 0;
            int digit = nums.get(i);
            
            for (int j = 0; j < keys.length; j++) {
                
            }
        }

        return 0;
    }
}

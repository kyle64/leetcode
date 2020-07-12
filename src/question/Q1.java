package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ziheng on 2018/2/26.
 */
public class Q1 {
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<Integer>());
            list.add(i);
            map.put(nums[i], list);
        }

        for (Map.Entry entry : map.entrySet()) {
            if (map.containsKey(target - (Integer) entry.getKey())) {
                res[0] = map.get(entry.getKey()).get(0);
                List secondList = map.get(target - (Integer) entry.getKey());
                res[1] = (int) secondList.get(secondList.size() - 1);
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums={3,3};
        int target=6;

        int[] res = Q1.twoSum(nums, target);
        System.out.println(res[0]+" "+res[1]);
    }
}

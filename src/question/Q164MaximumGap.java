package question;

/**
 * Created by ziheng on 2020/11/27.
 */
public class Q164MaximumGap {
    public int maximumGap(int[] nums) {
        int length = nums.length;
        if (length < 2) return 0;

        // 寻找最大的数
        int max = nums[0];
        for (int i = 0; i < length; i++) { // find max element
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        int[] buffer = new int[length];
        int divisor = 1;
        while (max >= divisor) {
            int[] order = new int[10];
            // 将每个数没有统计过的最后一位放到对应的桶里
            for (int i = 0; i < length; i++) {
                int digit = (nums[i]/divisor) % 10;
                order[digit]++;
            }

            // 由原本表示每个桶的数量，变为表示在数组中的索引
            for (int i = 1; i < 10; i++) {
                order[i] += order[i - 1];
            }

            // 此步对nums按照低位大小进行排序
            for (int i = length - 1; i >= 0; i--) {
                int digit = (nums[i]/divisor) % 10;
                buffer[order[digit] - 1] = nums[i];
                order[digit]--;
            }

            System.arraycopy(buffer, 0, nums, 0, length);

            divisor *= 10;
        }

        int maxSpan = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            if (nums[i] - nums[i-1] > maxSpan) {
                maxSpan = nums[i] - nums[i-1];
            }
        }

        return maxSpan;
    }
}

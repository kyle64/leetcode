package question;

/**
 * Created by ziheng on 2019/12/3.
 */
public class Q275 {
    public static int hIndex(int[] citations) {
        int left = 0;
        int right = citations.length-1;
        int res = 0;

        if (citations.length == 0 || citations[right] == 0) {
            return 0;
        }

        while (left < right) {
            int mid = (left+right)/2;
            int count = citations.length - mid;

            if (citations[mid] >= count) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return citations.length - left;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,4,5,7};

        int hIndex = hIndex(nums);

        System.out.println(hIndex);
    }
}

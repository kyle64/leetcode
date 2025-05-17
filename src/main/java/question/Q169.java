package question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/7/9.
 */
public class Q169 {
    /**
     * @Description: 169. 多数元素
     *
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/9 下午10:40
     * @param
     * @return int
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.compute(num, (k, v) -> v + 1);
            } else {
                map.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }

        return 0;
    }

    public int majorityElement1(int[] nums) {
        // O(nlogn)
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /*
    * 方法四：分治
    *
    * 思路
    * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
    * 我们可以使用反证法来证明这个结论。假设 a 既不是左半部分的众数，也不是右半部分的众数，
    * 那么 a 出现的次数少于 l / 2 + r / 2 次，其中 l 和 r 分别是左半部分和右半部分的长度。
    * 由于 l / 2 + r / 2 <= (l + r) / 2，说明 a 也不是数组 nums 的众数，因此出现了矛盾。所以这个结论是正确的。
    * 这样以来，我们就可以使用分治法解决这个问题：将数组分成左右两部分，分别求出左半部分的众数 a1 以及右半部分的众数 a2，随后在 a1 和 a2 中选出正确的众数。
    *
    * 算法
    * 我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
    * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。
    * 如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。
    * 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
    * 否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
    * */
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement2(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }

    // 方法五：Boyer-Moore 投票算法
    //思路
    //
    //如果我们把众数记为 +1+1，把其他数记为 -1−1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
    //
    //算法
    //
    //Boyer-Moore 算法的本质和方法四中的分治十分类似。我们首先给出 Boyer-Moore 算法的详细步骤：
    //
    //我们维护一个候选众数 candidate 和它出现的次数 count。初始时 candidate 可以为任意值，count 为 0；
    //
    //我们遍历数组 nums 中的所有元素，对于每个元素 x，在判断 x 之前，如果 count 的值为 0，我们先将 x 的值赋予 candidate，随后我们判断 x：
    //
    //如果 x 与 candidate 相等，那么计数器 count 的值增加 1；
    //
    //如果 x 与 candidate 不等，那么计数器 count 的值减少 1。
    //
    //在遍历完成后，candidate 即为整个数组的众数。
    //
    //我们举一个具体的例子，例如下面的这个数组：
    //
    //
    //[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //在遍历到数组中的第一个元素以及每个在 | 之后的元素时，candidate 都会因为 count 的值变为 0 而发生改变。最后一次 candidate 的值从 5 变为 7，也就是这个数组中的众数。
    //
    //Boyer-Moore 算法的正确性较难证明，这里给出一种较为详细的用例子辅助证明的思路，供读者参考：
    //
    //首先我们根据算法步骤中对 count 的定义，可以发现：在对整个数组进行遍历的过程中，count 的值一定非负。这是因为如果 count 的值为 0，那么在这一轮遍历的开始时刻，我们会将 x 的值赋予 candidate 并在接下来的一步中将 count 的值增加 1。因此 count 的值在遍历的过程中一直保持非负。
    //
    //那么 count 本身除了计数器之外，还有什么更深层次的意义呢？我们还是以数组
    //
    //
    //[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //作为例子，首先写下它在每一步遍历时 candidate 和 count 的值：
    //
    //
    //nums:      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //candidate:  7  7  7  7  7  7   5  5   5  5  5  5   7  7  7  7
    //count:      1  2  1  2  1  0   1  0   1  2  1  0   1  2  3  4
    //我们再定义一个变量 value，它和真正的众数 maj 绑定。在每一步遍历时，如果当前的数 x 和 maj 相等，那么 value 的值加 1，否则减 1。value 的实际意义即为：到当前的这一步遍历为止，众数出现的次数比非众数多出了多少次。我们将 value 的值也写在下方：
    //
    //
    //nums:      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //value:      1  2  1  2  1  0  -1  0  -1 -2 -1  0   1  2  3  4
    //有没有发现什么？我们将 count 和 value 放在一起：
    //
    //
    //nums:      [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //count:      1  2  1  2  1  0   1  0   1  2  1  0   1  2  3  4
    //value:      1  2  1  2  1  0  -1  0  -1 -2 -1  0   1  2  3  4
    //发现在每一步遍历中，count 和 value 要么相等，要么互为相反数！并且在候选众数 candidate 就是 maj 时，它们相等，candidate 是其它的数时，它们互为相反数！
    //
    //为什么会有这么奇妙的性质呢？这并不难证明：我们将候选众数 candidate 保持不变的连续的遍历称为「一段」。在同一段中，count 的值是根据 candidate == x 的判断进行加减的。那么如果 candidate 恰好为 maj，那么在这一段中，count 和 value 的变化是同步的；如果 candidate 不为 maj，那么在这一段中 count 和 value 的变化是相反的。因此就有了这样一个奇妙的性质。
    //
    //这样以来，由于：
    //
    //我们证明了 count 的值一直为非负，在最后一步遍历结束后也是如此；
    //
    //由于 value 的值与真正的众数 maj 绑定，并且它表示「众数出现的次数比非众数多出了多少次」，那么在最后一步遍历结束后，value 的值为正数；
    //
    //在最后一步遍历结束后，count 非负，value 为正数，所以它们不可能互为相反数，只可能相等，即 count == value。因此在最后「一段」中，count 的 value 的变化是同步的，也就是说，candidate 中存储的候选众数就是真正的众数 maj。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int majorityElementBoyerMoore(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

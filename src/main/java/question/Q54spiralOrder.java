package question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/7/16.
 */
public class Q54spiralOrder {
    /**
     * @Description: 54. 螺旋矩阵
     *
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/spiral-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/16 上午2:43
     * @param
     * @return java.util.List<java.lang.Integer>
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;

        spiralOrderHelper(result, matrix, 0, 0, matrix[0].length, matrix.length);

        return result;
    }

    public void spiralOrderHelper(List<Integer> result, int[][] matrix, int startx, int starty, int width, int height) {
        if (width <= 0 || height <= 0) return;
        if (width == 1) {
            for (int i = 0; i < height; i++) {
                result.add(matrix[starty + i][startx]);
            }
            return;
        }
        if (height == 1) {
            for (int i = 0; i < width; i++) {
                result.add(matrix[starty][startx + i]);
            }
            return;
        }

        for (int i = 0; i < width - 1; i++) {
            result.add(matrix[starty][startx + i]);
        }

        for (int i = 0; i < height - 1; i++) {
            result.add(matrix[starty + i][startx + width - 1]);
        }

        for (int i = 0; i < width - 1; i++) {
            result.add(matrix[starty + height - 1][startx + width - 1 - i]);
        }

        for (int i = 0; i < height - 1; i++) {
            result.add(matrix[starty + height - 1 - i][startx]);
        }

        spiralOrderHelper(result, matrix, startx + 1, starty + 1, width - 2, height - 2);
    }

    public static void main(String[] args) {
        Q54spiralOrder q54spiralOrder = new Q54spiralOrder();
        int[][] input = new int[][] {
                {6, 9, 7},
        };

        List<Integer> list = q54spiralOrder.spiralOrder(input);
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}

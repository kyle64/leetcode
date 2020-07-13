package question;

/**
 * Created by ziheng on 2020/7/13.
 */
public class Q59 {
    /**
     * @Description: 59. 螺旋矩阵 II
     *
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @date 2020/7/13 上午10:13
     * @param
     * @return int[][]
     */
    public int[][] generateMatrixOfficial(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }


    public int[][] generateMatrix(int n) {
        // 考虑4个顶点的情况
        // 分别是1，n，1+(n-1)*2 = 2n-1,1+(n-1)*3 = 3n-2
        // 外圈最后一个数应该是 1+(n-1)*4-1 = 4n-4
        int[][] result = new int[n][n];

        generateMatrixHelper(result, 1, n, 0);
        return result;
    }

    public void generateMatrixHelper(int[][] matrix, int offset, int n, int startPosition) {
        if (n == 0) return;
        if (n == 1) {
            matrix[startPosition][startPosition] = offset;
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            // upper line
            matrix[startPosition][startPosition + i] = offset + i;
            // right line
            matrix[startPosition + i][startPosition + n - 1] = offset + i + (n - 1);
            // bot line
            matrix[startPosition + n - 1][startPosition + n - 1 - i] = offset + i + 2 * (n - 1);
            // left line
            matrix[startPosition + n - 1 - i][startPosition] = offset + i + 3 * (n - 1);
        }

        generateMatrixHelper(matrix, offset + 4 * (n - 1), n - 2, startPosition + 1);
    }

    public static void main(String[] args) {
        Q59 q59 = new Q59();
        int n = 5;
        int[][] res = q59.generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]);
            }
            System.out.print("\n");
        }
    }
}

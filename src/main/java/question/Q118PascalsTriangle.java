/*
 * Copyright (C) 2025 Baidu, Inc. All Rights Reserved.
 */
package question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuziheng
 * @description
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 *
 * 提示:
 *
 * 1 <= numRows <= 30
 *
 * @date 2025/5/14 16:01
 **/
public class Q118PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        // init
        List<Integer> l1 = new ArrayList<>(1) {{
            add(1);
        }};
        res.add(l1);

        for (int i = 2; i <= numRows; i++) {
            List<Integer> current = new ArrayList<>();
            List<Integer> last = res.get(res.size() - 1);
            current.add(1);
            for (int j = 1; j < i - 1; j++) {
                current.add(last.get(j - 1) + last.get(j));
            }
            current.add(1);
            res.add(current);
        }
        return res;
    }

    public List<List<Integer>> generateOpt(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    current.add(1);
                } else {
                    current.add(res.get(res.size() - 1).get(j - 1) + res.get(res.size() - 1).get(j));
                }
            }
            res.add(current);
        }
        return res;
    }
}

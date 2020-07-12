package question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q155 {
    /**
     * @param
     * @Description: 155. 最小栈
     * <p>
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     *  
     * <p>
     * 示例:
     * <p>
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     * <p>
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     * <p>
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *  
     * <p>
     * 提示：
     * <p>
     * pop、top 和 getMin 操作总是在 非空栈 上调用。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-stack
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @date 2020/7/10 下午1:41
     * @return
     */
    class MinStack {
        // 使用两个stack
        Deque<Integer> insertStack;
        Deque<Integer> savedMinStack;

        /** initialize your data structure here. */
        public MinStack() {
            this.insertStack = new ArrayDeque<>();
            this.savedMinStack = new ArrayDeque<>();
            this.savedMinStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            this.insertStack.push(x);
            int min = x < savedMinStack.peek() ? x : savedMinStack.peek();
            savedMinStack.push(min);
        }

        public void pop() {
            this.insertStack.pop();
            this.savedMinStack.pop();
        }

        public int top() {
            return insertStack.peek();
        }

        public int getMin() {
            return savedMinStack.peek();
        }
    }

    public static class MinStack2 {
        Deque<Integer> insertStack;
        List<Integer> sortList;
        int min;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            this.insertStack = new ArrayDeque<>();
            this.sortList = new ArrayList<>();
            this.min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            this.insertStack.push(x);
            if (x <= this.min) {
                sortList.add(0, x);
                this.min = sortList.get(0);
            } else {
                sortList.add(x);
            }
        }

        public void pop() {
            Integer y = this.insertStack.pop();
            this.sortList.remove(y);
            if (y == this.min) {
                if (this.sortList.size() > 0)
                    this.min = sortList.get(0);
                else
                    this.min = Integer.MAX_VALUE;
            }
        }

        public int top() {
            return this.insertStack.peek();
        }

        public int getMin() {
            return this.min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}

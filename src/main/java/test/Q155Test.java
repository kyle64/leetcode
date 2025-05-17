package test;

import question.Q155MinStack;

/**
 * Created by ziheng on 2020/7/10.
 */
public class Q155Test {
    public static void main(String[] args) {
        Q155MinStack.MinStack2 minStack = new Q155MinStack.MinStack2();
        minStack.push(Integer.MAX_VALUE-1);
        minStack.push(Integer.MAX_VALUE-1);
        minStack.push(Integer.MAX_VALUE);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(Integer.MAX_VALUE);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(Integer.MIN_VALUE);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());

    }
}

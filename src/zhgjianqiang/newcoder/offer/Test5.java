package zhgjianqiang.newcoder.offer;

import org.junit.Test;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class Test5 {

    @Test
    public void test() {

    }

    public class Solution {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack1.empty() && stack2.empty()) {
                throw new RuntimeException();
            } else {
                if (!stack2.isEmpty()) {
                    return stack2.pop();
                }
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }
    }
}

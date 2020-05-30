package leetCode;

import java.util.Stack;

/**
 * @author fanbaicheng
 * @since 2020/5/30 12:46
 */
public class No84 {

    public static int largestRectangleArea(int[] heights) {

        int len = heights.length;

        if (len == 0) {
            return 0;
        }

        // 使用最小栈

        // 本次的目的是，针对一根柱子，找出他的左边界，和右边界。
        // 所以暴力解法很简单，但时间复杂度为N方
        // 所以，引出最小栈这个东西
        int[] left = new int[len];
        int[] right = new int[len];

        // 定义一个最小栈，最小栈是什么，下面的是最小的，依次增大
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < len; i++) {

            // 如果栈顶元素大，那么将栈顶元素pop出去
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }

            // 判断栈是否为空，为空将-1存入left，说明，左边没有比自己小的
            left[i] = stack.isEmpty() ? -1 : stack.peek();

            // 将自己的下标入最小栈
            stack.push(i);
        }

        // 左边整完了。把栈清空，开始整右边
        stack.clear();

        for (int i = len - 1; i >= 0; i --) {
            // 如果栈顶元素大，那么将栈顶元素pop出去
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                stack.pop();
            }

            // 判断栈是否为空，为空将len存入right，说明，右边没有比自己小的
            right[i] = stack.isEmpty() ? len : stack.peek();

            // 将自己的下标入最小栈
            stack.push(i);
        }

        // 左右都push进去了，开始计算
        int max = -1;
        for (int i = 0; i < len; i++) {

            int leftIndex = left[i];
            int rightIndex = right[i];

            // 计算
            int wide = rightIndex - leftIndex - 1;
            int c = wide * heights[i];
            max = max > c ? max : c;
        }


        return max;
    }

    public static void main(String[] args) {


        int[] hights = new int[]{1,1};
        int max = largestRectangleArea(hights);
        System.out.println(max);
    }
}

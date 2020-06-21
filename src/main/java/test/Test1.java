package test;


import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author fanbaicheng
 * @since 2020/6/17 18:21
 *
 * 入参 线段
 * 求 线段覆盖的总长度
 * (1,0, 2.3) (4.5, 7) (6, 8.5)
 */
public class Test1 {
    public static boolean check(String str) {

        Stack<String> stack = new Stack();

        for(int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // 如果不是 ( ) 直接返回false
            if (currentChar != '(' && currentChar != ')') {
                return false;
            }

            // 如果是小括号，压栈
            if (currentChar != '(') {
                stack.push(String.valueOf(currentChar));
            } else {
                // 如果是右括号
                // 弹出
                if (stack.isEmpty()) {
                    return false;
                }

                String popStr = stack.pop();
                if (!popStr.equals("(")) {
                    return false;
                }
            }
        }

        // 如果栈为空 说明都匹配到了返回true
        if (stack.isEmpty()) {
            return true;
        }

        // 否则返回false
        return false;
    }

    public static void main(String[] args) {
        try{

            boolean result = check("()()");

            System.out.println(result ? "YES" : "NO");
        } catch(Exception e) {
            // do
        }


    }

}

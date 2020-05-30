package leetCode;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author fanbaicheng
 * @since 2020/5/28 19:32
 */
public class No394 {

    public static String decodeString(String s) {

        Stack<String> stack = new Stack<String>();

        // 定义一个字符串，用于记录
        String tmp = "";

        // 匹配中括号
        for (int i = 0; i < s.length(); i++) {

            // 不等于[] tmp开始统计数量 或者 字符串
            if (s.charAt(i) != '[' && s.charAt(i) != ']') {

                tmp += s.charAt(i);

                // 最后一位都不是]  将tmp压栈
                if (i == s.length() - 1) {
                    stack.push(tmp);
                }

                continue;
            }

            // 如果等于[ 将[ 前面的数字 压栈 并将[ 压栈
            // 并 清空tmp
            if (s.charAt(i) == '[') {

                // 判断 aa32[ 这种情况
                // 要将字符串与数字拆分
                if (Character.isLetter(tmp.charAt(0))) {

                    int partition = 0;
                    while (partition < tmp.length() && Character.isLetter(tmp.charAt(partition))) {
                        partition++;
                    }

                    // partition的位置是数字的位置
                    String p1 = tmp.substring(0, partition);
                    String p2 = tmp.substring(partition, tmp.length());

                    // 判断前面的数是不是字符
                    // 如果栈顶是字符串的话，需要将字符串与当前字符串合并
                    if (!stack.isEmpty()) {
                        String ding = stack.pop();
                        if (isAz(ding)) {
                            p1 = ding + p1;
                            stack.push(p1);
                        } else {
                            stack.push(ding);
                            stack.push(p1);
                        }
                        stack.push(p2);
                        stack.push("[");

                    } else {
                        stack.push(p1);
                        stack.push(p2);
                        stack.push("[");
                    }

                } else {

                    // 纯数字
                    stack.push(tmp);
                    stack.push("[");
                }

                tmp = "";
                continue;
            }

            // 如果是 ]
            if (!tmp.equals("")) {

                // 判断前面的数是不是字符
                // 如果栈顶是字符串的话，需要将字符串与当前字符串合并
                if (!stack.isEmpty()) {
                    String ding = stack.pop();
                    if (isAz(ding)) {
                        tmp = ding + tmp;
                        stack.push(tmp);

                    } else {
                        stack.push(ding);
                        stack.push(tmp);
                    }

                }

                tmp = "";
            }

            //"2[abc]3[cd]ef"

            // 如果等等于 ] 说名已经匹配了一个[ 。开始计算。此时的栈 13[12[asd
            // pop三次
            // 第一次是字符串
            // 第二次是[
            // 第三次是 数量
            String str = stack.pop();
            String symbol = stack.pop();
            String num = stack.pop();

            // 计算数量，然后把计算的结果压栈
            String result = strAdaptor(str, num);

            // 如果栈顶是字符串的话，需要将字符串与当前字符串合并
            if (!stack.isEmpty()) {
                String ding = stack.pop();
                if (isAz(ding)) {
                    result = ding + result;
                    stack.push(result);
                    continue;
                }
                stack.push(ding);
            }

            stack.push(result);
        }


        String x = "";
        while (!stack.isEmpty()) {
            x = stack.pop() + x;
        }

        return x ;

    }


    public static boolean isAz(String ding) {

        for (int i = 0; i < ding.length(); i ++) {

            if (!Character.isLetter(ding.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String strAdaptor(String str, String num) {

        String result = "";

        int n = Integer.valueOf(num);
        for (int i = 0; i < n; i++) {
            result += str;
        }

        return result;
    }

    public static void main(String[] args) {

        String result = decodeString("sd2[f2[e]g]i");
        System.out.println(result);
    }
}

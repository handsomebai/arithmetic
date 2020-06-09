package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/9 10:07
 */
public class No46 {
    private int a;

    public static int translateNum(int num) {
        // 简单动态规划
        // 第2个 是前一个 ，如果和前一个能组成字符串的话，那就再+ 前前
        String numStr = String.valueOf(num);
        int[] tmp = new int[numStr.length()];

        tmp[0] = 1;

        for (int i = 1; i < numStr.length(); i++) {

            tmp[i] = tmp[i-1];

            // 如果和前一个能组成字符串的话，那就再+1
            int current = getIndex(numStr, i);
            int last = getIndex(numStr, i - 1);
            if ( last == 1 || (last == 2 && current >= 0 && current <= 5) ) {

                int x = tmp[i-1];
                int xx = i >= 2 ? tmp[i-2] : 1;
                tmp[i] = x + xx;
            }
        }

        return tmp[numStr.length() - 1];
    }

    public static int getIndex(String numStr, int index) {
        return Integer.valueOf(String.valueOf(numStr.charAt(index)));
    }

    public static void main(String[] args) {

        int result = translateNum(506);
        System.out.println(result);

    }
}

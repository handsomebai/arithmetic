package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanbaicheng
 * @since 2020/6/3 11:11
 */
public class No837 {

    public static double new21Game(int N, int K, int W) {

        if (K == 0) {
            return 1.0;
        }

        // 从后往前动态规划
        // 当手里的数字之和为k-1时，就只有一次抽牌的机会了。此时就是边界条件了。
        // 计算抽到[1-w]后，是否超过N，超过概率就是0，未超过概率就是1

        double[] dp = new double[K+W+1];

        for (int i = K, num = 1; num <= W; i++, num++) {
            if (K - 1 + num <= N) {
                dp[i] = 1.0;
            } else {
                dp[i] = 0;
            }
        }

        for (int i = K - 1; i >= 0; i--) {

            double sum = 0;
            // 针对每一个总和，算它的概率
            for (int num = 1; num <= W; num++) {
                sum = sum + dp[i+num];
            }

            dp[i] = sum / W;
        }

        return dp[0];
    }

    public static void main(String[] args) {

        double result = new21Game(10,1,10);
        System.out.println(result);

    }
}

package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/5/26 15:58
 *
 * 结题思路：动态规划
 */
public class No322 {

    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return calculate(coins, amount, new int[amount + 1]);
    }

    /**
     * coins 硬币数组
     * amount 当前需要计算的总金额
     * tmp 计算过的金额对应的最小硬币数  下标为金额，值为最小硬币数
     */
    public static int calculate(int[] coins, int amount, int[] tmp) {

        // 如果当前的值，小于0，说明扣钱扣多了，终止：返回-1
        if (amount < 0) {
            return -1;
        }

        // 如果总金额为0，说明刚好扣完，完美结束。返回0
        if (amount == 0) {
            return 0;
        }

        // 如果当前金额，之前被计算过，直接返回
        if (tmp[amount] != 0) {
            return tmp[amount];
        }

        // 走到这，说明，该金额可算，且之前没被算过，那么就开始算吧

        // 遍历每个硬币，计算扣完这个硬币之后，所用的个数。得出这个个数的最小值，就是我们所需要的

        // 先定义一个最小值
        int min = Integer.MAX_VALUE;

        for(int coin : coins) {

            // 计算扣完这枚硬币后，所用硬币的个数
            int result = calculate(coins, amount - coin, tmp);

            // 判断，是否扣超了，扣超了就下一个硬币
            if (result < 0) {
                continue;
            }

            // 判断该个数，是否是最小的个数
            if (result < min) {
                min = result + 1;
            }
        }

        // 都扣超了。无解
        if (min == Integer.MAX_VALUE) {
            tmp[amount] = -1;
        } else {
            // 如果扣完个数是最小的，那么没扣前的个数就是 扣完个数是最小的 + 1
            tmp[amount] = min;
        }

        return tmp[amount];
    }

    public static void main(String[] args) {

        int[] coins = new int[] {186,419,83,408};
        int result = coinChange(coins, 6249);
        System.out.println(result);
    }

}

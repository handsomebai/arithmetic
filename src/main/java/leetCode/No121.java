package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/16 16:42
 */
public class No121 {

    public int max = 0;

    public int maxProfit(int[] prices) {

        if (prices.length == 0 || prices.length == 1) {
            return 0;
        }

        dfs(0, 0, prices, 0);
        return max > 0 ? max : 0;
    }


    // 深度优先搜索

    // total 当前手里的钱
    // lastOpt 0-未操作 1-买入 2-买出
    // index
    public void dfs(int total, int lastOpt, int[] prices, int index) {
        // 可以选择不做操
        // 选择 买入
        // 选择 买出


        // 如果上一次是卖出， 那么结算
        if (lastOpt == 2) {
            max = total > max ? total : max;
            return;
        }

        if (index >= prices.length) {
            return;
        }

        // 如果上一次是不操作，可以选择继续不操作，可以选择买入
        if (lastOpt == 0) {

            // 继续不操作
            dfs(total, 0, prices, index + 1);

            int nextTotal = total - prices[index];
            // 买入
            dfs(nextTotal, 1, prices, index + 1);
        }

        // 如果上一次的操作是买入，可以选择买出，不操作
        if (lastOpt == 1) {
            // 不操作  相当于买入
            dfs(total, 1, prices, index + 1);

            // 买出
            int nextTotal = total + prices[index];
            dfs(nextTotal, 2, prices, index + 1);
        }
    }

    public static void main(String[] args) {
        No121 n = new No121();
        int result = n.maxProfit(new int[]{1,2});
        System.out.println(result);
    }
}

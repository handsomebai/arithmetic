package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/14 17:15
 */
public class No746 {

    public static int minCost = Integer.MAX_VALUE;

    public static int minCostClimbingStairs(int[] cost) {

        int[] visit = new int[cost.length];

        // 深度优先搜索
        dfs(0, 0, cost, visit);
        dfs(0, 1, cost, visit);
        return minCost;
    }

    // 当前一共花费
    // 当前所在index
    // 体力花费值list
    public static void dfs(int totalCost, int currentIndex, int[] cost, int[] visit) {

        // 当走出去结束
        if (currentIndex >= cost.length) {

            // 结束的时候，判断是否是最大消费
            minCost = totalCost < minCost ? totalCost : minCost;
            return;
        }

        // 访问过跳过
        if (visit[currentIndex] == 1) {
            return;
        }

        // 做选择

        // 站上
        totalCost = totalCost + cost[currentIndex];
        // 访问过
        visit[currentIndex] = 1;

        // 跳一步
        dfs(totalCost, currentIndex + 1, cost, visit);

        // 跳两步
        dfs(totalCost, currentIndex + 2, cost, visit);

    }

    public static void main(String[] args) {

        int[] cost = new int[]{0,0,1,0};

        int result = minCostClimbingStairs(cost);

        System.out.println(result);

    }
}

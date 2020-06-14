package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/13 16:19
 */
public class No0_1 {

    public static int resultPrice = 0;

    // 0-1 背包
    // 输入 物品个数N 背包最大承重W
    // 物品重量 list
    // 物品价值 list
    // 返回总价值贵高的组合
    public static int getMaxPrice(int N, int W, int[] wight, int[] price) {

        dfs(0, 0, 0, wight, price, N, W);
        return resultPrice;
    }

    // 需要当前遍历到了第几个
    // 当前背包中重量
    // dfs中最大重量标识
    // 两个数组
    // 目标重量
    public static void dfs(int index, int currentWight, int currentPrice, int[] wight, int[] price, int N, int W) {


        // 如果index超过限制，返回
        if (index == N) {
            return;
        }

        // 判断当前是不是最大的价值
        resultPrice = currentPrice > resultPrice ? currentPrice : resultPrice;

        // 做选择
        // 1. 不放入背包 --> 只将index++就好
        dfs(index + 1, currentWight, currentPrice, wight, price, N, W);

        // 2.放入背包
        // 2.1 判断放入背包是否超量程
        currentWight = currentWight + wight[index];
        if (currentWight > W) {
            return;
        }

        // 2.2加入价值
        currentPrice = currentPrice + price[index];

        dfs(index + 1, currentWight, currentPrice, wight, price, N, W);
    }

    public static void main(String[] args) {

        int result = getMaxPrice(3,4, new int[]{2,1,3}, new int[]{4,2,3});
        System.out.println(result);

    }
}

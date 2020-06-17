package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/16 11:59
 */
public class No96 {

    public int count = 0;

    public int numTrees(int n) {

        int[] tree = new int[n*n];
        tree[1] = 1;
        dfs(n-1, tree, 1);
        return count;

    }

    // 做选择
    // rest 剩余的选择
    // 每次选择，左，右，跳到选择上，不跳到选择上
    // currentIndex为当前所在节点，+1为左节点 +2为右节点
    public void dfs(int rest, int[] tree, int currentIndex) {

        int leftIndex = 2 * currentIndex;
        int rightIndex = 2 * currentIndex + 1;

        // 选择左边
        if (tree[leftIndex] == 0) {
            int[] newTree = tree.clone();
            newTree[leftIndex] = 1;

            int r = rest - 1;

            // 选完次数没了 count + 1
            if (r == 0) {
                count = count + 1;
                return;
            }

            // 不跳上去
            dfs(r, newTree, currentIndex);

            // 跳上去
            dfs(r, newTree, leftIndex);
        }

        // 选择右边
        if (tree[rightIndex] == 0) {

            int[] newTree = tree.clone();
            newTree[rightIndex] = 1;

            int r = rest - 1;

            // 选完次数没了 count + 1
            if (r == 0) {
                count = count + 1;
                return;
            }

            // 不跳上去
            dfs(r, newTree, currentIndex);

            // 跳上去
            dfs(r, newTree, rightIndex);
        }
    }

    public static void main(String[] args) {
        No96 n = new No96();
        int result = n.numTrees(3);
        System.out.println(result);
    }
}

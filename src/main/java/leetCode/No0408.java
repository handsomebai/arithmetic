package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanbaicheng
 * @since 2020/6/25 11:59
 */
public class No0408 {

    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    List<List<TreeNode>> out_road = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        dfs(root, new ArrayList(), p);
        dfs(root, new ArrayList(), q);

        List<TreeNode> p_road = out_road.get(0);
        List<TreeNode> q_road = out_road.get(1);

        int length = p_road.size() < q_road.size() ? p_road.size() : q_road.size();

        for (int i = 0; i < length; i++) {
            if (p_road.get(i) != q_road.get(i)) {
                return p_road.get(i-1);
            }
        }

        return null;
    }

    public void dfs(TreeNode node, List<TreeNode> road, TreeNode target) {

        if (node == null) {
            return;
        }

        // 将自己放入road中
        road.add(node);

        // 判断当前node 是否为target
        if (node == target) {
            out_road.add(road);
            return;
        }

        // 继续dfs
        List<TreeNode> left = new ArrayList(road);
        dfs(node.left, left, target);

        List<TreeNode> right = new ArrayList(road);
        dfs(node.right, right, target);
    }

    public static void main(String[] args) {

        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n0.left = n7;
        n0.right = n4;


        No0408 handler = new No0408();
        TreeNode result = handler.lowestCommonAncestor(n3, n5, n4);
        System.out.println(result);

    }
}

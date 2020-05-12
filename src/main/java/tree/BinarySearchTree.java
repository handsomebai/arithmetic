package tree;



/**
 * @author fanbaicheng
 * @since 2020/5/12 18:18
 *
 * 二叉搜索树
 */
public class BinarySearchTree extends BinaryTree {

    /**
     * 定义根节点
     */
    private static TreeNode root;

    /**
     * 查找
     */
    private static TreeNode find(int data) {
        TreeNode p = root;
        while (p != null) {
            if (p.key == data) {
                return p;
            } else if (p.key < data) {
                p = p.leftChild;
            } else {
                p = p.rightChild;
            }
        }
        return null;
    }

    /**
     * 插入
     */
    private static void insert(int data) {
        if (root == null) {
            root = new TreeNode(data, "");
            return;
        }

        TreeNode p = root;
        while (p != null) {

            if (data < p.key) {

                if (p.leftChild == null) {
                    p.leftChild = new TreeNode(data, "");
                    return;
                } else {
                    p = p.leftChild;
                }
            } else {

                if (p.rightChild == null) {
                    p.rightChild = new TreeNode(data, "");
                    return;
                } else {
                    p = p.rightChild;
                }
            }
        }
    }

    /**
     * 删除
     */
    private static void delete(int data) {

        TreeNode p = root;

        // pp 记录的是p的父节点
        TreeNode pp = null;

        // 先找到它，再分情况
        while (p != null && p.key != data) {

           if (p.key < data) {
                pp = p;
                p = p.leftChild;
            } else {
                pp = p;
                p = p.rightChild;
            }
        }

        // 到这里就是找到了, 或者轮训完了
        if (p == null) {
            // 没找到
            return;
        }

        // 1.要删除的节点有左右两个子节点
        if (p.leftChild != null && p.rightChild != null) {

            // 现在要做的就是找到右子树里面最小的那个节点  跟现在这个节点换一下
            TreeNode minP = p.rightChild;
            TreeNode minPP = p;

            while (minP.leftChild != null) {
                minPP = minP;
                minP = minPP.leftChild;
            }

            // 现在minP就是 右子树里面最小的那个节点 了
            // 1.1 先把这个节点的值，赋值给删除节点
            p.data = minP.data;

            // 1.2 删除这个 最小节点
            minPP.leftChild = null;

            return;
        }

        // 2.要删除的节点没有子节点 或 只有一个叶子节点
        // 那直接挂过去就行了
        TreeNode child;
        if (p.leftChild != null) {
            child = p.leftChild;
        } else if (p.rightChild != null) {
            child = p.rightChild;
        } else {
            child = null;
        }

        if (pp == null) {
            // 删除的是根节点
            root = child;
        } else if (pp.leftChild == p) {
            pp.leftChild = child;
        } else {
            pp.rightChild = child;
        }
    }

    public static void main(String[] args) {

        insert(1);
        insert(2);
        insert(3);
        insert(4);
        insert(5);
        insert(6);
        insert(11);
        insert(12);
        insert(13);
        insert(14);
        insert(15);
        insert(16);

        System.out.println("=============原始树=================");
        inOrder(root);
        System.out.println("=====================================");

        System.out.println("=============添加99=================");
        insert(99);
        inOrder(root);
        System.out.println("=====================================");

        System.out.println("=============删除12=================");
        delete(12);
        inOrder(root);
        System.out.println("=====================================");

        System.out.println("=============查询11=================");
        TreeNode node = find(11);
        visited(node);
        System.out.println("=====================================");

    }
}

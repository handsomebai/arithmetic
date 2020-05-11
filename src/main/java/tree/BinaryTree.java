package tree;

import java.util.*;

/**
 * @author fanbaicheng
 * @since 2020/5/11 19:59
 */
public class BinaryTree {

    /**
     * 前序遍历
     * 自己 -> 左子树 -> 右子树
     */
    public static void preOrder(TreeNode node) {
        if (node != null) {
            visited(node);
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    /**
     * 非递归前序遍历
     *
     * 自己实现压栈
     */
    public static void noRecPreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pNode = node;
        while (pNode != null || stack.size() > 0) {

            while (pNode != null) {

                // 先打印自己
                visited(pNode);
                // 将自己压入栈
                stack.push(pNode);
                // 依次把左子树都执行一遍
                pNode = pNode.leftChild;
            }

            // 此时 最后一个节点的左子树已经都打印完了
            // 开始打印右子树
            if (stack.size() > 0) {
                pNode = stack.pop();
                pNode = pNode.rightChild;
            }
        }
    }

    /**
     * 中序遍历
     * 左子树 -> 自己 -> 右子树
     */
    public static void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.leftChild);
            visited(node);
            inOrder(node.rightChild);
        }
    }

    /**
     * 非递归中序遍历
     *
     * 自己实现压栈
     */
    public static void noRecInOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pNode = node;
        while (pNode != null || stack.size() > 0) {

            while (pNode != null) {

                // 先把左子树压栈
                stack.push(pNode);
                // 一直压倒左子树最后一个节点
                pNode = pNode.leftChild;
            }

            // 此时 最后一个节点的左子树都已经压进来了
            // 打印自己，将自己的右子树压栈，下一轮循环，先出栈的，就是自己的右子树
            if (stack.size() > 0) {
                pNode = stack.pop();
                visited(pNode);
                pNode = pNode.rightChild;
            }
        }
    }

    /**
     * 后序遍历
     * 左子树 -> 右子树 -> 自己
     */
    public static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            visited(node);
        }
    }

    /**
     * 非递归后序遍历
     *
     * 自己实现压栈
     */
    public static void noRecPostOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pNode = node;
        TreeNode lastVisit = node;
        while (pNode != null || !stack.isEmpty()) {

            while (pNode != null) {

                // 先把左子树压栈
                stack.push(pNode);
                // 一直压倒左子树最后一个节点
                pNode = pNode.leftChild;
            }

            // 查看当前栈顶元素
            pNode = stack.peek();

            // 如果其右子树也为空，或者右子树已经被访问
            // 那么就可以直接打印自己
            if (pNode.rightChild == null || pNode.rightChild == lastVisit) {
                visited(pNode);
                stack.pop();
                lastVisit = pNode;
                pNode = null;
            } else {
                // 否则，继续遍历右子树
                pNode = pNode.rightChild;
            }
        }
    }

    /**
     * 层序遍历
     */
    public static void layerOrder(TreeNode node) {

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(node);

        while (!queue.isEmpty()) {

            TreeNode current = queue.pop();
            // 先打印自己
            visited(current);
            //在将自己的所有节点一次入队列
            if (current.leftChild != null) {
                queue.add(current.leftChild);
            }
            if (current.rightChild != null) {
                queue.add(current.rightChild);
            }
        }
    }

    /**
     * 打印节点
     */
    private static void visited(TreeNode node) {
        node.isVisited = true;
        System.out.println(node.data+","+node.key);
    }

    /**
     * 计算树的高度
     */
    private int height(TreeNode node){
        if(node == null){
            return 0;
        }else{
            int i = height(node.leftChild);
            int j = height(node.rightChild);
            return (i<j)?j+1:i+1;
        }
    }

    /**
     * 计算树的节点数
     */

    private int size(TreeNode node){
        if(node == null){
            return 0;
        }else{
            return 1+size(node.leftChild)+size(node.rightChild);
        }
    }

    /**
     * 创建二叉树
     */
    public static void createBinaryTree(TreeNode root){
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");
        TreeNode nodeG = new TreeNode(7, "G");
        TreeNode nodeH = new TreeNode(8, "H");
        TreeNode nodeI = new TreeNode(9, "I");
        TreeNode nodeJ = new TreeNode(10, "J");
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeD.leftChild = nodeH;
        nodeD.rightChild = nodeI;
        nodeE.leftChild = nodeJ;
        nodeC.leftChild = nodeF;
        nodeC.rightChild = nodeG;
    }

    /**
     * 定义根节点
     */
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public BinaryTree() {
        this.root = new TreeNode(1, "A");
    }

    /**
     * 节点数据结构
     */
    private static class TreeNode {
        private int key = 0;
        private String data = null;
        private boolean isVisited = false;
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;


        public TreeNode(){}

        public TreeNode(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.root;
        createBinaryTree(root);
        System.out.println("=============前序遍历=================");
        preOrder(root);
        System.out.println("=====================================");
        System.out.println("=============中序遍历=================");
        inOrder(root);
        System.out.println("=====================================");
        System.out.println("=============后续遍历=================");
        postOrder(root);
        System.out.println("=====================================");

        System.out.println("=============前序遍历 非递归=================");
        noRecPreOrder(root);
        System.out.println("=====================================");
        System.out.println("=============中序遍历 非递归=================");
        noRecInOrder(root);
        System.out.println("=====================================");
        System.out.println("=============后续遍历 非递归=================");
        noRecPostOrder(root);
        System.out.println("=====================================");

        System.out.println("=============层序遍历 非递归=================");
        layerOrder(root);
        System.out.println("=====================================");
    }
}

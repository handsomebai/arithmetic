package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/18 10:51
 */
public class No725 {

    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(){}
        public ListNode(int value){
            this.value = value;
        }
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        // 先计算数组长度
        int length = 0;
        ListNode current = root;
        while (current != null) {
            length = length + 1;
            current = current.next;
        }

        // 用长度除以k，就是分组的个数
        int perCount = length / k;
        int rest = length % k;

        // k组 每组几个
        int[] nums = new int[k];

        for (int i = 0; i < k; i++) {
            if (rest > 0) {
                nums[i] = perCount + 1;
            } else {
                nums[i] = perCount;
            }
            rest = rest - 1;
        }

        ListNode[] result = new ListNode[k];

        ListNode cur = root;
        // 开始截取链表
        for (int i = 0; i < k; i++) {
            int num = nums[i];
            ListNode last = get(cur, num);

            if (last == null) {
                result[i] = null;
                continue;
            }
            ListNode r = cur;
            cur = last.next;

            // 将尾节点指向null，放入返回数组
            last.next = null;
            result[i] = r;
        }

        return result;
    }

    // node 为当前节点
    // num为截取几个
    // 返回第num个节点
    public ListNode get(ListNode node, int num) {

        if (num == 0 || node == null) {
            return null;
        }

        ListNode current = node;
        while (num != 1 && node.next != null) {
            current = current.next;
            num = num - 1;
        }

        return current;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        No725 n = new No725();
        ListNode[] result = n.splitListToParts(head, 3);
        System.out.println(result);
    }
}

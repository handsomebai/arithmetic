package leetCode;

/**
 * @author fanbaicheng
 * @since 2020/6/26 16:37
 */
public class No25 {

    static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode current = head;
        ListNode soldier = new ListNode(-1);
        ListNode so = soldier;

        // 先判断当前节点能否反转
        while (canGetK(current, k)){

            ListNode tail = current;
            ListNode back = null;
            ListNode fast = null;
            // 反转k个链表
            int count = k;
            while(count-- > 0) {
                fast = current.next;
                current.next = back;
                back = current;
                current = fast;
            }

            tail.next = fast;
            so.next = back;
            so = tail;
        }

        return soldier.next;
    }



    // 判断是否有k个数据
    // node为第一个要反转的元素
    public boolean canGetK(ListNode node, int k) {
        while (k > 0 && node != null) {
            k--;
            node = node.next;
        }

        return k == 0;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        /*head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);*/

        No25 n = new No25();
        n.reverseKGroup(head, 2);
    }
}

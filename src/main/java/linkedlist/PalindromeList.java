package linkedlist;

/**
 * @author fanbaicheng
 * @description 断是否是回文链表  case: 单链表反转
 * @since 2020/3/27 10:36
 */
public class PalindromeList extends SingleLinkedList {

    /**
     * 判断是否是回文链表
     * 思路：
     *  1、先找到链表的中点（分奇数个节点和偶数个节点）
     *  2、将链表分为左链表 和 右链表
     *  3、反转其中一个链表
     *  4、然后逐个遍历比对左右链表的值是否相同
     */
    public boolean palindrome() {

        /**
         * step 1: 找到中点
         * 思路：快慢指针，快指针走两步，慢指针走一步，当快指针走到最后的时候，慢指针就在中点了
         */
        if (head == null) {
            return false;
        }

        Node front = head;
        Node back = head;

        // 只有一个节点
        if (front.next == null) {
            return true;
        }

        while(front.next != null && front.next.next != null) {
            front = front.next.next;
            back = back.next;
        }

        Node rightLink = null;
        Node leftLink = null;

        if (front.next == null) {
            // 快指针下个节点是null -> 奇数节点   case: 1->2->3->null  front指向3

            /**
             * step 2: 分左右链表
             * 奇数节点：抛弃中间节点  向前一位为右  向后一位为左
             *
             * step 3: 链表反转
             * 奇数节点：现在拿不到中间节点向前一位  所以用中间节点把左链表进行反转  反转后: 2->1->null  然后再向前进一位，就抛弃了原中间节点  变成：1->null。 右链表不动: 3->null
             */
            rightLink = back.next;
            leftLink = inverseLinkList(back).next;


        } else {
            // 偶数节点  case: 1->2->3->4->null   front指向3的时候跳出

            /**
             * step 2: 分左右链表
             * 偶数节点：中间节点指向2 刚好是左链表的末尾   下一个节点是右链表的头  所以不需要抛弃中间节点
             *
             * step 3: 链表反转
             * 偶数节点：直接将左链表进行反转 变成: 2->1->null  右链表不动：3—>4->null
             */
            rightLink = back.next;
            leftLink = inverseLinkList(back);
        }

        /**
         * step 4: 逐个遍历比对左右链表的值是否相同
         */
        return equals(leftLink, rightLink);
    }

    /**
     * 无头节点的链表反转
     * @param endNode 最后节点标记
     * @return dead->endNode 的反转链表
     *
     * 思路：前节点 当前节点  后节点 穿针引线法
     */
    public Node inverseLinkList(Node endNode){

        Node back = null;
        Node current = head;
        Node front;

        // 当前节点为endNode的时候结束遍历
        while (current != endNode) {

            // 先把前节点就位
            front = current.next;

            // 当前节点断开与前节点的关系，将next指向后节点
            // 到这一步 已经反转完了 将指针移动到下一次的前中后
            current.next = back;

            // back = 现在的当前节点
            back = current;

            // current = 下次要反转的节点 也就是现在的front节点
            current = front;
        }

        // 此时当前节点还没有被反转, 需要手动反转一下
        current.next = back;

        return current;
    }

    /**
     * 判断两个链表是否相等
     */
    public boolean equals(Node leftLink, Node rightLink) {

        // 当都不为空的时候，进入循环
        while (leftLink != null && rightLink != null) {

            if(leftLink.data != rightLink.data) {
                return false;
            }

            leftLink = leftLink.next;
            rightLink = rightLink.next;
        }

        if (leftLink == null && rightLink == null) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        PalindromeList palindromeList1 = new PalindromeList();
        palindromeList1.insertToTail(1);
        palindromeList1.insertToTail(2);
        palindromeList1.insertToTail(3);
        palindromeList1.insertToTail(2);
        palindromeList1.insertToTail(1);
        palindromeList1.print();
        System.out.println("是否回文:" + palindromeList1.palindrome());
        System.out.println("================================");

        PalindromeList palindromeList2 = new PalindromeList();
        palindromeList2.insertToTail(1);
        palindromeList2.insertToTail(2);
        palindromeList2.insertToTail(2);
        palindromeList2.insertToTail(1);
        palindromeList2.print();
        System.out.println("是否回文:" + palindromeList2.palindrome());
        System.out.println("================================");

        PalindromeList palindromeList3 = new PalindromeList();
        palindromeList3.insertToTail(1);
        palindromeList3.insertToTail(2);
        palindromeList3.insertToTail(4);
        palindromeList3.insertToTail(5);
        palindromeList3.insertToTail(2);
        palindromeList3.insertToTail(1);
        palindromeList3.print();
        System.out.println("是否回文:" + palindromeList3.palindrome());
        System.out.println("================================");

        PalindromeList palindromeList4 = new PalindromeList();
        palindromeList4.insertToTail(1);
        palindromeList4.print();
        System.out.println("是否回文:" + palindromeList4.palindrome());
        System.out.println("================================");
    }

}

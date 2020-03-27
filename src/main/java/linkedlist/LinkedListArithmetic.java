package linkedlist;

/**
 * @auther fanbaicheng
 * @description 常见链表算法
 * @since 2020/3/27 13:26
 *
 * 1、单链表反转
 * 2、链表中环的检测
 * 3、两个有序的链表合并
 * 4、删除链表倒数第n个结点
 * 5、求链表的中间结点
 */
public class LinkedListArithmetic extends SingleLinkedList {

    /**
     * 单链表反转
     *
     * 思路：
     *  1、前中后指针
     *  2、穿针引线
     *  2、当中指针走到最后的时候 将尾节点反转
     */
    public static Node reverse(Node head) {

        Node back = null;
        Node current = head;
        Node front;

        while (current.next != null) {

            // 先将下个节点找到，因为前节点要断开与后节点的链接，先用一个指针守着，防止下个节点丢了
            front = current.next;

            // 将当前节点的next，指向前一个节点。 此时current已完成反转 且  与下一个节点断开了链接
            current.next = back;

            // 将 back 和 current指针 向前移。为下一个节点的反转做准备。 back先移，current先移的话，back将丢失目标
            back = current;
            current = front;
        }

        // 此时current.next为空。也就是current在最后一个节点。把最后一个节点翻转
        current.next = back;

        return current;
    }

    /**
     * 环检测
     *
     * 思路：
     *  1、快慢指针
     *  2、快指针走两步 慢指针走一步
     *  3、当快指针追上慢指针的时候 也就是 快指针==慢指针 的时候  说明有环
     */
    public static boolean checkCircle(Node head) {

        if (head == null) {
            return false;
        }

        Node fast = head;
        Node slow = head;

        while (fast.next != null && fast.next.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (slow.data == fast.data) {
                return true;
            }
        }

        // 出来之后就不用判断了，能出来，必然有终点，必然不是环
        return false;
    }

    /**
     * 两个有序的链表合并
     *
     * 思路:
     *  1、开一个新链表
     *  2、取两链表节，小的放入新链表，小元素链表向前移动
     *  3、直到一个链表被取完，另一个链表剩下的元素直接挂到新链表尾就可以了
     */
    public static Node mergeTwoListsBySoldier(Node l1, Node l2) {

        // 新链表的头，不知道先取l1还是l2 所以得先判断一遍l1和l2的第一个节点才能知道新链表的head的第一个元素 所以需要先判断第一个节点在while循环
        // 为了不做这一次判断，引入哨兵节点的概念
        // 什么意思呢，既然痛点是head节点不知道绑谁身上，那我绑哨兵身上好了
        Node soldier = createNode(-1);
        Node index = soldier;

        while (l1 != null && l2 != null) {

            if (l1.data < l2.data) {
                index.next = l1;
                l1 = l1.next;
            } else {
                index.next = l2;
                l2 = l2.next;
            }

            index = index.next;
        }

        if (l1 == null) {
            index.next = l2;
        }

        if (l2 == null) {
            index.next = l1;
        }

        return soldier.next;
    }

    /**
     * 两个有序的链表合并 不带哨兵
     */
    public Node mergeTwoLists(Node l1, Node l2) {

        // 带哨兵时分析过，不带哨兵的痛点就是事先的判断一遍head指向谁
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        Node head = null;
        if (l1.data < l2.data) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        Node index = head;

        // 以下代码 和带哨兵的一样
        while (l1 != null && l2 != null) {

            if (l1.data < l2.data) {
                index.next = l1;
                l1 = l1.next;
            } else {
                index.next = l2;
                l2 = l2.next;
            }

            index = index.next;
        }

        if (l1 == null) {
            index.next = l2;
        }

        if (l2 == null) {
            index.next = l1;
        }

        return head;

    }

    /**
     * 删除链表倒数第n个结点
     *
     * 思路：
     *  1、游标卡尺
     *  2、定义一个距离k的前后指针 一步一步走
     *  3、当前指针指到最后一位的时候，后指针就是倒数第k位
     *  4、知道第k位还不行，得记录k-1位，这样k-1.next = k-1.next.next 就能把第k位删除了
     */
    public static Node deleteLastKth(Node list, int k) {

        // 找到前指针的位置
        Node front = list;
        for (int i = 0; front != null && i < k; i++) {
            front = front.next;
        }

        // 如果前指针为空，说明整个链表都没有k个节点，不需要删除将原链表返回
        if (front == null) {
            return list;
        }

        Node slow = list;
        Node back = null;
        while (front.next != null) {
            back = slow;
            slow = slow.next;
            front = front.next;
        }

        //当删除的节点为第一个节点的时候，也就是没有进while呢
        if (back == null) {
            list = list.next;
        } else {
            back.next = back.next.next;
        }

        return list;
    }

    public static void main(String[] args) {

        System.out.println("==============链表反转=================");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.insertToTail(1);
        list1.insertToTail(2);
        list1.insertToTail(3);
        list1.insertToTail(4);
        list1.print();
        list1.head = reverse(list1.head);
        list1.print();
        System.out.println("======================================");
        System.out.println();

        System.out.println("==============链表环判断================");
        SingleLinkedList list2 = new SingleLinkedList();
        list2.insertToTail(1);
        list2.insertToTail(2);
        list2.insertToTail(3);
        list2.insertToTail(4);
        list2.head.next.next.next.next = list2.head;
        System.out.println(checkCircle(list2.head));
        System.out.println(checkCircle(list1.head));
        System.out.println("======================================");
        System.out.println();

        System.out.println("==========两个有序的链表合并=============");
        SingleLinkedList list3 = new SingleLinkedList();
        list3.insertToTail(2);
        list3.insertToTail(3);
        list3.insertToTail(5);
        list3.print();

        SingleLinkedList list4 = new SingleLinkedList();
        list4.insertToTail(1);
        list4.insertToTail(2);
        list4.insertToTail(5);
        list4.insertToTail(8);
        list4.print();

        SingleLinkedList merge = new SingleLinkedList();
        merge.head = mergeTwoListsBySoldier(list3.head, list4.head);
        merge.print();
        System.out.println("======================================");
        System.out.println();

        System.out.println("==========删除链表倒数第n个结点=============");
        SingleLinkedList list5 = new SingleLinkedList();
        list5.insertToTail(1);
        list5.insertToTail(2);
        list5.insertToTail(5);
        list5.insertToTail(8);
        list5.print();
        list5.head = deleteLastKth(list5.head, 1);
        list5.print();
        System.out.println("======================================");
        System.out.println();
    }
}

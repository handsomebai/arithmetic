package leetCode;

import linkedlist.SingleLinkedList;

/**
 * @author fanbaicheng
 * @since 2020/5/29 17:33
 */
public class No92 extends SingleLinkedList {

    public static Node reverseBetween(Node head, int m, int n) {

        // 如果是空链表
        if (head == null) {
            return null;
        }

        // 先找到m的位置
        Node back = null;
        Node current = head;
        Node fast = head.next;

        while(m > 1) {
            back = current;
            current = fast;
            fast = fast.next;

            m--;
            n--;
        }

        // 现在current的位置就是m的位置
        // 定义con指针
        Node con = back;
        Node tail = current;

        // 开始链表反转
        while(n > 0) {

            current.next = back;
            back = current;
            current = fast;
            fast = fast.next;

            n--;
        }

        // 现在子串反转好了

        if (con != null) {
            con.next = back;
        } else {
            head = back;
        }

        tail.next = current;

        return head;
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();

        // [1,2,3,4,5]

        // 头添加两个元素12  链表 2->1
        linkedList.insertToTail(1);
        linkedList.insertToTail(2);
        linkedList.insertToTail(3);
        linkedList.insertToTail(4);
        linkedList.insertToTail(5);

        reverseBetween(linkedList.head, 2, 4);

        linkedList.print();
    }
}

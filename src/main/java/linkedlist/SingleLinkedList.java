package linkedlist;

/**
 * @auther fanbaicheng
 * @description 单链表操作
 * @since 2020/3/25 18:54
 */
public class SingleLinkedList {

    /**
     * 链表
     */
    public Node head = null;

    /**
     * 根据value查询node
     */
    public Node findByValue(int value) {

        Node index = head;
        while (index != null && index.data != value) {
            index = index.next;
        }

        return index;
    }

    /**
     * 根据id查询node
     */
    public Node findByIndex(int index) {

        Node current = head;
        int i = 0;
        while (current != null && i != index) {
            current = current.next;
            i++;
        }

        return current;
    }

    /**
     * 无头链表 --> head就是头节点
     * 链表头插入
     */
    public void insertToHead(int value) {
        Node newNode = createNode(value);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {

        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    /**
     * 无头链表
     * 链表尾插入
     */
    public void insertToTail(int value) {
        Node newNode = createNode(value);
        insertToTail(newNode);
    }

    public void insertToTail(Node newNode) {

        if (head == null) {
            head = newNode;
            return;
        }

        Node index = head;

        while (index.next != null){
            index = index.next;
        }

        index.next = newNode;
    }

    /**
     * 在某个节点后添加node
     */
    public void insertAfter(Node p, int value) {
        Node newNode = createNode(value);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {

        if (p == null) {
            return;
        }

        newNode.next = p.next;
        p.next = newNode;
    }

    /**
     * 在某个节点之前添加node
     */
    public void insertBefore(Node p, int value) {
        Node newNode = createNode(value);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {

        if (p == null) {
            return;
        }

        if (head == p) {
            insertToHead(newNode);
            return;
        }

        Node index = head;

        while (index != null && index.next != p) {
            index = index.next;
        }

        // 如果p在链表中不存在
        if (index == null) {
            return;
        }

        newNode.next = p;
        index.next = newNode;
    }

    /**
     * 删除某个节点
     */
    public void delByNode(Node delNode) {

        if (delNode == null || head == null) {
            return;
        }

        // 如果delNode是头节点
        if (delNode == head) {
            head = head.next;
        }

        Node index = head;

        while(index != null && index.next != delNode) {
            index = index.next;
        }

        // delNode 在链表中不存在
        if (index == null) {
            return;
        }

        index.next = index.next.next;
    }

    /**
     * 删除指定值的节点
     * 注意：链表里可能存在多个指定的值
     */
    public void delByValue(int delValue) {

        if (head == null) {
            return;
        }

        // 使用前后指针
        Node front = head;
        Node back = null;

        while (front != null && front.data != delValue) {
            back = front;
            front = front.next;
        }

        // 如果 delValue 值不存在
        if (front == null) {
            return;
        }

        // 第一个节点就是删除节点  后指针为空
        if (back == null) {
            head = head.next;
        } else {
            back.next = front.next;
        }

        // 这里递归把所有相同的数去掉
        delByValue(delValue);
    }

    /**
     * 打印链表
     */
    public void print() {

        if (head == null) {
            System.out.println("null linked ist");
            return;
        }

        Node index = head;

        while (index != null) {
            System.out.print(index.data + "->");
            index = index.next;
        }
        System.out.println();
    }

    /**
     * 定义链表node
     */
    public static class Node {
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 节点工厂
     */
    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();

        // 空链表打印
        System.out.println("空链表打印");
        linkedList.print();
        System.out.println("=================================");

        // 头添加两个元素12  链表 2->1
        System.out.println("头添加12");
        linkedList.insertToHead(1);
        linkedList.insertToHead(2);
        linkedList.print();
        System.out.println("=================================");

        // 尾添加两个元素341 链表 2->1->3->4->1
        System.out.println("尾添加341");
        linkedList.insertToTail(3);
        linkedList.insertToTail(4);
        linkedList.insertToTail(1);
        linkedList.print();
        System.out.println("=================================");

        // 删除3 链表 2->1->4->1
        System.out.println("删除3");
        linkedList.delByValue(3);
        linkedList.print();
        System.out.println("=================================");

        //删除4 链表 2->1->1
        System.out.println("删除4");
        linkedList.delByValue(4);
        linkedList.print();
        System.out.println("=================================");

        //删除2 链表 1->1
        System.out.println("删除2");
        linkedList.delByValue(2);
        linkedList.print();
        System.out.println("=================================");

        //删除1 链表 null
        System.out.println("删除1");
        linkedList.delByValue(1);
        linkedList.print();
        System.out.println("=================================");

    }
}

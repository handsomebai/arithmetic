package test;

import leetCode.No725;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fanbaicheng
 * @since 2020/6/19 11:32
 */
public class Test2 {

    /**
     * 录音系统
     * 文本 几百万量级
     *
     * 请求一次 获取一个文本
     *
     * 上传一次
     *
     *
     *
     * zk。  zk   offset.log
     *
     * 二分查找找这个offset  读文本
     *
     *
     * 1、生成文本
     * 策略生成  offset头  头记录到一个地方。磁盘。keyWrite->offset
     * redis map
     *
     * void rpc（ String str ）{
     *     int x  = hash(str);
     *     long offset = write(str);
     *
     *     // redis
     *     map(x, str);
     *
     *     // mysql
     *     把 k,str 存到从db
     * }
     *
     * redis
     *
     * 2、search文本  -> readKey -> keyWrite  tireTree
     * 策略。offset  key ->
     *
     * 文件的offset
     *
     * userId
     * mysql
     *
     * userId (input);
     *
     * trieTree
     *
     * 读（语言流）
     * 先根据
     *
     * 上传到服务端
     *
     *
     * 一次：一个文本只被读一次
     *
     * Queue(（1, stream) ,2,3,4);
     *
     * true
     *
     * deadQueue(5)
     *
     *
     * 表1 存 userId -> readList
     * id
     *
     * 表2 id++   存 userId -> readlistKey offset  行
     *
     * user -> offset , next  表1 line 表2 offset++  - > 用户
     *
     * A id: 56  57
     *
     * B id: 57
     *
     * C id: 1, +1, 56
     *
     * // 顺序读 +1
     *
     * redis userId +1 id()。 （全 - 已读 - 废弃） random id
     *
     * 最大值 id
     * redis
     *
     * redis set  废弃的id。
     *
     * 被读id，存redis。
     *
     * 可读全集  random  id
     *
     * mysql redis问题。
     *
     *
     * 原子性：
     * 1、写加锁 悲观锁
     * 2、自旋 cas 乐观锁
     *
     *
     *
     * 表3 存用户读的stream流
     * 表3 user，offset， bin
     *
     * String get(key x){
     *    rerurn get(x);
     * }
     *
     *
     *
     * 3、get文本 （业务逻辑）
     *
     * offset读磁盘，或者读内存
     */

    static class Node {
        public int val;

        public Node next;

        public Node(){}

        public  Node(int val){
            this.val = val;
        }
    }

    // hashMap List Set

    // 两个链表。找相交的点
    // 1 -> 2 -> 3 -> 4
    // 6 -> 3 -> 5

    public Node getExchange(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }

        Set<Node> set = new HashSet<>();

        Node current1 = head1;
        // step1 : 先遍历head1
        while (current1 != null) {
            set.add(current1);
            current1 = current1.next;
        }

        // step2 : 遍历第二个链表
        Node current2 = head2;
        while (current2 != null) {

            if (set.contains(current2)) {
                return current2;
            }

            current2 = current2.next;
        }

        return null;
    }



}

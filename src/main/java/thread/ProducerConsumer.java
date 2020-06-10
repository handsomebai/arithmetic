package thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fanbaicheng
 * @since 2020/6/10 12:03
 */
public class ProducerConsumer {



    public static void main(String[] args) {

        final Lock lock = new ReentrantLock();
        final Condition full = lock.newCondition();
        final Condition empty = lock.newCondition();

        final int max = 10;
        final LinkedList<Integer> list = new LinkedList();


        /**
         * 生产者
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        // 上锁
                        lock.lock();

                        // 如果队列满了，阻塞
                        if (list.size() > max) {
                            full.await();
                        }

                        // 如果队列没满, 添加
                        int in = new Random().nextInt();
                        list.add(in);
                        System.out.println("添加" + in);

                        //添加完，通知empty
                        empty.signal();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }

            }
        }).start();

        /**
         * 消费者
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        // 上锁
                        lock.lock();

                        // 如果队列没数据，阻塞
                        if (list.size() == 0) {
                            empty.await();
                        }

                        // 如果队列有数据, 获取
                        System.out.println("获取" + list.getFirst());
                        list.removeFirst();

                        //添加完，通知empty
                        full.signal();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }

            }
        }).start();
    }
}

package syndemo19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add和size，写两个线程，线程1添加10个元素到这个容器中，线程2监控元素的个数，当个数为5个时，线程2给出提示并结束。
 */
public class MyContainer3 {
    /**
     * 调用的是加锁的对象(lock)的wait方法和notify方法。wait会释放锁，notify和sleep不会释放锁。
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer3 mc = new MyContainer3();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 开始");
                if (mc.size() != 5) {
                    try {
                        /**
                         * t1线程里面notify了，会叫醒t2线程从wait的这个地方开始执行。哪里睡的又会从哪里醒来。
                         * 问题：t2这里wait了，释放了锁，t1里面会拿到锁，但是t2醒来之后，锁还在t1里面，t2是不能拿到锁的，
                         * t2只能等t1结束了才能，释放了lock的锁，t2才能拿到锁。详情见打印结果。
                         */
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
            }
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    mc.add(new Object());
                    System.out.println("add" + i);
                    if (mc.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1结束");
            }
        }, "t1").start();
    }
}

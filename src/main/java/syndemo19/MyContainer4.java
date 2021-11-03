package syndemo19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add和size，写两个线程，线程1添加10个元素到这个容器中，线程2监控元素的个数，当个数为5个时，线程2给出提示并结束。
 * t2线程先等待，t1线程执行，符合条件时，t1线程叫醒t2线程，t1线程等待释放锁，t2线程拿到锁，t2线程打印结束信息，t2线程叫醒t1线程，
 * t1线程做后续打印。即：t2wait--->t1run--->t1notify--->t1wait--->t2run--->t2notify,t2结束，锁释放了--->t1run--->t1结束,锁释放了。
 */
public class MyContainer4 {
    /**
     * 添加volatile使t2线程能够得到通知
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer4 mc = new MyContainer4();
        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 开始");
                if (mc.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();//叫醒t1线程
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
                        try {
                            lock.wait();//t1线程等待，释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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

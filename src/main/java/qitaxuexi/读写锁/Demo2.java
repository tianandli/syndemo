package qitaxuexi.读写锁;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能描述：读写锁表示有两个锁，一个是读操作相关的锁，也称为共享锁；另一个是写操作相关的锁，也叫排他锁。
 * 也就是多个读锁之间不互斥，读锁与写锁互斥、写锁与写锁互斥
 * 写写互斥————写锁与写锁互斥，这就类似于ReentrantLock的作用效果。
 * 调用结果如下：只有某一个释放了锁，其他的才能占用锁
 * threadName -> t1占用写锁,i->0
 * threadName -> t1释放写锁,i->1
 * threadName -> t2占用写锁,i->1
 * threadName -> t2释放写锁,i->2
 * threadName -> t3占用写锁,i->2
 * threadName -> t3释放写锁,i->3
 *
 *
 * @author: lijie
 * @date: 2021/7/2 8:52
 * @version: V1.0
 */
public class Demo2 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁

    private int i;

    public void addI() {
        try {
            lock.writeLock().lock();// 占用写锁
            System.out.println("threadName -> "+Thread.currentThread().getName()+ "占用写锁,i->" + i);
            Thread.sleep(2 * 1000);
            i++;
        } catch (InterruptedException e) {

        } finally {
            System.out.println("threadName -> "+Thread.currentThread().getName()+ "释放写锁,i->" + i);
            lock.writeLock().unlock();// 释放写锁
        }
    }

    public static void main(String[] args) {
        final Demo2 demo1 = new Demo2();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                demo1.addI();
            }
        };

        new Thread(runnable, "t1").start();
        new Thread(runnable, "t2").start();
        new Thread(runnable, "t3").start();

    }

}
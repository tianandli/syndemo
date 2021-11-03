package qitaxuexi.读写锁;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能描述：读写锁表示有两个锁，一个是读操作相关的锁，也称为共享锁；另一个是写操作相关的锁，也叫排他锁。
 * 也就是多个读锁之间不互斥，读锁与写锁互斥、写锁与写锁互斥
 * 读读共享————读锁与读锁可以共享，这种锁一般用于只读操作，不对变量进行修改操作
 *
 * 调用结果：可以同时占用锁
 * threadName -> t1 占用读锁,i->0
 * threadName -> t3 占用读锁,i->0
 * threadName -> t2 占用读锁,i->0
 * threadName -> t2 释放读锁,i->0
 * threadName -> t1 释放读锁,i->0
 * threadName -> t3 释放读锁,i->0
 *
 * @author: lijie
 * @date: 2021/7/2 8:52
 * @version: V1.0
 */
public class Demo1 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁
    private int i;

    public String readI() {
        try {
            lock.readLock().lock();// 占用读锁()
            System.out.println("threadName -> "+Thread.currentThread().getName()+" 占用读锁,i->" + i);
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            System.out.println("threadName -> "+Thread.currentThread().getName()+" 释放读锁,i->" + i);
            lock.readLock().unlock();// 释放读锁
        }
        return i + "";
    }

    public static void main(String[] args) {
        final Demo1 demo1 = new Demo1();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                demo1.readI();
            }
        };

        new Thread(runnable, "t1").start();
        new Thread(runnable, "t2").start();
        new Thread(runnable, "t3").start();
    }

}

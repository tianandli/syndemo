package qitaxuexi.公平锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/6/30 16:03
 * @version: V1.0
 */
public class FairLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);//这里只要设置为true就是公平锁.具体效果看控制台
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new FairLock(), "线程一");
        Thread thread2 = new Thread(new FairLock(), "线程二");
        thread1.start();
        thread2.start();
    }
}

package qitaxuexi.可重入锁;

import java.util.concurrent.locks.ReentrantLock;

/**2
 * 功能描述：通过可重入锁来实现synchronized的效果
 *
 * @author: lijie
 * @date: 2021/6/30 14:12
 * @version: V1.0
 */
public class ReentrantLockTest extends Thread {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 5; j++) {
            lock.lock();//线程一和线程二都会在各自的线程里走到这里，然后线程一获取到了lock的监视器，线程一就去做加一的操作，线程二等待，线程一走完后线程二再执行。
            try {
                System.out.println(this.getName() + " --- " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest thread1 = new ReentrantLockTest();
        ReentrantLockTest thread2 = new ReentrantLockTest();
        thread1.setName("线程一");
        thread2.setName("线程二");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName()+"   i的最终值是："+i);
    }
}


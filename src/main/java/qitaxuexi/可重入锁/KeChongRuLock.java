package qitaxuexi.可重入锁;

import java.util.concurrent.locks.ReentrantLock;

/**1
 * 功能描述：通过可重入锁来实现synchronized的效果
 *
 * @author: lijie
 * @date: 2021/6/30 13:56
 * @version: V1.0
 */
public class KeChongRuLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();//创建一个可重入锁
    public static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 2; j++) {
            //通过可重入锁来使得i的操作是安全的。下面的lock.lock();是可以多次加锁的，下面的解锁也要对应次数一致
            //这里有两个线程：线程一和线程二，都要去获取lock对象的监视器，哪个获取到了哪个就能进来。
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new KeChongRuLock(),"线程一");
        Thread thread2 = new Thread(new KeChongRuLock(),"线程二");
        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread().getName()+"   i的最终值是："+i);
    }
}

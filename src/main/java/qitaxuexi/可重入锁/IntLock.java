package qitaxuexi.可重入锁;

import java.util.concurrent.locks.ReentrantLock;

/**3
 * 功能描述：可重入锁的中断响应:通过外部通知的方式来避免死锁.synchronized如果第一个线程在执行,第二个线程只能等第一个线程执行完了才能执行.
 * 使用可重入锁,第二个线程可以被中断,也就是说第二个线程在等待的时候,可以取消请求让第二个线程不去获取锁了.
 *
 *  lock.lockInterruptibly()尝试获取锁,但优先响应中断。如果当前有别的线程获取了锁，则睡眠。当该函数返回时，有两种可能：
 *  a.已经获取了锁
 *  b.获取锁不成功，但是别的线程打断了它。则该线程会抛出IterruptedException异常而返回，同时该线程的中断标志会被清除。
 *  lock.isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定
 * @author: lijie
 * @date: 2021/6/30 14:59
 * @version: V1.0
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();//创建一个可重入锁
    public static ReentrantLock lock2 = new ReentrantLock();//创建一个可重入锁
    int lock;

    //控制加锁顺序，方便构造死锁
    public IntLock(int lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            //下面的if和else相当于构建了一个死锁状态,但是thread2被中断了interrupt,所以线程2主动的会退出,只有线程1是进入线程体内执行了run方法的
            //至于是线程1先退出还是线程2先退出,完全取决于线程1先抢到cpu还是线程2先抢到cpu
            if(lock == 1){
                lock1.lockInterruptibly();//尝试获取锁,但优先响应中断
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--11111");
                //主线程中给线程二设置了中断，这时候线程二获取了lock2的监听器（也就是锁），所以线程一在走到这里去获取lock2的监听器的时候，会响应中断，
                //这时候线程二就主动退出了，使得线程一能够走下去。避免了死锁的情况。
                lock2.lockInterruptibly();
                System.out.println("11111111");
            }else{
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--22222");
                lock1.lockInterruptibly();
                System.out.println("22222222");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock1.isHeldByCurrentThread()){//查询当前线程是否保持此锁定
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "退出了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new IntLock(1),"线程一");
        Thread thread2 = new Thread(new IntLock(2),"线程二");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();//中断线程3
    }
}

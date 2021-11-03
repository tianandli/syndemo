package qitaxuexi.可重入锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述：线程一和线程二,有一个先抢到了cpu,假设一先抢到了,那么线程一就拿到了锁,打印出获取锁成功,线程二也立马进来,
 * 但是线程一睡了6秒,线程二申请锁只会申请5秒的时候,所以这时候,线程二是拿不到锁的,所以线程二会走else里面的代码.
 * 最后线程一和线程二都会进finally里面,但是只有线程一是持有锁的,所以解锁的只有线程一.
 * 控制台打印如下:(当线程一先抢到了cup资源的情况)
 * 线程一尝试获取锁成功
 * 线程二获取锁失败
 * 线程一释放锁
 *
 * @author: lijie
 * @date: 2021/6/30 15:32
 * @version: V1.0
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            //线程获取锁只尝试5秒,如果超过5秒就不会再去获取锁.这里也可以不设置时间,不设置就是0秒
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"尝试获取锁成功");
                Thread.sleep(6000);
            }else{
                System.out.println(Thread.currentThread().getName()+"获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){//如果锁被当前线程持有,held是hold的被动式,就解锁
                System.out.println(Thread.currentThread().getName()+"释放锁");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new TimeLock(), "线程一");
        Thread thread2 = new Thread(new TimeLock(), "线程二");
        thread1.start();
        thread2.start();
    }
}

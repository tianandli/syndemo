package qitaxuexi.原子.boolean的原子;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 功能描述：
 * 打印如下：线程一进来先将exist修改为了true，线程二进来的时候，exist=true，与false比较不符，就不会进if直接走else。线程一会走完整个if
 * 线程一进来了，将exist设置为了false
 * 线程二放弃了
 * 线程一做了点事，离开了
 *
 * @author: lijie
 * @date: 2021/7/6 9:28
 * @version: V1.0
 */
public class WorkerThread2 implements Runnable{
    /**
     * 描述：能够保证在高并发的情况下只有一个线程能够访问这个属性值。（类似我们之前所说的volatile）。
     * 一般情况下，我们使用 AtomicBoolean 高效并发处理 “只初始化一次” 的功能要求
     */
    private static AtomicBoolean exist = new AtomicBoolean(false);//注意这里要用static修饰

    @Override
    public void run() {
        if(exist.compareAndSet(false, true)){
            System.out.println(Thread.currentThread().getName() + "进来了，将exist设置为了false");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "做了点事，离开了");
            exist.set(false);
        }else{
            System.out.println(Thread.currentThread().getName() + "放弃了");
        }
    }

    public static void main(String[] args) {
        new Thread(new WorkerThread2(),"线程一").start();
        new Thread(new WorkerThread2(),"线程二").start();
    }
}

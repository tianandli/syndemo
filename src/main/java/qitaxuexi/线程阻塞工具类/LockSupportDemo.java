package qitaxuexi.线程阻塞工具类;

import java.util.concurrent.locks.LockSupport;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/7/2 14:21
 * @version: V1.0
 */
public class LockSupportDemo {
    public static Object u = new Object();//用于加锁

    //静态内部类
    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run(){
            synchronized (u){
                System.out.println("现在跑的线程是：" + Thread.currentThread().getName());
                LockSupport.park();//将线程阻塞
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread("t1");
        ChangeObjectThread t2 = new ChangeObjectThread("t2");
        System.out.println("11111");
        t1.start();
        System.out.println("22222");
        Thread.sleep(4000);//主线程睡1秒
        t2.start();
        System.out.println("33333");
        /**
         * 注意：这里的取消t1和t2的阻塞，是在主线程中的，让t1和t2阻塞，是在子线程中的，这里是不能无法保证unpark()就发生在park()之后的，
         * 但是代码是可以正常结束的，不会因为park()方法阻塞了而永久的挂起。
         */
        LockSupport.unpark(t1);
        System.out.println("44444");
        LockSupport.unpark(t2);
        System.out.println("55555");
    }
}

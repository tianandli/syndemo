package syndemo12;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况下锁会被释放。在并发过程中有异常要特别注意，不然会发生不一致的情况。
 * 运行结果请自行观察
 */
public class T12 {
    int count = 0;

    synchronized void m() {
        System.out.println("线程名：" + Thread.currentThread().getName() + "    start");
        while (true) {
            count++;
            System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 6) {
                /**
                 * 这里抛异常，锁将会被释放，如果进行try……catch……，锁就不会被释放。
                 */
                int i = 1 / 0;
            }
        }
    }

    public static void main(String[] args) {
        T12 t12 = new T12();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                t12.m();
            }
        };

        new Thread(runnable, "t1").start();//创建第一个线程

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(runnable, "t2").start();//创建第二个线程
    }

}

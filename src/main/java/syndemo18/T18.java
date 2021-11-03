package syndemo18;

import java.util.concurrent.TimeUnit;

public class T18 {
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程名：" + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T18 t18 = new T18();
        //创建第一个线程并启动
        new Thread(t18::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(5);//5秒后t2线程启动。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(t18::m, "t2");
        //锁对象发生改变了，所有t2线程得以执行，如果注释下面这行代码，t2线程永远不会被执行。
        t18.o = new Object();
        t2.start();
    }
}

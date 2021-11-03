package syndemo1011;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法里面可以调用另一个同步方法。一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁，
 * 也就是说synchronized获得的锁是可重入的。下面展示的是继承中可能发生的情况，子类的同步方法里面调用父类的同步方法，不会死锁。
 * 运行结果：
 * child m start
 * m start
 * m end
 * child m end
 */
public class T10 {
    public synchronized void m() {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        /**
         * 根据前面的知识我们知道：这里的两个m()方法都是给this加锁，而this就是下面new出来的T11的对象。所以这俩个加锁的是同一个对象
         */
        new T11().m();
    }
}

class T11 extends T10 {
    @Override
    public synchronized void m() {
        System.out.println("child m start");
        super.m();//子类调用父类的同步方法
        System.out.println("child m end");
    }
}


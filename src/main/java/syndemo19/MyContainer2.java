package syndemo19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add和size，写两个线程，线程1添加10个元素到这个容器中，线程2监控元素的个数，当个数为5个时，线程2给出提示并结束。
 */
public class MyContainer2 {
    /**
     * 给容器添加一个volatile关键字即可。让t2线程能够得到通知。这样功能就可以实现了，但是不够完美。
     * 1、t2线程中使用了while(true)，cpu一直在监测mc.size() == 5,它很浪费cpu。
     * 2、t1线程里面一直在往lists容器中加元素，如果这时候有多个线程在往lists容器中加元素，那么可能t2线程打印结束信息的时候，
     * lists中的元素已经有6个甚至7个了。
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer2 mc = new MyContainer2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                mc.add(new Object());
                System.out.println("add" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            System.out.println("t2 开始");
            while (true) {
                if (mc.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }
}

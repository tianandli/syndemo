package syndemo19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add和size，写两个线程，线程1添加10个元素到这个容器中，线程2监控元素的个数，当个数为5个时，
 * 线程2给出提示并结束。下面的代码能实现吗？
 */
public class MyContainer1 {
    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 mc = new MyContainer1();
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

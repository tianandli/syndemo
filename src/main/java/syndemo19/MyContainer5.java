package syndemo19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供两个方法add和size，写两个线程，线程1添加10个元素到这个容器中，线程2监控元素的个数，当个数为5个时，线程2给出提示并结束。
 */
public class MyContainer5 {
    /**
     * 添加volatile使t2线程能够得到通知
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        MyContainer5 mc = new MyContainer5();
        //使用门闩。这里门闩里面的数字为1，当调用一次countDown（）方法，这个数就减1，当这个数为0的时候，门闩就开了。
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 开始");
            if (mc.size() != 5) {
                try {
                    latch.await();
//                    latch.await(5000,TimeUnit.MILLISECONDS);可以指定等待时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            System.out.println("t1启动");
            for (int i = 0; i < 10; i++) {
                mc.add(new Object());
                System.out.println("add" + i);
                if (mc.size() == 5) {
                    //因为前面我们设定的值为1，这里调用了一次countDown（）方法，所以这里门闩就开了。
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1结束");

        }, "t1").start();
    }
}

package qitaxuexi.倒计时器;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：使用场景：等待n个线程执行完毕后，某一线程才开始运行
 *缺点：CountDownLatch是一次性的，计数器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
 * @author: lijie
 * @date: 2021/7/2 10:28
 * @version: V1.0
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);//创建计数器，计数数量为10
    static final CountDownLatchDemo demo = new CountDownLatchDemo();//创建一个线程
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);//模拟检查任务
            System.out.println(Thread.currentThread().getName()+"检查完成");
            end.countDown();//一个线程完成，计数器就减一
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(5);//创建线程池，可同时运行5个线程
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);//将线程放入池中运行
        }
        System.out.println("准备检查");
        end.await();//主线程等待检查。这里要求主线程等待10个检查全部完成，也就是10个任务全部完成，主线程才能继续执行下去。
        System.out.println("火箭发射");
        exec.shutdown();
    }
}

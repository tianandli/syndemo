package qitaxuexi.循环栅栏;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 功能描述：使用场景：某一线程先执行，然后等待其他N个线程都执行完，某一线程再执行
 *
 * @author: lijie
 * @date: 2021/7/2 11:08
 * @version: V1.0
 */
public class CyclicBarrierDemo {
    //士兵静态内部类
    public static class SoliderThread implements Runnable {
        private String solider;
        private final CyclicBarrier cyclic;

        //构造器
        public SoliderThread(String soliderName, CyclicBarrier cyclic) {
            this.solider = soliderName;
            this.cyclic = cyclic;
        }

        private void doWork() {
            System.out.println(solider + "开始工作");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(solider + "工作完成");
        }

        @Override
        public void run() {
            try {
                //第二大步：
                cyclic.await();//等待所有士兵到齐。在这里，每个士兵都会等待，也就是10个线程都会等待，直到10个士兵集合完毕
                doWork();//线程中调用方法
                cyclic.await();//等待所有士兵完成工作，在这里，每个士兵都会等待，也就是10个线程都会等待，直到10个士兵完成工作。这里又会调用LeaderThread里面的run方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    //司令静态内部类
    public static class LeaderThread implements Runnable {
        boolean flag;
        int n;

        public LeaderThread(boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令说：" + n + "个士兵完成任务");
            }else{
                System.out.println("司令说：" + n + "个士兵集合完成");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int m = 10;
        Thread[] allSolider = new Thread[m];//用了数组，只是告诉数组的大小，不能创建对象实例
        boolean flag = false;
        //第一大步：这里创建循环栅栏，将计数器设置为10，并且要求当计数器达到10的时候，执行LeaderThread线程里面的run方法。
        //也就是下面的for走完了10次，会执行LeaderThread线程里面的run方法。这里不能确定10个线程哪个会先进去
        //第一个参数，表示那个一起执行的线程个数。
        //第二个参数，表示线程都处于barrier状态时，需要先执行LeaderThread这个线程
        CyclicBarrier cyclic = new CyclicBarrier(m, new LeaderThread(flag, m));
        System.out.println("队伍集合");
        for (int i = 0; i < m; ++i) {
            System.out.println("士兵" + i + "报道");
            //这里new具体的对象实例，相当于new Thread(new Runnable())，循环创建了10个线程
            allSolider[i] = new Thread(new SoliderThread("士兵" + i, cyclic));
            allSolider[i].start();
        }
    }
}

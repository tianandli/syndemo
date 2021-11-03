package qitaxuexi.原子.boolean的原子;

/**
 * 功能描述：
 * 打印如下：发现两个线程会同时进来，
 * 线程二进来了
 * 线程一进来了
 * 线程一做了点事，离开了
 * 线程二做了点事，离开了
 *
 * @author: lijie
 * @date: 2021/7/6 9:28
 * @version: V1.0
 */
public class WorkerThread1 implements Runnable{
    private static boolean exist =true;

    @Override
    public void run() {
        if(exist){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exist = false;
            System.out.println(Thread.currentThread().getName() + "进来了，将exist设置为了false");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "做了点事，离开了");
        }else{
            System.out.println(Thread.currentThread().getName() + "放弃了");
        }
    }

    public static void main(String[] args) {
        new Thread(new WorkerThread1(),"线程一").start();
        new Thread(new WorkerThread1(),"线程二").start();
    }
}

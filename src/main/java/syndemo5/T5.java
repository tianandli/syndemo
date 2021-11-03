package syndemo5;

public class T5 implements Runnable {
    private int count = 10;

    public /*synchronized*/ void run() {
        count--;
        System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
    }

    public static void main(String[] args) {
        T5 t = new T5();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
    /**
     * 多次运行之后，可能会拿到如下结果：
     * 线程名：THREAD1    count:8
     * 线程名：THREAD0    count:8
     * 线程名：THREAD2    count:7
     * 线程名：THREAD3    count:6
     * 线程名：THREAD4    count:5
     * 分析：1、上面的代码中只new了一个T5的对象t，而不是在每个线程中都new了对象。所以这些线程是共同访问这个对象的。
     * 2、这5个线程访问的是同一个count，count在堆里面，t在栈里面。
     * 3、这里对运行结果做个分析。第一个线程count--之后还没有打印之前，第二个线程进来了，做了个count--操作，这时候
     *第一个线程才开始打印结果，第二个线程也随之打印，所以前两个线程打印的结果都是count--再count--的结果，即8，后边的三个
     * 线程都是count--立马打印，即结果正确。
     * 解决办法。只要将上面的synchronized打开给加上锁，重复多次执行拿到的结果都是正确的。这里不展示测试结果了。
     */
}

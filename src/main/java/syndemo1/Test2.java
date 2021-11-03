package syndemo1;

public class Test2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "，我是run方法");
    }

    public static void main(String[] args) {
        Test2 test1 = new Test2();
        Test2 test2 = new Test2();
        Test2 test3 = new Test2();
        Thread thread1 = new Thread(test1, "线程1");
        Thread thread2 = new Thread(test2, "线程2");
        Thread thread3 = new Thread(test3, "线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

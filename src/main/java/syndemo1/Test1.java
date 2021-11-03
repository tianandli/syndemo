package syndemo1;

public class Test1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + "，我是run方法");
            Thread.yield();//Yield方法可以暂停当前正在执行的线程对象，让其它有相同优先级的线程执行
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Test1 test2 = new Test1();
        Test1 test3 = new Test1();
        test1.setName("线程1");
        test2.setName("线程2");
        test3.setName("线程3");
        test1.start();
        test2.start();
        test3.start();
    }
}

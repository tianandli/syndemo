package syndemo6;

/**
 * 问题：同步和非同步方法是否可以同时调用？
 * 答：可以。理解：m1执行需要锁，m2不管有没有锁都会执行，所以可以同时调用。（注意下面两个方法中的休眠时间）
 * https://blog.csdn.net/zhou_fan_xi/article/details/83752697   lambda表达式引入依赖后报错的解决方法
 * 运行结果：
 * 线程名：t1    m1 start
 * 线程名：t2    m2
 * 线程名：t1    m1 end
 */
public class T6 {
    public synchronized void m1() {
        System.out.println("线程名：" + Thread.currentThread().getName() + "    m1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名：" + Thread.currentThread().getName() + "    m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名：" + Thread.currentThread().getName() + "    m2");
    }

    public static void main(String[] args) {
        T6 t = new T6();
        new Thread(() -> t.m1(), "t1").start();//在一个线程中调用m1()方法
        new Thread(() -> t.m2(), "t2").start();//在另一个线程中调用m2()方法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m1();
//            }
//        });
    }
}

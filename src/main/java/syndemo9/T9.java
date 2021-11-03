package syndemo9;

/**
 * 这个类里面模拟了死锁
 */
public class T9 {
    private final Object o1 = new Object();
    private final Object o2 = new Object();

    private void m1() {
        synchronized (o1) {
            System.out.println(Thread.currentThread().getName() + "获取对象o1锁");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m2();
        }
        System.out.println("m1结束");
    }

    private void m2() {
        synchronized (o2) {
            System.out.println(Thread.currentThread().getName() + "获取对象o2锁");
            m1();
        }
        System.out.println("m2结束");
    }

    public static void main(String[] args) {
        T9 t = new T9();
        new Thread(t::m1, "线程1").start();
        new Thread(t::m2, "线程2").start();
    }
}

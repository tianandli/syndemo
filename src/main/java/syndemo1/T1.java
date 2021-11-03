package syndemo1;

/**
 * synchronized：加锁的意思，他是对某个对象加锁，而不是对某段代码。
 * 下面例子中创建了一个对象o专门用于加锁。
 */
public class T1 {
    private int count = 4;
    private Object o = new Object();//o的作用就是充当锁

    public static void main(String[] args) {
        T1 t = new T1();
        t.m();
    }

    public void m() {
        /**
         * 1、要执行下面的代码，必须先拿到o的锁。new Object()在堆里创建了一个对象，o是在栈里指向堆里面的对象。这里实际是
         * 要找堆里面的对象申请锁的。下面统一说是找o申请锁。
         * 2、这里可以理解为向o申请锁，也可以理解为给o加锁，怎么好理解怎么整，但是锁只有一把。
         * 3、第一个线程把这把锁释放了，第二个线程才能申请到这把锁，才能调用下面的方法。一个线程拿到了这把锁别的线程就拿不到了，
         * 这就叫互斥锁。
         */
        synchronized (o) {
            count--;
            System.out.println("线程名：" + Test1.currentThread().getName() + "    count:" + count);
        }
    }
}

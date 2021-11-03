package syndemo13141516;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 当只做++，--操作时，可以使用下面的方法，可以保证原子性。不用使用synchronized。
 * Atomicxxx类有很多，这些类本身方法都是原子性的，性能比synchronized好很多。但不能保证多个方法连续调用是原子性的。
 */
public class T16 {

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized */void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//等同于count++
        }
    }

    public static void main(String[] args) {
        T16 t16 = new T16();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            threads.add(new Thread(t16::m, "thread-" + j));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t16.count);
    }
}

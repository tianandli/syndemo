package syndemo13141516;

import java.util.ArrayList;

public class T14 {
    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T14 t14 = new T14();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            threads.add(new Thread(t14::m, "thread-" + j));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t14.count);
    }
}

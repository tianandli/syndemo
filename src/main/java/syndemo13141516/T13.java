package syndemo13141516;

import java.util.concurrent.TimeUnit;

/**
 * volatile:临时的，透明的。这个关键字使得一个变量在多个线程间可见
 * A、B两个线程都用到一个变量，Java默认是A线程将主存中的值copy一份保存在cpu的缓存中，如果B线程修改了该变量，A线程
 * 未必知道主存中的值被修改了，使用volatile关键字会让所有线程都知道主存中的值被修改了，缓存中的值过期了，需要去主存中重新读取值。
 * <p>
 * 在下面代码中，t13在栈中，T13的对象在堆中，t13指向T13，running存在于T13对象中。在t1线程执行的时候，会把running的值从内存中
 * copy一份放到t1线程的工作区（缓存），在运行过程中直接使用这个copy的值，并不会每次都去读取主存，这样主线程修改running的值后，
 * t1线程并不会感知到，所以不会停止。
 * <p>
 * 使用volatile关键字，将会强制所有线程都去主存中读取running的值。
 * <p>
 * volatile并不能保证多个线程共同修改running变量所带来的不一致问题，也就是说volatile不能代替synchronized。
 * 区别：
 * 1、volatile本质是在告诉jvm当前变量在寄存器（工作内存或缓存）中的值是不确定的，需要从主存中读取；
 * synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。
 * 2、volatile仅能使用在变量级别；synchronized则可以使用在变量、方法、和类级别的
 * 3、volatile只保证可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性(重要)
 * 4、volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞。synchronized效率比volatile低很多
 * 5、volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化
 * Java内存模型的资料：https://blog.csdn.net/suifeng3051/article/details/52611310
 */
public class T13 {
    volatile boolean running = true;//要想让这个变量在多个线程间共享，就要加volatile这个关键字。

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T13 t13 = new T13();
        new Thread(t13::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t13.running = false;
    }
}

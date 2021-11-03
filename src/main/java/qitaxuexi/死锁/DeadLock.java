package qitaxuexi.死锁;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/7/7 9:41
 * @version: V1.0
 */
public class DeadLock{
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
            DeadLock t = new DeadLock();
            new Thread(t::m1, "线程1").start();
            new Thread(t::m2, "线程2").start();
//            上面的代码等价于下面的
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    t.m1();
//                }
//            }).start();
        }
}

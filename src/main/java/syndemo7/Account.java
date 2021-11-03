package syndemo7;

import java.util.concurrent.TimeUnit;

/**
 * 下面这个类模拟了一个充钱和查询钱的业务。对充钱的过程加了锁，对查询钱的过程没加锁，在执行过程中可能产生脏读。
 */
public class Account {
    String name;
    double money;

    //充钱
    public synchronized void setMoney(String name, double money) {
        this.name = name;

        /**
         *  这里线程休息了2秒后才把钱设置进去。这里是为了模拟真实义务逻辑复杂的情况，将代码执行时间放大
         *  尽管对写的过程加了锁，但是在写的过程还没有完成的时候进行了读，也就是在休息的这2秒内进行了读，
         *  读是没加锁的，就可能发生脏读。
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.money = money;
    }
    //查询钱

    /**
     * 如果业务里面允许暂时性的脏读，那么读是不用加锁的，这样可以提高性能。如果不允许，就读写都加锁，把下面的加锁的代码打开即可。
     */
    public /*synchronized*/ double getMoney(String name) {
        return this.money;
    }

    public static void main(String[] args) {
        Account account = new Account();

//        new Thread(new Runnable() {//开启一个线程，调用setMoney()方法
//            @Override
//            public void run() {
//                account.setMoney("张三",101.0);
//            }
//        }).start();

        //开启一个线程，调用setMoney()方法。下面这行代码等同于上面注释的六行代码
        new Thread(() -> account.setMoney("张三", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);//线程休息1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getMoney("张三"));//查询张三的钱

        try {
            TimeUnit.SECONDS.sleep(2);//线程休息2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getMoney("张三"));//查询张三的钱
    }
}

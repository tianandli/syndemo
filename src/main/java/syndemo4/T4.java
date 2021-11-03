package syndemo4;

public class T4 {
    private static int count = 4;

    public static void main(String[] args) {
        m();//这里也可以写为T4.m()
        mm();//这里也可以写为T4.mm()
    }

    public static void mm() {
        /**
         * 静态属性和静态方法的调用是类名.类方法或类名.类属性，这个时候调用方法的时候是没有new一个对象的，所以下面这行代码里
         * 就不能写synchronized(this)。即这里不能给this加锁！这个时候锁定的是当前类的class对象。
         */
        synchronized (T4.class) {
            count--;
            System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
        }
    }

    /**
     * 这里m()方法和mm（）方法中的两个加锁的效果是一样的。
     * 我们知道万物皆对象，T4.class是将类抽象成了一个对象。
     * 静态方法里面synchronized加锁的对象是当前类的class对象。
     */
    public synchronized static void m() {
        count--;
        System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
    }
}

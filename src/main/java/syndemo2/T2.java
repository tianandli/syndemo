package syndemo2;

public class T2 {
    private int count = 2;

    public static void main(String[] args) {
        T2 t = new T2();
        t.m();
    }

    /**
     * 任何线程要执行下面的代码，先要给this加锁，这里是给自身加锁。或者说要用这个方法，要先new一个T的对象指向自身。
     * 也就是说this就是T2的对象。
     * <p>
     * m()方法里面“一开始就对this加锁了”，对这种有个简单的写法，见T3。
     */
    public void m() {
        synchronized (this) {
            count--;
            System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
        }
    }
}

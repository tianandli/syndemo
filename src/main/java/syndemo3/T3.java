package syndemo3;


public class T3 {
    private int count = 3;

    public static void main(String[] args) {
        T3 t = new T3();
        t.m();
    }

    /**
     * 对T2里面的简单写法如下。这个例子里面锁定的是当前对象this。
     */
    public synchronized void m() {
        count--;
        System.out.println("线程名：" + Thread.currentThread().getName() + "    count:" + count);
    }
}

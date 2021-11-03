package syndemo1;

public class Test4 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "，我是run方法");
            }
        }).start();
    }
}

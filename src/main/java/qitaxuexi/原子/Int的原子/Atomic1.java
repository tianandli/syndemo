package qitaxuexi.原子.Int的原子;

/**
 * 功能描述：
 * 打印结果的如下：
 * 0
 * 3
 * 2
 * 1
 * 4
 * 5
 * 5
 * 7
 * 6
 * 5
 * 8
 * 10
 * 9
 * 9
 * 10
 * 出现上面问题的原因是：a++的操作不是线程安全的，对于a++的操作，其实可以分解为3个步骤。1从主存中读取a的值；2对a进行加1操作；3把a重新刷新到主存
 * 这三个步骤在单线程中一点问题都没有，但是到了多线程就出现了问题了。比如说有的线程已经把a进行了加1操作，
 * 但是还没来得及重新刷入到主存，其他的线程就重新读取了旧值。因为才造成了错误。
 *
 * @author: lijie
 * @date: 2021/7/6 8:52
 * @version: V1.0
 */
public class Atomic1 {
    private static volatile  int a = 0;

    public static void main(String[] args) {
        //这里创建5个线程
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程内部对a循环3次进行++
                    try {
                        for (int j = 0; j < 3; j++) {
                            //问题的原因就是这里的a++不是线程安全的
                            System.out.println(a++);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

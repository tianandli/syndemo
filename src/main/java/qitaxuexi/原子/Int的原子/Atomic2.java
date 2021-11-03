package qitaxuexi.原子.Int的原子;

/**
 * 功能描述：
 * 使用synchronized解决问题，打印结果的如下：
 * 0
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 8
 * 9
 * 10
 * 11
 * 12
 * 13
 * 14
 *
 * @author: lijie
 * @date: 2021/7/6 8:52
 * @version: V1.0
 */
public class Atomic2 {
    private static volatile  int a = 0;
    private static Object obj = new Object();

    public static void main(String[] args) {
        //这里创建5个线程
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (obj){
                        //每个线程内部对a循环3次进行++
                        try {
                            for (int j = 0; j < 3; j++) {
                                System.out.println(a++);
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}

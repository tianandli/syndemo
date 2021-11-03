package qitaxuexi.原子.Int的原子;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：
 * 其他类似的还有AtomicLong,AtomicBoolean,AtomicReference
 * 使用AtomicInteger解决问题，打印结果的如下：
 * 0
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 10
 * 8
 * 9
 * 7
 * 11
 * 12
 * 15
 * 13
 * 14
 *
 * @author: lijie
 * @date: 2021/7/6 8:52
 * @version: V1.0
 */
public class Atomic3 {
    private static AtomicInteger a = new AtomicInteger();//创建一个，注意这里用static修饰了

    public static void main(String[] args) {
        System.out.println(a);
        //这里创建5个线程
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        //每个线程内部对a循环3次进行++
                        try {
                            for (int j = 0; j < 3; j++) {
                                //对a进行加一（先加一再获取值。相关的方法见资料https://baijiahao.baidu.com/s?id=1647621616629561468&wfr=spider&for=pc）
                                System.out.println(a.incrementAndGet());
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

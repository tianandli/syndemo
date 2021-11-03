package qitaxuexi.重点;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：不使用ThreadLocal
 *
 *
 * @author: lijie
 * @date: 2021/7/5 15:27
 * @version: V1.0
 */
public class ThreadLocal1 {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static class ParseDateThread1 implements Runnable{
        int i = 0;
        //构造器
        public ParseDateThread1(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //第二步：sdf.parse()这个parse方法不是线程安全的，在线程池中共享sdf这个对象，必然导致异常，见打印结果
                Date parse = sdf.parse("2021-07-05 10:00:" + i % 60);
                System.out.println(i + ":" + parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            //第一步：这里通过线程池来执行1000个线程，并把i赋值给每个线程，然后启动线程，即启动每个线程的run方法
            es.execute(new ParseDateThread1(i));
        }
    }
}

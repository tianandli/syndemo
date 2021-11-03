package qitaxuexi.重点;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：使用ThreadLocal解决
 *
 * @author: lijie
 * @date: 2021/7/5 15:27
 * @version: V1.0
 */
public class ThreadLocal2 {
    private static ThreadLocal tl = new ThreadLocal<SimpleDateFormat>();
    public static class ParseDate implements Runnable{
        int i = 0;
        //构造器
        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                //如果当前线程不持有SimpleDateFormat的对象，就创建一个新的SimpleDateFormat的对象并设置给当前线程
                if(tl.get() == null){
                    //注意！！！这里设置的都是新创建的SimpleDateFormat的对象，如果这里设置的都是同一个对象，依旧不能保证线程安全。
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                //如果持有或者已经设置过了，直接获取
                SimpleDateFormat sl = (SimpleDateFormat)tl.get();
                //这样就可以保证每个线程使用的SimpleDateFormat的对象，即sl是不一样的。
                Date parse = sl.parse("2021-07-05 10:00:" + i % 60);
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
            es.execute(new ParseDate(i));
        }
    }
}

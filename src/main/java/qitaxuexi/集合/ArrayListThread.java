package qitaxuexi.集合;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 功能描述：在线程外面定义的ArrayList,HashMap,Set，在线程的方法体run方法里面就不能使用了，而应该使用线程安全的
 *
 * @author: lijie
 * @date: 2021/7/2 15:41
 * @version: V1.0
 */
public class ArrayListThread {

    public static void main(String[] args) {
        //ArrayList是线程不安全的，所以在多线程的是，有的线程给list里面在add，有的在打印list，就会报并发修改异常ConcurrentModificationException
//        List<String> list = new ArrayList<>();

        //3种解决方案
//        List<String> list = new Vector<>();
//        List list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //上面代码与下面的一个作用
//        for (int i = 0; i < 30; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    list.add(String.valueOf(new Random().nextInt(100)));
//                    System.out.println(list);
//                }
//            }, String.valueOf(i)).start();
//        }
    }
}

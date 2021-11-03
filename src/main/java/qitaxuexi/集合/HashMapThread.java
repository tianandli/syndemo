package qitaxuexi.集合;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述：线程的方法体，run方法里面就不能使用ArrayList,HashMap,Set了，而应该使用线程安全的
 *
 * @author: lijie
 * @date: 2021/7/2 15:41
 * @version: V1.0
 */
public class HashMapThread {
    public static void main(String[] args) {

//        Map<String,String> map = new HashMap<>();//导致线程不安全

        //2种解决方案
//         Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,2));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}

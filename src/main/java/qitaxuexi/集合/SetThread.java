package qitaxuexi.集合;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 功能描述：线程的方法体，run方法里面就不能使用ArrayList,HashMap,Set了，而应该使用线程安全的
 *
 * @author: lijie
 * @date: 2021/7/2 15:41
 * @version: V1.0
 */
public class SetThread {
    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();//导致线程不安全

        //2种解决方案
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 2));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

}

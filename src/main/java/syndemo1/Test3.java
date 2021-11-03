package syndemo1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test3 implements Callable {//实现Callable，重写call方法

    @Override
    public Object call() throws Exception {
        return "hello";
    }

    public static void main(String[] args) {
        //call方法里面返回的是String,所以泛型是String
        FutureTask<String> task = new FutureTask<String>(new Test3());
        new Thread(task).start();//启动线程
        try {
            String result = task.get();//相当于是回调函数，拿到call方法的返回值
            System.out.println(result);//控制台打印出"hello"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

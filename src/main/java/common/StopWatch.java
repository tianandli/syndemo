package common;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/10/14 15:01
 * @version: V1.0
 */
public class StopWatch {
    private long startTime;
    private long endTime;

    public StopWatch() {
        this(true);
    }
    public StopWatch(boolean isTrue){
        if(isTrue){
            start();
        }
    }
    public void start(){
        startTime = System.nanoTime();
        System.out.println(startTime);
    }
    public void end(){
        endTime = System.nanoTime();
        System.out.println(endTime);
    }
    public String printCost(){
        String useTime = "执行总耗时[" + (endTime - startTime) /1000/1000+"]毫秒";
        System.out.println(useTime);
        return useTime;
    }
}

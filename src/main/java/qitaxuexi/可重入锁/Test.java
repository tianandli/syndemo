package qitaxuexi.可重入锁;

/**0
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/6/29 13:56
 * @version: V1.0
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {

            @Override
            public void run(){
                while (true){
                    /**
                     * 第一遍循环的时候，不会进来，第二遍会进来
                     */
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("====");
                        break;
                    }
                    try {
                        System.out.println("[[[[[[");
                        /**
                         * 这里的休眠时间要大于下面的2秒。线程在休眠，这里会报错进入catch。
                         */
                        Thread.sleep(3000);
                        System.out.println("】】】】】");
                    } catch (InterruptedException e) {
                        System.out.println("----");
                        Thread.currentThread().interrupt();//抛异常会取消终中断状态，所以这里重新设置为中断状态。否在程序不会进入break
                    }
                    Thread.yield();//Thread.yield()方法的作用：暂停当前正在执行的线程，并执行其他线程。
                }
            }
        };
        thread.start();
        /**
         * 线程在休眠的时候（上面休眠了3秒），下面那行代码将线程中断了，这时候，上面那行休眠的代码（休眠3秒）会抛异常进入catch里面
         */
        Thread.sleep(2000);
        thread.interrupt();
    }
}

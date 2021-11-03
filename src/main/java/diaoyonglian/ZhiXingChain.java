package diaoyonglian;

import common.StopWatch;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/10/14 11:17
 * @version: V1.0
 */
public class ZhiXingChain {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        System.out.println("开始创建链式结构");
        Chain chain = ChainFactory.createChain();
        System.out.println("创建链式结构完成");
        Person person1 = new Person("1","20","张三","男");
        System.out.println("链式结构开始执行");
        chain.execute(person1);

        stopWatch.printCost();
    }
}

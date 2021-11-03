package diaoyonglian;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/10/14 10:29
 * @version: V1.0
 */
public class FourChain extends AbstractChain {
    @Override
    public void execute(Person person) {
        System.out.println("第四个链执行，获取到person对象是:"+person);
        System.out.println("整个链式结构执行完成");
    }
}

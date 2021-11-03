package diaoyonglian;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/10/14 10:26
 * @version: V1.0
 */
public class TwoChain extends AbstractChain {
    @Override
    public void execute(Person person) {
        System.out.println("第二个链执行，获取到person对象是:"+person);
        Person person3 = new Person("3","20","王五","男");
        zhiXingNextChain(person3);
    }
}

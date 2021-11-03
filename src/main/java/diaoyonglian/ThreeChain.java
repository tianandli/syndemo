package diaoyonglian;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/10/14 10:28
 * @version: V1.0
 */
public class ThreeChain extends AbstractChain {
    @Override
    public void execute(Person person) {
        System.out.println("第三个链执行，获取到person对象是:"+person);
        Person person4 = new Person("4","20","赵六","男");
        zhiXingNextChain(person4);
    }
}

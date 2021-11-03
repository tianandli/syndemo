package diaoyonglian;

/**
 * 功能描述：具体的子类就不定义为抽象类了，因为这里要实现具体的execute()方法
 *
 * @author: lijie
 * @date: 2021/10/14 10:16
 * @version: V1.0
 */
public class OneChain extends AbstractChain {
    @Override
    public void execute(Person person) {
        System.out.println("第一个链执行，获取到person对象是:"+person);
        Person person2 = new Person("2","20","李四","男");
        zhiXingNextChain(person2);
    }
}

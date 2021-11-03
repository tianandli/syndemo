package diaoyonglian;

/**
 * 功能描述：这个类定义为抽象类，否则就要实现Chain里面的所有方法。
 * AbstractChain这个父类里面只写公用的方法，不共用的方法在子类中去写
 *
 * @author: lijie
 * @date: 2021/10/14 10:13
 * @version: V1.0
 */
public abstract class AbstractChain implements Chain {
    private Chain next;

    @Override
    public Chain getNext() {
        return next;
    }

    @Override
    public void setNext(Chain chain) {
        this.next = chain;
    }

    //protected对于子类是public的，对于其他类是private的
    protected void zhiXingNextChain(Person person){
        Chain next = getNext();
        if(next != null){
            next.execute(person);
        }
    }
}

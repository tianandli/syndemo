package diaoyonglian;

/**
 * 功能描述：链的接口。接口里默认是publisc的
 *
 * @author: lijie
 * @date: 2021/10/14 9:53
 * @version: V1.0
 */
public interface Chain {
    public void execute(Person person);
    public Chain getNext();
    public void setNext(Chain chain);
}

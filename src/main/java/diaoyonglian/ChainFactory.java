package diaoyonglian;

/**
 * 功能描述：链式的工厂
 *
 * @author: lijie
 * @date: 2021/10/14 9:52
 * @version: V1.0
 */
public class ChainFactory {
    /**
     * 描述：抽象类（AbstractChain）是不能实例化的，所以用子类来实例化
     */
    private static final Chain oneChain = new OneChain();
    private static final Chain twoChain = new TwoChain();
    private static final Chain threeChain = new ThreeChain();
    private static final Chain fourChain = new FourChain();

    //构造链式结构
    static{
        oneChain.setNext(twoChain);
        twoChain.setNext(threeChain);
        threeChain.setNext(fourChain);
    }

    //返回链式结构
    public static Chain createChain(){
        return oneChain;
    }
}

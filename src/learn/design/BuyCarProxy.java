package learn.design;

/**
 * Created by liubin on 2020/4/4.
 */
public class BuyCarProxy implements BuyCar {
    private BuyCar mBc;
    public BuyCarProxy(BuyCar buyCar){
        mBc = buyCar;
    }
    @Override
    public void buyCar() {
        System.out.println("你先攒钱");
        mBc.buyCar();
        System.out.println("买完车后走上人生巅峰");
    }
}

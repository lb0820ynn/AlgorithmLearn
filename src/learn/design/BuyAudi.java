package learn.design;

/**
 * Created by liubin on 2020/4/4.
 */
public class BuyAudi implements BuyCar {
    final String baseUrl;

    public BuyAudi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void buyCar() {
        System.out.println("我买了一辆奥迪");
    }
}

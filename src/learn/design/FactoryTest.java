package learn.design;

/**
 * Created by liubin on 2020/4/5.
 */
public class FactoryTest {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        Shape circle = factory.getShape(SimpleFactory.SHAPE_CIRCLE);
        circle.draw();
        Shape s = factory.getShape(SimpleFactory.SHAPE_SQUARE);
        s.draw();


        AbsFactory sFactory = AbsFactoruProducer.getFactory("SHAPE");
        Shape square = sFactory.getShape(AbsFactory.SHAPE_SQUARE);
        square.draw();
        AbsFactory c = AbsFactoruProducer.getFactory("COLOR");
        Color red = c.getColor(AbsFactory.COLOR_RED);
        red.fill();
    }
}

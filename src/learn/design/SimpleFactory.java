package learn.design;


/**
 * Created by liubin on 2020/4/5.
 */
public class SimpleFactory {
    public static final String SHAPE_SQUARE = "square";
    public static final String SHAPE_CIRCLE = "circle";

    public Shape getShape(String shape){
        if(SHAPE_SQUARE.equals(shape)){
            return new Square();
        }else if(SHAPE_CIRCLE.equals(shape)){
            return new Circle();
        }else {
            throw new RuntimeException("no this shape");
        }
    }
}

package learn.design;

/**
 * Created by liubin on 2020/4/5.
 */
public class AbsShapeFactory implements AbsFactory {
    public Shape getShape(String shape){
        if(SHAPE_SQUARE.equals(shape)){
            return new Square();
        }else if(SHAPE_CIRCLE.equals(shape)){
            return new Circle();
        }else {
            throw new RuntimeException("no this shape");
        }
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}

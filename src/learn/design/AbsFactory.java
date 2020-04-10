package learn.design;

/**
 * Created by liubin on 2020/4/5.
 */
public interface AbsFactory {

    String SHAPE_SQUARE = "square";
    String SHAPE_CIRCLE = "circle";

    String COLOR_RED = "red";
    String COLOR_BLUE = "blue";

    Shape getShape(String shape);

    Color getColor(String color);
}

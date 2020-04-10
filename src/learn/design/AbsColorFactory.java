package learn.design;

/**
 * Created by liubin on 2020/4/5.
 */
public class AbsColorFactory implements AbsFactory {
    public Shape getShape(String shape){
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(COLOR_RED.equals(color)){
            return new Red();
        }else if(COLOR_BLUE.equals(color)){
            return new Blue();
        }else {
            throw new RuntimeException("no this color");
        }
    }
}

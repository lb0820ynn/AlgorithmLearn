package learn.design;

/**
 * Created by liubin on 2020/4/5.
 */
public class AbsFactoruProducer {

    public static AbsFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new AbsShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new AbsColorFactory();
        }
        return null;
    }
}

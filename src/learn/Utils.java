package learn;

import java.util.List;

/**
 * Created by liubin on 2018/12/6.
 */
public class Utils {

    public static void printList(List list) {
        if (list == null) {
            return;
        }
        for (Object item : list) {
            System.out.print(item.toString() + " ");
        }
    }
}

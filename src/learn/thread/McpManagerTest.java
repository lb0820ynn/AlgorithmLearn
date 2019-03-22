package learn.thread;

/**
 * Created by liubin on 2018/8/27.
 */
public class McpManagerTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    McpManager.getInstance().readDoorStatus(3);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    McpManager.getInstance().readVol(3);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    McpManager.getInstance().readVol(5);
                }
            }
        }).start();
    }
}

package learn.thread;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by liubin on 2018/8/27.
 */
public class McpManager {
    private McpManager() {
        list = new LinkedList<>();
    }

    static McpManager mcpManager;
    LinkedList<Integer> list;

    public static McpManager getInstance() {
        if (mcpManager == null) {
            synchronized (McpManager.class) {
                if (mcpManager == null) {
                    mcpManager = new McpManager();
                }
            }
        }
        return mcpManager;
    }

    public void readDoorStatus(int byteRequired) {
        writeCan4Response(byteRequired);
    }

    private synchronized void writeCan4Response(int byteRequired) {
        writeCan(byteRequired);
        readResponse(byteRequired, 0, new int[9]);
    }

    public void readVol(int byteRequired) {
        writeCan4Response(byteRequired);
    }


    int readResponse(int byteRequired, int fromTarget, int[] buf) {
        int N = read_mcp(buf, byteRequired, 50);
        if (N != byteRequired) {
            System.out.println(("ERROR --- response:byte recved != required:" + N + "," + byteRequired));
            return N;
        }
        int cmd_type = buf[0] & 0xF0;
        int cmd_from_target = buf[0] & 0x0F;
        int target_id = buf[1];
        int cmd_content = buf[2];
        System.out.println("pican-------response,byteRequired:" + byteRequired + "  Thread=== " + Thread.currentThread().toString());


        delayMs(50);  //每次读取响应增加50ms delay
        return N;
    }

    private void writeCan(int byteRequired) {
        list.add(byteRequired);
        delayMs(20);
    }

    int read_mcp(int[] buf, int byteRequired, int timeOutMs) {
        int N = read_mcp_without_check_broadcast(buf, byteRequired, timeOutMs);
        return N;
    }


    int read_mcp_without_check_broadcast(int[] buf, int byteRequired, int timeOutMs) {
        int[] buf1 = new int[]{223, 2, 103, 2, 105, 0, 0, 0};
        int[] buf2 = new int[]{208, 0, 129, 0, 0, 0, 0, 0};

        int byteCount = list.pollFirst();

        buf = byteCount == 5 ? buf1 : buf2;
        System.out.println("read_mcp_without_check_broadcast   byteRequired" + byteRequired + "  byteCount == " + byteCount);
        return byteCount;
    }

    public void delayMs(int ms) {
        try {
            Thread.currentThread().sleep(ms);//阻断1ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

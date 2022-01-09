package zkLock;

import org.apache.zookeeper.ZooKeeper;
import zkConfigurationCenter.DefaultWatch;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private static ZooKeeper zk;

    private static final String address = "192.168.1.16:2181,192.168.1.16:2182,192.168.1.16:2183/testLock";

    private static DefaultWatch defaultWatch = new DefaultWatch();

    private static CountDownLatch downLatch = new CountDownLatch(1);

    public static ZooKeeper getZk(){
        try {
            zk = new ZooKeeper(address,1000,defaultWatch);
            // 连接是异步的，为了防止发送完连接请求完之后还未获取真正连接时，主线程就执行完了
            // 使用 CDL
            defaultWatch.setDownLatch(downLatch);
            downLatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return zk;
    }
}

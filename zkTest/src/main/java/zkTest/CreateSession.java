package zkTest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 先看test包下的{@link zkTest }
 */
public class CreateSession implements Watcher {

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    /**客户端可以通过创建一个zk实例来连接zk服务器
        new Zookeeper(connectString,sessionTimeOut,watcher)
        connectString: 链接地址:ip:port
        sessionTimeOut: 会话断开后停留时间，ms
        watcher: 监听器
        */
    public static void main(String[] args) throws InterruptedException,
            IOException {

        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.16:2181", 5000, new CreateSession());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        //表示会话真正建立
        System.out.println("=========Client Connected to zookeeper==========");
    }

    // watch的回调方法
    // 在这里解除CountDownLatch，使得main函数继续运行
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
}


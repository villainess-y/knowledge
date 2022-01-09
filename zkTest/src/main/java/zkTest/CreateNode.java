package zkTest;

import org.apache.zookeeper.*;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class CreateNode implements Watcher {

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.1.16:2181", 5000, new CreateNode());
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
        // 调用创建节点方法
        try {
            createNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * path 节点创建路径
     * data[] 节点保存的数据，是byte类型
     * acl 权限信息
     * createMode 创建节点的类型
     * String node = zookeeper.create(path,data,acl,createMode);
     */
    private void createNodeSync() throws Exception {
        String node_PERSISTENT = zooKeeper.create("/lg_persistent", "持久节点内容".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String node_PERSISTENT_SEQUENTIAL = zooKeeper.create("/lg_persistent_sequential", "持久顺序节点内容".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        String node_EPERSISTENT = zooKeeper.create("/lg_ephemeral", "临时节点内容".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        System.out.println("创建的持久节点内容是:" + node_PERSISTENT);
        System.out.println("创建的持久顺序节点内容是:" + node_PERSISTENT_SEQUENTIAL);
        System.out.println("创建的临时节点内容是:" + node_EPERSISTENT);
    }
}

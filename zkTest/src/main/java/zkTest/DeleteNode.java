package zkTest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DeleteNode implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.1.16:2181", 5000, new DeleteNode());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            deleteNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteNodeSync() throws InterruptedException, KeeperException {
        /*
         * zooKeeper.exists(path,watch) :判断节点是否存在
         * zookeeper.delete(path,version) : 删除节点
        */
        Stat exists = zooKeeper.exists("/lg_persistent/lg-children", false);
        System.out.println(exists == null ? "该节点不存在" : "该节点存在");
        zooKeeper.delete("/lg_persistent/lg-children", -1);
        Stat exists2 = zooKeeper.exists("/lg_persistent/lg-children", false);
        System.out.println(exists2 == null ? "该节点不存在" : "该节点存在");
    }
}

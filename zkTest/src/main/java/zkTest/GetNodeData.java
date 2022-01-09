package zkTest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetNodeData implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.1.16:2181", 10000, new
                GetNodeData());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void getNoteData() throws Exception {
        byte[] data = zooKeeper.getData("/lg_persistent/lg-children", true,
                null);
        System.out.println(new String(data, StandardCharsets.UTF_8));
    }

    private static void getChildrens() throws KeeperException,
            InterruptedException {
        List<String> children = zooKeeper.getChildren("/lg_persistent", true);
        System.out.println(children);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        //子节点列表发生变化时，服务器会发出NodeChildrenChanged通知，但是不会主动告知客户端
        // 需要客户端自行获取，且通知是一次性的，需反复注册
        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            // 再次获取节点数据
            try {
                List<String> children =
                        zooKeeper.getChildren(watchedEvent.getPath(), true);
                System.out.println(children);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            //修改节点数据
            try {
                //调取获得单个节点数据的方法
                getNoteData();
                getChildrens();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

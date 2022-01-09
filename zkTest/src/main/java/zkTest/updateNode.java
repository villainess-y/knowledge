package zkTest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class updateNode implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.1.16:2181", 5000, new updateNode());
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            updateNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    path:
    data:byte[]
    version:为-1时表示对最新版本修改
    zooKeeper.setData(path, data,version);
    */
    private void updateNodeSync() throws Exception {

        byte[] data = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改前内容:" + new String(data));
        Stat stat = zooKeeper.setData("/lg_persistent", "客户端修改内容:".getBytes(), -1);
        byte[] data2 = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改后的值:" + new String(data2));
    }
}

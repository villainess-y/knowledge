package zkTest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class zkTest {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        System.out.println("hello world");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 这里还可以跟上path ...2183/ooxx，那么此次连接访问的所有节点就默认是在/ooxx下
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.16:2181,192.168.1.16:2182,192.168.1.16:2183",
                3000, new Watcher() {
            // 新建时的watch是会话级的
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.KeeperState state = watchedEvent.getState();
                String path = watchedEvent.getPath();
                Event.EventType type = watchedEvent.getType();
                System.out.println(watchedEvent);
                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("connected");
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                }

                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                }

            }
        });

        countDownLatch.await();
        ZooKeeper.States state = zooKeeper.getState();

        switch (state) {
            case CONNECTING:
                System.out.println(".......ing");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed.......");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }

        String pathName = zooKeeper.create("/abcd", "i am abcd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(pathName);
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/abcd", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("callBack function :" + watchedEvent.toString());
                // 因为这个watch是一次性的，所以如果还想检测打印，则需要循环注册
                try {
                    // 这里 watch = true 表示使用zk创建时的watcher
                    // 这里 如果还想继续使用打印callBack function的watcher ,直接使用this即可
                    zooKeeper.getData("/abcd", this, stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println(new String(data));

        // 触发回调,持续触发需要重新注册watch
        Stat stat1 = zooKeeper.setData("/abcd", "new Data".getBytes(), 0);
        Stat stat2 = zooKeeper.setData("/abcd", "new Data12".getBytes(), stat1.getVersion());
        Stat stat3 = zooKeeper.setData("/abcd", "new Data23".getBytes(), stat2.getVersion());

        // 使用异步回调
        System.out.println("-----------async start -------------");
        zooKeeper.getData("/abcd", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("-----------async call back -------------");
                System.out.println(o.toString());
                System.out.println(s);
            }
        }, "applicationContext");
        System.out.println("-----------async over -------------");

        Thread.sleep(2222222);
    }
}

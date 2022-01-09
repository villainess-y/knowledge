package zkConfigurationCenter;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * session 级 watch,用于ZK创建时
 */
public class DefaultWatch implements Watcher {

    CountDownLatch downLatch;


    public void setDownLatch(CountDownLatch cc) {
        this.downLatch = cc;
    }



    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("session watch :" + watchedEvent.toString());

        switch (watchedEvent.getState()) {
            case Disconnected:
                break;
            case SyncConnected:
                downLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            case Closed:
                break;
        }
    }
}

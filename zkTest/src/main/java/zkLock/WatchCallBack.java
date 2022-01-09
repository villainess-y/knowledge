package zkLock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.KeeperState.Disconnected;

public class WatchCallBack implements Watcher, AsyncCallback.StringCallback, AsyncCallback.StatCallback, AsyncCallback.ChildrenCallback {

    ZooKeeper zk;

    String threadName;

    CountDownLatch cc = new CountDownLatch(1);

    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }


    public void tryLock() {
        try {
            zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL, this, "applicationContext");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unLock() {
        try {
            zk.delete(pathName,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/",false,this,"applicationContext");
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }

    /**
     * create的callBack
     * @param i
     * @param s
     * @param o
     * @param s1
     */
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        if (s != null) {
            // 这里的path前面有testLock，就是获取/testLock 的children
            // 这里的watch不需要，因为这里的watch是监控/testLock，消耗较大
            // 只需要watch自己前面那个节点即可
            System.out.println(threadName +" create node :" + s1);
            pathName = s1;
            zk.getChildren("/",false,this,"applicationContext");
        }
    }

    /**
     * getChildren的callBack
     * 判断自己是否是第一个节点
     * 一定能看到自己之前的节点,并且list并不是顺序的
     * @param rc
     * @param pathName
     * @param ctx
     * @param list
     */
    @Override
    public void processResult(int rc, String pathName, Object ctx, List<String> list) {

        System.out.println(threadName + "look.....");
        Collections.sort(list);
        // pathName带了‘/’，而list没有
        int i = list.indexOf(pathName.substring(1));
        // 如果是第一个，则countDown,执行业务逻辑
        if(i == 0){
            System.out.println(threadName + ": i am first。。。。。");
            cc.countDown();
        }else{
            // 等待，判断前一个节点是否存在
            // 因为前面一个节点可能会down掉，所以需要callBack
            zk.exists("/" + list.get(i-1),this,this,"applicationContext");
        }
    }

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        // 判断前一个节点是否down掉,
        /**
         * if (stat.compareTo(Disconnected))
         */
    }
}

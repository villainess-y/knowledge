package zkConfigurationCenter;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private ZooKeeper zk;

    private MyConf myConf ;

    CountDownLatch latch = new CountDownLatch(1);

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    /**
     * watch回调
     * 监听数据是否修改
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeDeleted:
                // 节点被删除是否需要处理？
                // 节点被删除时，设置Conf = ""，
                myConf.setConf("");
                latch = new CountDownLatch(1);
                break;
            case NodeCreated:
            case NodeDataChanged:
                // 节点被修改，重新获取
                zk.getData("/AppConf",this,this,"context");
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
     * getData()的callBack
     * @param i
     * @param s
     * @param o
     * @param bytes
     * @param stat
     */
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        if(bytes != null){
            String conf = new String(bytes);
            myConf.setConf(conf);
            // 设置完数据之后放行
            latch.countDown();
        }
    }

    /**
     *  状态callBack
     * @param i
     * @param s
     * @param o
     * @param stat
     */
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        if(stat != null){
            // 节点已存在,获取数据
            zk.getData("/AppConf",this,this,"context");
        }
    }

    //实际路径为/testConf/AppConf
    public void aWait() throws InterruptedException {
        zk.exists("/AppConf", this,this,"context");
        // 等待取出数据
        latch.await();
    }
}

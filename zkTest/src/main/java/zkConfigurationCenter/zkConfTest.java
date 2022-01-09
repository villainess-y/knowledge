package zkConfigurationCenter;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class zkConfTest {

    ZooKeeper zk;

    @Before
    public void conn() {
        zk = ZKUtils.getZk();
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConf() throws InterruptedException {
        MyConf myConf = new MyConf();
        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setMyConf(myConf);
        watchCallBack.setZk(zk);

        System.out.println("get conf start");
        while (true) {
            if (myConf.getConf() == null || "".equals(myConf.getConf())) {
                // 节点被删除
                System.out.println("configuration not found !!!");
                watchCallBack.aWait();
            } else {
                System.out.println(myConf.getConf());
            }

            Thread.sleep(500);
        }


    }
}

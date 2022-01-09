package zkLock;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class zkLockTest {

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
    public void lock(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run(){
                    WatchCallBack watchCallBack = new WatchCallBack();
                    watchCallBack.setZk(zk);
                    watchCallBack.setThreadName(Thread.currentThread().getName());
                    // 每一个线程去抢锁
                    watchCallBack.tryLock();
                    // 干活
                    System.out.println("i am " + watchCallBack.getThreadName() );
                    // 释放锁
                    watchCallBack.unLock();
                }
            }.start();
        }

        while (true){

        }
    }
}

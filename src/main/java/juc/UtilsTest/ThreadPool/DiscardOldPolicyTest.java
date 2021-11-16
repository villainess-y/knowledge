package juc.UtilsTest.ThreadPool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 当队列满了时，丢弃最先进入的正在任务队列里等待的线程任务，使得后续任务能够添加进来
 */
public class DiscardOldPolicyTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executorA = new ThreadPoolExecutor(4, 4, 10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        for (int i = 0; i < 100; i++) {
            int s = i;
            executorA.execute(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【" + LocalTime.now() + "】线程 " + Thread.currentThread() + "正在执行任务" + s);
            });
        }
    }
}

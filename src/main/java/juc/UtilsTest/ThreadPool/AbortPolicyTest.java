package juc.UtilsTest.ThreadPool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 丢弃，并抛出异常
 */
public class AbortPolicyTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executorA = new ThreadPoolExecutor(4, 4, 10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
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

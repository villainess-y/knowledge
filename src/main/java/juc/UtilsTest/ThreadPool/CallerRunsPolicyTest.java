package juc.UtilsTest.ThreadPool;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 拒绝，并让`调用者线程`自己运行该方法，而非线程池
 * <p>
 * 这个例子里的`调用者线程`就是main线程，
 * 也可以是 起一个线程，线程里再起线程池，然后测试
 */
public class CallerRunsPolicyTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executorA = new ThreadPoolExecutor(4, 4, 10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(30),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
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

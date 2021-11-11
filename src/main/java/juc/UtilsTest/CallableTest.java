package juc.UtilsTest;

import Utils.SleepHelper;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                SleepHelper.sleepSeconds(2);
                System.out.println("_______");
                return "hello world";

            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c);
        System.out.println("******");
        System.out.println(future.get());
        System.out.println("******");
        service.shutdown();
    }
}

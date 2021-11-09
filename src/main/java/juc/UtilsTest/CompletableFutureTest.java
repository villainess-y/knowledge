package juc.UtilsTest;

import Utils.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    static List<MyTask> tasks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        MyTask task1 = new MyTask("task1", 3, Result.SUCCESS);
        MyTask task2 = new MyTask("task2", 6, Result.SUCCESS);
        MyTask task3 = new MyTask("task3", 2, Result.FAIL);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        for (MyTask task : tasks) {
            CompletableFuture f = CompletableFuture.supplyAsync(() -> task.runTask())
                    .thenAccept((result) -> callback(result, task));
        }
        TimeUnit.SECONDS.sleep(10);

    }

    private static Result callback(Result result, MyTask task) {
        if (Result.FAIL == result) {
            for (MyTask _task : tasks) {
                if (_task != task) {
                    _task.cancel();
                }
            }
        }
        return result;
    }


    private enum Result {
        SUCCESS, FAIL, CANCELLED
    }

    private static class MyTask {
        private final String name;
        private final int timeInSeconds;
        private Result ret;

        boolean cancelling = false;
        volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds;
            this.ret = ret;
        }

        public Result runTask() {
            int interval = 100;
            int total = 0;
            try {
                for (; ; ) {
                    Thread.sleep(interval);
                    if (cancelling) continue;
                    total += interval;
                    if(total >= timeInSeconds){
                        break;
                    }
                    if(cancelled){
                        return Result.CANCELLED;
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(name + "end!");
            return ret;
        }

        public void cancel() {
            cancelling = true;
            synchronized (this){
                System.out.println(name + "cancelling");
                SleepHelper.sleepMilli(50);
                System.out.println(name + "cancelled");
            }
            cancelled = true;
        }
    }
}

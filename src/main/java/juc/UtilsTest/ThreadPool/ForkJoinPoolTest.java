package juc.UtilsTest.ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    static final int MAX_NUM = 50000;
    static int[] nums = new int[1000000];
    static Random r = new Random();

    static {
        for (int j = 0; j < nums.length; j++) {
            nums[j] = r.nextInt(100);
        }
        System.out.println("----" + Arrays.stream(nums).sum());
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPoolTest test = new ForkJoinPoolTest();
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask t1 = new AddTask(0, 1000000);
        fjp.execute(t1);
        Long result = t1.join();
        System.out.println("result : " + result);
        System.in.read();
    }

    static class AddTask extends RecursiveTask<Long> {
        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + "\tto:" + end + "\t=" + sum);
                return sum;
            }
            int middle = start + (end - start) / 2;
            AddTask t1 = new AddTask(start, middle);
            AddTask t2 = new AddTask(middle, end);
            t1.fork();
            t2.fork();
            return t1.join() + t2.join();
        }
    }
}

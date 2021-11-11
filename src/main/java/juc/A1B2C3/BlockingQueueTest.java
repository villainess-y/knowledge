package juc.A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {


    public static void main(String[] args) {
        char[] c1 = "123456".toCharArray();
        char[] c2 = "ABCDEF".toCharArray();

        BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

        new Thread(() -> {
            try {
                for (char c : c1) {
                    q2.take();
                    System.out.print(c);
                   q1.put("ok");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                for (char c : c2) {
                    System.out.print(c);
                    q2.put("ok");
                    q1.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

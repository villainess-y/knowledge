package jvm.A1B2C3;

import java.util.concurrent.CountDownLatch;

public class waitAndNotify {

    // 有可能是1A...，也有可能是A1...
    // 如果要保证顺序，则需要在第二个输出的线程里加上CountDownLatch的wait，等第一个线程输出了之后，
    //调用
    CountDownLatch latch = new CountDownLatch(1);
    //线程2：
    //latch.await() 停住
    // print...
    //线程1：
    //print...
    //  latch.countDown();

    public static void main(String[] args) {
        final Object o = new Object();
        char[] c1 = "123456".toCharArray();
        char[] c2 = "ABCDEF".toCharArray();
        new Thread(()->{
            synchronized (o){
                for (char c : c1){
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();

        new Thread(()->{
            synchronized (o){
                for (char c : c2){
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        },"t1").start();
    }
}

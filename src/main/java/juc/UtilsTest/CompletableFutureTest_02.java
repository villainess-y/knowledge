package juc.UtilsTest;

import Utils.SleepHelper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest_02 {

    public static void main(String[] args) throws IOException {
        long start,end;

        start = System.currentTimeMillis();

        /*CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
         // 必须等三个结果都返回了才能往下运行
        CompletableFuture.allOf(futureJD,futureTB,futureTM).join();
        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end-start));*/

        CompletableFuture.supplyAsync(()-> priceOfTM()).thenApply(String::valueOf).thenApply(str -> "TM price:"+str).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(()-> priceOfJD()).thenApply(String::valueOf).thenApply(str -> "JD price:"+str).thenAccept(System.out::println);
        CompletableFuture.supplyAsync(()-> priceOfTB()).thenApply(String::valueOf).thenApply(str -> "TB price:"+str).thenAccept(System.out::println);
        System.in.read();



    }

    private static Double priceOfJD(){
        delay();
        return Double.valueOf("6.18");
    }
    private static Double priceOfTM(){
        delay();
        return Double.valueOf("12.12");
    }

    private static Double priceOfTB(){
        delay();
        return Double.valueOf("11.11");
    }

    private static void delay(){
        int time = new Random().nextInt(500);
        SleepHelper.sleepMilli(time);
        System.out.printf("After %s sleep !\n",time);
    }
}

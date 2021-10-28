package jvm.A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    private static Thread t1,t2;

    public static void main(String[] args) {

        char[] c1 = "123456".toCharArray();
        char[] c2 = "ABCDEF".toCharArray();

        t1 = new Thread(()->{
            for (char c : c1){
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");

        t2 = new Thread(()->{
            for(char c : c2){
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }

        },"t2");

        t1.start();
        t2.start();

    }


}

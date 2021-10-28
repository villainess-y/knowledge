package jvm.A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {

    public static void main(String[] args) {
        char[] c1 = "123456".toCharArray();
        char[] c2 = "ABCDEF".toCharArray();
        TransferQueue<Character> queue = new LinkedTransferQueue<>();
        new Thread(()-> {
            try {
                for (char c : c1) {
                    System.out.print(queue.take());
                    queue.transfer(c);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();
        new Thread(()-> {
            try {
                for (char c : c2) {
                    queue.transfer(c);
                    System.out.print(queue.take());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }).start();
    }
}

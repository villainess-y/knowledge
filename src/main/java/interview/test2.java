package interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put与get方法，以及getCount方法
 * 能够支持两个生产者线程与10个消费者线程的阻塞调用
 */
public class test2 {

    final private LinkedList<String> lists = new LinkedList<String>();
    final private int MAX = 10;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition customer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public static void main(String[] args) {
        test2 t = new test2();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + "\tget\t" + t.get());
                }
            }, "t" + i).start();
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    t.put(String.valueOf(j));
                    System.out.println(Thread.currentThread().getName() + "\tput\t" + j);
                }
            },"p" + i).start();
        }
    }

    public void put(String t) {
        try {
            lock.lock();
            while (lists.size() == MAX){
                producer.await();
            }
            lists.add(t);
            ++count;
            customer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public String get() {
        String t = null;
        try {
            lock.lock();
            while (lists.size() == 0){
                customer.await();
            }

            t = lists.peekFirst();
            --count;
            producer.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

}

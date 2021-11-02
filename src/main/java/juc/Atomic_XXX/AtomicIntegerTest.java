package juc.Atomic_XXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomXXX类方法都是原子性，但不能保证多个方法连续调用是原子性
 */
public class AtomicIntegerTest {

    // volatile int count = 0;
    AtomicInteger count = new AtomicInteger(0);

    /* synchronized */ void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();// count++
        }
    }

    public static void main(String[] args) {
        AtomicIntegerTest t = new AtomicIntegerTest();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(t::m,"thread-"+i));
        }
        threadList.forEach(Thread::start);
        threadList.forEach(o ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}

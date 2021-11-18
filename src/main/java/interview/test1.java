package interview;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class test1 {

    List<Object> list = new ArrayList<>();

    public void add(Object o){
        list.add(0);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        test1 t = new test1();

        Thread t2 = new Thread(() ->{
            LockSupport.park();
            System.out.println("数量到达5个");
        },"t2");

        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add\t" +i);
                if(i == 4){
                    LockSupport.unpark(t2);
                }
            }
        },"t1");
        t1.start();
        t2.start();
    }

}

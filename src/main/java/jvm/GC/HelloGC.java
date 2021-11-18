package jvm.GC;

import java.util.LinkedList;
import java.util.List;

//java -Xmn10M -Xms10M -Xmx10M -XX:+PrintCommandLineFlags -XX:+PrintGCDetails HelloGC
public class HelloGC {
    public static void main(String[] args) {
        System.out.println("hello GC!");
        List list = new LinkedList<>();
        for (;;){
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }
}

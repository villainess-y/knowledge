package jvm.object;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

class Biology {
    protected String type;
    protected int id;
    protected int rootId;
    protected boolean extince;
}


@Data
public class Person extends Biology{
    private Person children;
    private String name;
    private long height;
    private int age;
    private String address;
    private boolean man;
    private int areaCode;

    public static void printf(Person p) {
        // 查看对象的整体结构信息
        //JOL工具类
        System.out.println(ClassLayout.parseInstance(p).toPrintable());
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("小陈");
        person.setAge(18);
        Person children = new Person();
        children.setName("小小陈");
        children.setAge(1);
        person.setChildren(children);

        System.out.println(Integer.toHexString((int) VM.current().addressOf(person)));
        //输出对象内存地址
        System.out.println(GraphLayout.parseInstance(person).toPrintable());
        //打印对象布局信息
        printf(person);
    }
}


// 无锁状态 对象头数据展示
//    public static void main(String[] args) {
//        //创建一个实例
//        Person person = new Person();
//        // 输入对象信息
//        System.out.println(person);
//        //输出信息
//        Person.printf(person);
//    }

// 偏向锁测试
// 偏向锁需要系统启动4秒后才开启，是延迟加载偏向锁，所以需要睡眠5S
//    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
//        //创建一个实例
//        Person person = new Person();
//        //输出信息
//        System.out.println(
//                "============================before lock============================");
//        Person.printf(person);
//        synchronized (person) {
//            System.out.println(
//                    "============================locked============================");
//            Person.printf(person);
//        }
//        //输出信息
//        System.out.println(
//                "============================after lock============================");
//        Person.printf(person);
//    }


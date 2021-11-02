package juc.Singleton;

import java.io.*;

/**
 * 有时候需要将对象序列化后写入磁盘，下次使用时从磁盘读取对象，反序列化成为内存对象。
 * 反序列化后的对象会重新分配内存，即重新创建。
 * 如果序列化的目标是单例对象，则违背了单例的初衷，相当于破坏了单例。
 */
public class SerializableSingleton implements Serializable {
    public final static SerializableSingleton INSTANCE = new SerializableSingleton();
    private SerializableSingleton(){};

    public static SerializableSingleton getInstance(){
        return INSTANCE;
    }
}

class SerializableSingletonTest {

    public static void main(String[] args) {
        SerializableSingleton s1 = null;
        SerializableSingleton s2 = SerializableSingleton.getInstance();

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("SerializableSingleton.obj");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(s2);
            objectOutputStream.flush();
            objectOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("SerializableSingleton.obj");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            s1 = (SerializableSingleton) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

// 运行结果：
//Simple.Factory.Pattern.SerializableSingleton@378bf509
//        Simple.Factory.Pattern.SerializableSingleton@14ae5a5
//        false

package jvm.ClassLoader;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader("out/production/Test/juc/A1B22C3/");
        Class<?> clazz = classLoader.loadClass("LockSupportTest");
        System.out.println("LockSupportTest字节码是由" + clazz.getClassLoader().getClass().getName() + "加载的...");
    }
}

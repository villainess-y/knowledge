package juc.Singleton;

/**
 * 静态内部类
 * 加载外部类时不会加载内部类，只有第一次调用getInstance方法时，
 * JVM才加载 LazyInnerClassSingleton 并初始化INSTANCE ，
 * 只有一个线程可以获得对象的初始化锁，其他线程无法进行初始化，保证对象的唯一性。
 */
//这种形式兼顾饿汉式的内存浪费，也兼顾synchronized 性能问题
//完美地屏蔽了这两个缺点
public class LazyInnerClassSingleton {
    //默认使用LazyInnerClassGeneral 的时候，会先初始化内部类
    //如果没使用的话，内部类是不加载的
    private LazyInnerClassSingleton(){
        if(LazyHolder.LAZY != null){
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    //每一个关键字都不是多余的
    //static 是为了使单例的空间共享
    //保证这个方法不会被重写，重载
    public static LazyInnerClassSingleton getInstance(){
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }
    //默认不加载
    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}

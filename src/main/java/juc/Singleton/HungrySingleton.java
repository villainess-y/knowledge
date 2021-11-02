package juc.Singleton;

/**
 * 饿汉式单例是在类加载的时候就立即初始化，并且创建单例对象。绝
 * 对线程安全，在线程还没出现以前就是实例化了，不可能存在访问安全问题。
 *
 * 优点：没有任何的锁，执行效率高
 * 缺点：类加载的时候初始化，不管用不用，占用空间
 */
public class HungrySingleton {
    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return HUNGRY_SINGLETON;
    }
}


// 另一种写法
//饿汉式静态块单例
/**
public class HungryStaticSingleton {
    private static final HungryStaticSingleton hungrySingleton;
    static {
        hungrySingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){}

    public static HungryStaticSingleton getInstance(){
        return hungrySingleton;
    }
}
**/

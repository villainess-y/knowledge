package juc.Singleton;

/**
 * 懒汉式单例的特点是，被外部类调用的时候，内部才会加载
 */
public class LazyClassSingleton {
    private static volatile LazyClassSingleton INSTANCE;

    private LazyClassSingleton(){}

    public static LazyClassSingleton getInstance(){
        if(INSTANCE == null){
            synchronized (LazyClassSingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new LazyClassSingleton();
                }
            }
        }
        return INSTANCE;
    }

}

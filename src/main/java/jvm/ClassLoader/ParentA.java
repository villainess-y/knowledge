package jvm.ClassLoader;

/**
 * 类加载顺序
 */
public class ParentA {
    static {
        System.out.println("1");
    }
    public ParentA() {
        System.out.println("2");
    }
}

class SonB extends ParentA {
    static {
        System.out.println("a");
    }
    public SonB() {
        System.out.println("b");
    }
    public static void main(String[] args) {
        ParentA ab = new SonB();
        ab = new SonB();
    }
}

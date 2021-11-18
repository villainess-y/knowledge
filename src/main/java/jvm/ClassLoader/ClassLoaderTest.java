package jvm.ClassLoader;

public class ClassLoaderTest {

    public static void main(String[] args) {
        /*输出为  null           说明是由 Bootstrap ClassLoader加载的
          输出为  ExtClassLoader 说明是由 Extension ClassLoader加载的
          输出为  AppClassLoader 说明是由 Application ClassLoader加载的

          可以看到  ExtClassLoader 与 AppClassLoader 均是被 Bootstrap ClassLoader 加载的
          各个加载器加载的路径，可在  sun.misc.Launcher  内部类查看
         */
        System.out.println("-----查看各种class的加载器是哪种---------");
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println("-----查看各种加载器的加载器是谁------------");
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getClassLoader());
        System.out.println("-----查看各类加载器加载的内容-------------");
        System.out.println("-----Bootstrap ClassLoader 加载器可加载的内容-------------");
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";",System.lineSeparator()));
        System.out.println("-----Extension ClassLoader 加载器可加载的内容-------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";",System.lineSeparator()));
        System.out.println("-----Application ClassLoader 加载器可加载的内容-------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";",System.lineSeparator()));
    }
}

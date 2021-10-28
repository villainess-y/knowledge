package jvm.ClassLoader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader{

    // 1.自定义字节码文件
    private String codePath;
    // 2.定义构造方法


    public MyClassLoader(ClassLoader parent, String codePath) {
        super(parent);
        this.codePath = codePath;
    }

    public MyClassLoader(String codePath) {
        this.codePath = codePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 声明输入输出流
        BufferedInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try {
            // 字节码路径
            String file = codePath + name + ".class";
            inputStream = new BufferedInputStream(new FileInputStream(file));
            outputStream = new ByteArrayOutputStream();

            // I/O读写操作
            int len;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }

            // 获取内存中的字节数组
            byte[] bytes = outputStream.toByteArray();

            // 调用defineClass 将字节数组转成.class实例
            Class<?> clazz = defineClass(null,bytes,0,bytes.length);

            // 返回实例
            return clazz;

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

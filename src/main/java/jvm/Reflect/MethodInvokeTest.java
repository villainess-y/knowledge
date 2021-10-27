package jvm.Reflect;

import java.lang.reflect.Method;

public class MethodInvokeTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("jvm.Reflect.MethodInvokeTest");
        Method method = clazz.getMethod("target", int.class);
        for (int i = 0; i < 16; i++) {
            method.invoke(null, i);
        }
    }

    public static void target(int i) {
        System.out.println("test: " + i);
    }
}

package jvm.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description
 *
 * @author wangzhongyu 2022/01/11 3:15 下午
 */
public class ProxyTest {

    public static void main(String[] args) {
        CalculatorImpl target = new CalculatorImpl();
        ICalculator calculator = (ICalculator) getProxy(target);
        calculator.add(0,1);
        calculator.subtract(100,1);
    }

    private static Object getProxy(final Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy1, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + "方法正在执行。。。");
                        Object result = method.invoke(target,args);
                        System.out.println(method.getName() + "方法执行结果是：" + result.toString());
                        return result;
                    }
                }
        );
    }
}

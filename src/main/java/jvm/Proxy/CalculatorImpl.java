package jvm.Proxy;

/**
 * description
 *
 * @author wangzhongyu 2022/01/11 3:17 下午
 */
public class CalculatorImpl implements ICalculator{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int subtract(int a, int b) {
        return a-b;
    }
}

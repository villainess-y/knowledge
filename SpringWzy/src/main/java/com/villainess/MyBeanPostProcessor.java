package com.villainess;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * description
 *
 * @author wangzhongyu 2022/01/10 5:22 下午
 */
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("wzyBean".equals(beanName)){
            System.out.println("BeanPostProcessor 实现类 MyBeanPostProcessor.postProcessBeforeInitialization 方法调用中。。。");
        }
        return  bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("wzyBean".equals(beanName)){
            System.out.println("BeanPostProcessor 实现类 MyBeanPostProcessor.postProcessAfterInitialization 方法调用中。。。");
        }
        return bean;
    }

    public MyBeanPostProcessor() {
        System.out.println("MyBeanPostProcessor 构造器。。。。");
    }
}

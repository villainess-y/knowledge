package com.wzyIocTest;


import org.springframework.beans.factory.InitializingBean;

/**
 * description
 *
 * @author wangzhongyu 2022/01/10 5:20 下午
 */
public class WzyBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("WzyBean Bean afterProperties set...");
    }

    public WzyBean() {
        System.out.println("WzyBean Bean 构造器。。");
    }
}

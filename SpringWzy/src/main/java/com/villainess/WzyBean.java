package com.villainess;

import org.springframework.beans.factory.InitializingBean;

/**
 * description
 *
 * @author wangzhongyu 2022/01/10 5:20 下午
 */
public class WzyBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Villainess Bean afterProperties set...");
    }

    public WzyBean() {
        System.out.println("villainess Bean 构造器。。");
    }
}

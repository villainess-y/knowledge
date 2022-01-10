package com.villainess;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author wangzhongyu 2022/01/10 5:28 下午
 */
public class SpringWzyIocTest {


    @Test
    public void testIoc(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        WzyBean myBean = applicationContext.getBean(WzyBean.class);
        System.out.println(myBean);
    }
}

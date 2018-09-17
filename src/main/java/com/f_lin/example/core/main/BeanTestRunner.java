package com.f_lin.example.core.main;

import com.f_lin.example.core.beans.BeanTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author F_lin fengjunlin@23mofang.com
 * @since 2018/9/17
 **/
public class BeanTestRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("core.xml");
        BeanTest test1 = context.getBean(BeanTest.class);
        test1.testBeanExcute();
        BeanTest test2 = (BeanTest) context.getBean("beanTest");
        test2.testBeanExcute();
        // 单例？ 默认scope = false
        System.out.println("单例? " + (test1 == test2));
    }
}
